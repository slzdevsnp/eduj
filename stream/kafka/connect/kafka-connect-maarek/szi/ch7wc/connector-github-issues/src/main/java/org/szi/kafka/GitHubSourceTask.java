package org.szi.kafka;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.szi.kafka.model.*;
import org.szi.kafka.utils.DateUtils;

import java.time.Instant;
import java.util.*;
import static org.szi.kafka.GitHubSchemas.*;  //imports static strings


public class GitHubSourceTask extends SourceTask {
    private static final Logger log = LoggerFactory.getLogger(GitHubSourceTask.class);
    public GitHubSourceConnectorConfig config;

    protected Instant nextQuerySince;
    protected Integer lastIssueNumber;
    protected Integer nextPageToVisit = 1;
    protected Instant lastUpdatedAt;

    GitHubAPIHttpClient gitHubHttpAPIClient;

    @Override
    public String version() {
        return VersionUtil.getVersion();
    }

    @Override
    public void start(Map<String, String> map) {
        //Do things here that are required to start your task. This could be open a connection to a database, etc.
        config = new GitHubSourceConnectorConfig(map);
        initializeLastVariables(); // called  once  each connector's restart
        gitHubHttpAPIClient = new GitHubAPIHttpClient(config);
    }


    private void initializeLastVariables(){
        Map<String, Object> lastSourceOffset = null;
        lastSourceOffset = context.offsetStorageReader().offset(sourcePartition()); //framework provides
        if( lastSourceOffset == null){ // lastOffset can't be read from context
            //very first call
            // we haven't fetched anything yet, so we initialize to 7 days ago
            nextQuerySince = config.getSince();
            lastIssueNumber = -1;
        } else {
            // read variables, parse them to their expected types
            Object updatedAt = lastSourceOffset.get(UPDATED_AT_FIELD);
            Object issueNumber = lastSourceOffset.get(NUMBER_FIELD);  //expect to alway be null as it is not defined in SourceOffset
            Object nextPage = lastSourceOffset.get(NEXT_PAGE_FIELD);
            if(updatedAt != null && (updatedAt instanceof String)){
                nextQuerySince = Instant.parse((String) updatedAt);  // need for client query
            }
            if(issueNumber != null && (issueNumber instanceof String)){
                lastIssueNumber = Integer.valueOf((String) issueNumber);
            }
            if (nextPage != null && (nextPage instanceof String)){ //need for client query
                nextPageToVisit = Integer.valueOf((String) nextPage);
            }
        }
        log.debug("initialized lastIssueNumber - {}",lastIssueNumber); //expect to be null
        //log offset
        if(lastSourceOffset != null){
            lastSourceOffset.entrySet().stream()
                    .forEach(e ->  log.info("lastSourceOffset key: {}  value: {}",e.getKey(),e.getValue()));
        }else{
            log.debug("lastSourceOffset is null, the very first run");
        }
    }



    @Override
    public List<SourceRecord> poll() throws InterruptedException {  //continious polling
        gitHubHttpAPIClient.sleepIfNeed(); //NB
        //in poll() Thread sleeps  for pollFrequency milliseconds

        // fetch data (an array of github repo issues )
        final ArrayList<SourceRecord> records = new ArrayList<>();
        JSONArray issues = gitHubHttpAPIClient.getNextIssues(nextPageToVisit, nextQuerySince);
        // we'll count how many results we get with i
        int i = 0;
        //process all elements fetched from the request of this polling
        for (Object obj : issues) {
            Issue issue = Issue.fromJson((JSONObject) obj); //parse json
            SourceRecord sourceRecord = generateSourceRecord(issue); // create kafka record
            records.add(sourceRecord);
            i += 1;
            lastUpdatedAt = issue.getUpdatedAt(); //reassign at each iteration
        }
        if (i > 0) log.info(String.format("Fetched %s record(s)", i));
        if (i == config.getBatchSize() ){  // config.getBatchSize().intValue()
            // we have reached a full batch, we need to get the next one
            nextPageToVisit += 1;
        }
        else {  //when a since Param gets updates
            nextQuerySince = lastUpdatedAt.plusSeconds(1);
            nextPageToVisit = 1;
            gitHubHttpAPIClient.sleep();
        }
        return records;
    }

    private SourceRecord generateSourceRecord(Issue issue) {
        return new SourceRecord(
                sourcePartition(),
                sourceOffset(issue.getUpdatedAt()),
                config.getTopic(),
                null, // partition will be inferred by the framework
                KEY_SCHEMA,
                buildRecordKey(issue),
                VALUE_SCHEMA,
                buildRecordValue(issue),
                issue.getUpdatedAt().toEpochMilli());
    }

    @Override
    public void stop() {
        // Do whatever is required to stop your task.
    }

    private Map<String, String> sourcePartition() {
        Map<String, String> map = new HashMap<>();
        map.put(OWNER_FIELD, config.getOwnerConfig());
        map.put(REPOSITORY_FIELD, config.getRepoConfig());
        return map;
    }

    private Map<String, String> sourceOffset(Instant updatedAt) {
        Map<String, String> map = new HashMap<>();
        map.put(UPDATED_AT_FIELD, DateUtils.MaxInstant(updatedAt, nextQuerySince).toString());
        map.put(NEXT_PAGE_FIELD, nextPageToVisit.toString());
        return map;
    }


    private Struct buildRecordKey(Issue issue){
        // Key Schema
        Struct key = new Struct(KEY_SCHEMA)
                .put(OWNER_FIELD, config.getOwnerConfig())
                .put(REPOSITORY_FIELD, config.getRepoConfig())
                .put(NUMBER_FIELD, issue.getNumber());

        return key;
    }

    private Struct buildRecordValue(Issue issue){

        // Issue top level fields
        Struct valueStruct = new Struct(VALUE_SCHEMA)
                .put(URL_FIELD, issue.getUrl())
                .put(TITLE_FIELD, issue.getTitle())
                .put(CREATED_AT_FIELD, issue.getCreatedAt().toEpochMilli())
                .put(UPDATED_AT_FIELD, issue.getUpdatedAt().toEpochMilli())
                .put(NUMBER_FIELD, issue.getNumber())
                .put(STATE_FIELD, issue.getState());

        // User is mandatory
        User user = issue.getUser();
        Struct userStruct = new Struct(USER_SCHEMA)
                .put(USER_URL_FIELD, user.getUrl())
                .put(USER_ID_FIELD, user.getId())
                .put(USER_LOGIN_FIELD, user.getLogin());
        valueStruct.put(USER_FIELD, userStruct);  //!NB

        // Pull request is optional
        PullRequest pullRequest = issue.getPullRequest();
        if (pullRequest != null) {
            Struct prStruct = new Struct(PR_SCHEMA)
                    .put(PR_URL_FIELD, pullRequest.getUrl())
                    .put(PR_HTML_URL_FIELD, pullRequest.getHtmlUrl());
            valueStruct.put(PR_FIELD, prStruct);  //!NB
        }

        return valueStruct;
    }

}