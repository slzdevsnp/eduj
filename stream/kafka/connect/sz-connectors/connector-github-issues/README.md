Welcome to your new Kafka Connect connector!

## Running in development using locally installed confluent

```
mvn clean package
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"
$CONFLUENT_HOME/bin/connect-standalone $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties config/GitHubSourceConnector.properties
```

## run connector from its custom build docker image (dip way)
* install connector : `mvn package`
*  build docker image: `build-local-docker-dip.sh`
*  start connector docker with other services:  `docker-compose up -d`
*   register connector with a config: (see file requests.http)
*   `POST http://localhost:8083/connectors    Content-Type: application/json  {"name": "github-issues-connector","config":{..}} `
* one can restart the connect container, and reregister  connector.  It should work from offsets stored in kafka topic

