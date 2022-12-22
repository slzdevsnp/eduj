FROM confluentinc/cp-kafka-connect:3.2.0

WORKDIR /connector-github-issues
COPY config config
COPY target target

VOLUME /connector-github-issues/config
VOLUME /connector-github-issues/offsets

CMD CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')" connect-standalone config/worker.properties config/GitHubSourceConnectorExample.properties

