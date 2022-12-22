package org.szi.kafka;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.szi.kafka.model.Issue;
import org.json.JSONObject;
import org.junit.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.szi.kafka.GitHubSourceConnectorConfig.*;

public class GitHubSourceTaskTest {

    GitHubSourceTask gitHubSourceTask = new GitHubSourceTask();
    private Integer batchSize = 10;

    private Map<String, String> initialConfig() {
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_CONFIG, "apache");
        baseProps.put(REPO_CONFIG, "kafka");
        baseProps.put(SINCE_CONFIG, "2017-04-26T01:23:44Z");
        baseProps.put(BATCH_SIZE_CONFIG, batchSize.toString());
        baseProps.put(TOPIC_CONFIG, "github-issues");
        baseProps.put(AUTH_USERNAME_CONFIG, "slzdevsnp");
        baseProps.put(AUTH_PASSWORD_CONFIG, "xxxxxxxx"); //SZI
        return baseProps;
    }


    @Test
    public void shouldCallNextIssueApi() throws UnirestException {
        gitHubSourceTask.config = new GitHubSourceConnectorConfig(initialConfig());
        gitHubSourceTask.nextPageToVisit = 1;
        gitHubSourceTask.nextQuerySince = Instant.parse("2017-01-01T00:00:00Z");
        gitHubSourceTask.gitHubHttpAPIClient = new GitHubAPIHttpClient(gitHubSourceTask.config);
        String url = gitHubSourceTask.gitHubHttpAPIClient.constructUrl(gitHubSourceTask.nextPageToVisit, gitHubSourceTask.nextQuerySince);
        System.out.println(url);
        HttpResponse<JsonNode> httpResponse = gitHubSourceTask.gitHubHttpAPIClient.getNextIssuesAPI(gitHubSourceTask.nextPageToVisit, gitHubSourceTask.nextQuerySince);
        if (httpResponse.getStatus() != 403) {
            assert (httpResponse.getStatus() == 200);
            Set<String> headers = httpResponse.getHeaders().keySet();
            System.out.println("headers:"+headers);
            assert (headers.contains("ETag"));  //etag  if not-authenticated
            assert (headers.contains("X-RateLimit-Limit")); //X-Ratelimit-Limit
            assert (headers.contains("X-RateLimit-Remaining"));
            assert (headers.contains("X-RateLimit-Reset"));
            assert (httpResponse.getBody().getArray().length() == 10);
            JSONObject jsonObject = (JSONObject) httpResponse.getBody().getArray().get(0);
            Issue issue = Issue.fromJson(jsonObject);
            assert (issue != null);
            assert (issue.getNumber() == 2072);
        }
    }
}
