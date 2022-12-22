package org.szi.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static org.szi.kafka.GitHubSourceConnectorConfig.*;

@Slf4j
public class GitHubAPIHttpClientTest {


    private GitHubSourceConnectorConfig config ;
    private GitHubAPIHttpClient client;

    @Before
    public void setup(){
        GitHubSourceConnectorConfig config = new GitHubSourceConnectorConfig(initialConfig());
        client = new GitHubAPIHttpClient(config);
    }

    private Map<String, String> initialConfig() {
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_CONFIG, "kubernetes");
        baseProps.put(REPO_CONFIG, "kubernetes");
        baseProps.put(SINCE_CONFIG, "2018-01-01T01:23:45Z");
        baseProps.put(BATCH_SIZE_CONFIG, "2"); //2 entries per page
        baseProps.put(TOPIC_CONFIG, "github-issues");
        return baseProps;
    }

    @Test
    public void shouldGetNextIssues() throws InterruptedException {
        Instant since = Instant.parse("2021-01-01T00:00:30.00Z");
        JSONArray array = client.getNextIssues(Integer.valueOf(0), since);
        assertThat(array, notNullValue());
        assertThat(array.length(), is(2));
        log.debug("array: - {}",array.toString());
    }

}
