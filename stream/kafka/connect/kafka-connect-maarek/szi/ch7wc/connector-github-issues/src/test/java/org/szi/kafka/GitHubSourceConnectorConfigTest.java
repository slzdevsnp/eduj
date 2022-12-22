package org.szi.kafka;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigValue;
import org.junit.Test;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleToIntFunction;

import static org.szi.kafka.GitHubSourceConnectorConfig.*;   //to import static fields

public class GitHubSourceConnectorConfigTest {

  private ConfigDef configDef = GitHubSourceConnectorConfig.conf();

  private Map<String, String> initialConfig() {
    Map<String, String> baseProps = new HashMap<>();
    baseProps.put(OWNER_CONFIG, "foo");
    baseProps.put(REPO_CONFIG, "bar");
    baseProps.put(SINCE_CONFIG, "2017-04-26T01:23:45Z");
    baseProps.put(BATCH_SIZE_CONFIG, "100");
    baseProps.put(TOPIC_CONFIG, "github-issues");
    return baseProps;
  }

  @Test
  public void doc() { //this prints config doc
    System.out.println(GitHubSourceConnectorConfig.conf().toRst());
  }

  @Test
  public void initialConfigIsValid() {
    assert (configDef.validate(initialConfig())
            .stream()
            .allMatch(configValue -> configValue.errorMessages().size() == 0));
  }

  @Test
  public void canReadConfigCorrectly() {
    GitHubSourceConnectorConfig config = new GitHubSourceConnectorConfig(initialConfig());
    config.getAuthPassword();

  }


  @Test
  public void validateSince() {
    Map<String, String> config = initialConfig();
    config.put(SINCE_CONFIG, "not-a-date");
    List<ConfigValue> configVals = configDef.validate(config);
    for (ConfigValue cv :  configVals){
      if (cv.name() =="since.timestamp"){
        assert (cv.errorMessages().size() > 0);
      }
    }
  }

   @Test
  public void validateBatchSize() {
    Map<String, String> config = initialConfig();
    config.put(BATCH_SIZE_CONFIG, "-1");
    List<ConfigValue> configValues = configDef.validate(config);
    Iterator<ConfigValue> configValsIterator = configValues.iterator();
    while(configValsIterator.hasNext()){
      ConfigValue cvalue = configValsIterator.next();
      if ( cvalue.name()=="batch.size"){
        assert (cvalue.errorMessages().size() > 0);
      }
    }

  }

  @Test
  public void validateUsername() {
    Map<String, String> config = initialConfig();
    config.put(AUTH_USERNAME_CONFIG, "username");
    ConfigValue configValue = configDef.validate(config).get(0);
    assert (configValue.errorMessages().size() == 0);
  }

  @Test
  public void validatePassword() {
    Map<String, String> config = initialConfig();
    config.put(AUTH_PASSWORD_CONFIG, "password");
    ConfigValue configValue = configDef.validate(config).get(0);
    assert (configValue.errorMessages().size() == 0);
  }

}
