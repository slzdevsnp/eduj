# Apache kafka Connect hands-on 

NB! this file is movied in inkdrop : edu/kafka/kafka-connect-hands-on note

author Stephane Maarek

project code:

https://github.com/PacktPublishing/Apache-Kafka-Series---Kafka-Connect-Hands-on-Learning

## ch2 Kafka Connect Concepts
There is a lot of connectors for kafka-connectors:
* sources
* sinks
### Connect Architecture Design




```txt

              ||connect cluster||    ||kafka cluster||
=======         | worker |              | broker1 |  ->  [streaming app1] 
Source connecor =>                                   <-    
=======         | worker |              | broker2 |  ->  [streaming app2]
                                                <-  
======          | worker |              | broker3 |  ->  [streaming app3] 
Sink connector        <=                             <-  
======          | worker |              | zookeeper |
```

**Kafka Connect Cluster** has multiple loaded connectors
* each connector is a reusable jar file
* many connectors exist in open source

Connector + **User Configuration => Task**
Workers have (mutliple) tasks.

A task is a java process configured with a particular connector.

**Standalone** vs **distributed** mode

* standalone is easy and good for development
  * single process runs your connectors and tasks
  * configuration is bundled with  your process
  * not fault tolerant, no horisontal  scalability, hard to monitor
* distributed  is fault-tolerant
  * multiple workers run connectors and tasks
  * configuration is submitted using a rests api
  * easy toscale and fault tolerant (rebalancing if a worker dies) vclip S3.10
  * tasks can migrate from a failed host 
  * **For** production deployment of connectors

## ch3 setup and launch Kafka Connect Cluster

!w demo `szi/ch4-connect-cluster`

docker-compose.yml  has images for kafka cluster from 
docker image landoop/fast-data-dev with ports mapped:

* 2181 #zookeeper
* 3030 #landoop UI
* 8081-8083 #REST Proxy, schema registry, connect ports
* 9581-9585 #JMX Ports
* 9092:9092  # Kafka Broker

Other 2 images are for elasticsearch and for postgres

File `kafka-connect-tutorial-sources.sh`  is a script with step by step demo instructions
```
# Start our kafka cluster
docker-compose up kafka-cluster
```
goto landoo UI at  http://127.0.0.1:3030

it takes about 3-5 mins to get in Landoo UI  **1 connector Running**
* click _Enter_ to see  the config of **logs-broker** connector
* click _New_ to see list of all supported kafka connectors  

#### Troubleshooting general approach
* breathe, errrors are good for learning purposes
* make sure your config is correct
* make sure you followed instructions correctly
* look at logs. is there an error ? 
* google on the topic, stackoverflow
* ask question on on the web

`http://127.0.0.1:3030/logs/`  is a url to check cluster logs in distributed mod

Ctrl-C  to stop the kafka cluster

```
https://github.com/lensesio/fast-data-dev

##demo online

#to update  landoop docker image
docker pull landoop/fast-data-dev
```
`demo docker-compose_latest.yml` is a kafka-cluster with a lates image from landoop
```
docker-compose  -f docker-compose_latest.yml  up kafka-cluster
```

Landoop docker image github repo
https://github.com/lensesio/fast-data-dev

## Ch6 Kafka Connect Source hands On
`/szi/ch4-connect-cluster/source/demo-1`
goals:
* read a file and write its content directly into kafka
* run a connector in standalone mode
files:
* demo-1/workder.properties  : a config for a source connector in a standalone mode
	* convert strings to json.  props key.converter value.converter are important
* demo-1/file-stream-demo-standalone.properties  is a confirm for a connector itself (file source connector)

Instructions are in kafka-connect-tutorial-sources.ch

1. start kafka cluster with 
```
docker-compose  -f docker-compose_latest.yml  up kafka-cluster
```
2. connect to a kafka-cluster container with files mounted
```
docker run --rm -it -v "$(pwd)":/tutorial --net=host landoop/fast-data-dev:latest bash
```
3. inside a docker vm , create topics
```
#/tutorial is mapped to host's szi/ch4_connect-cluster folder
cd /tutorial/source/demo-1

kafka-topics --create --topic demo-1-standalone --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181

# launch a connector with its config (part of kafka distribution)
connect-standalone worker.properties file-stream-demo-standalone.properties
```
`worker.properties` is first argument a worker konfing in standalone mode

`file-stream-demo-standalone.properties`  is a config about type of connector (FileStreamSourceConnector) witha param like source file=demo-file.txt  and a configured topic

Wait until you see in the Log output `INFO Created connector file-stream-demon-standalone !NB make sure standalone.offsets file in the same folder is deleted.`



Now write some lines in the file `demo-file.txt`

In Landoo topics UI you see those line as kafka messages in topic

Now stop the connector (Ctrl-C). 
* Obseve the creation of standalone.offsets file 

Now restart the connector  

Refresh the UI topic and observe that file was not reread, and did not generate any new messages. 

Write an additional line in demo-file.txt   and see the new message arrive

###  source/demo-2 fileconnector distributed

connector with enabled schema.  Instructions  still in `kafka-connect-tutorial-sources.sh`
```
docker run --rm -it  --net=host landoop/fast-data-dev:latest bash
```
create demo2 tpic
```
kafka-topics --create --topic demo-2-distributed --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
```
### Connectors are on  confluent.io/hub

or google it   e.g.
dynamodb kafka connect

Landoop has included a number of connectors

In Landoo UI 127.0.0.1:3030 go to connectors **New**.  Chose connector type __File__ .

this opens the config window for file connector

NB! in distributed mode one do not need a worker properties file

Paste to the UI textarea the configuration at source/demo-2/file-stream-demo-distributed.properties
Remove all commented lines. Hit Create button. 

Connect to the docker which runs the landoop kafka-server 
```
docker ps
docker exec -ti $container_id bash
```
create a file where some text data wil be written
```
cd /
touch demo-file.txt
echo mama > demo-file.txt
echo papa >> demo-file.txt
```
check in UI in demo-2-distributed topic   two json messages 

Test consuming messages  from demo-2-distributed topic 

```
docker run --rm -it  --net=host landoop/fast-data-dev:latest bash
#in docker image
 kafka-console-consumer --topic demo-2-distributed --from-beginning --bootstrap-server 127.0.0.1:9092
```
Write some new lines lines to  demo-file.txt  on kafka-cluster docker  to observe new messages in  kafka-console-consumer

#### twitter source connector demo
`source/demo-3/source-twitter-distributed.properties`

This demo worked with kafka-cluster based on v3.3
based on https://github.com/Eneco/kafka-connect-twitter

Necessary to setup up an app credentials at developer.twitter.com
Using the existing app kafka_connect_szi

```
docker run --rm -it  --net=host landoop/fast-data-dev:latest bash

kafka-topics --create --topic demo-3-twitter --partitions 3 --
replication-factor 1 --zookeeper 127.0.0.1:2181

kafka-console-consumer --topic demo-3-twitter --bootstrap-server 127.0.0.1:9092
```
in UI kafka connectrors **New**  Twitter source
Paste the config from demo-3/source-twitter-distributed.properties  

In the console-consumer observe twitter messages 
In UI  Topics you can see the structure of tweet schema and values
In UI connectors you can cause the connector 

## Ch6 Kafka Connect Sinks
The same Connect Cluster can be used both to sources and sinks

Instructions for sinks demos are in kafka-connect-tutorial-sinks.sh

`sink/demo-elasticsearch`
```
docker-compose up kafka-cluster elasticsearch postgres
```
goto  http://127.0.0.1:9200

in Landoop UI crete new connector  Confluent ElaststicSearch Sink using sink-elastic-twitter-distributed.properties 

Check data at dejavu plugin
http://127.0.0.1:9200/_plugin/dejavu/

Once the sink plugin is enabled it pull data from kafka topic to the sink
put a query for only retweets and high friens from jsons in sink/demo-elasticsearch

### kafka connect rest api

rest api examples inclusding how to update connector how to update connector configuration
https://docs.confluent.io/3.2.0/connect/managing.html#common-rest-examples

seee demo rest calls in `demo-rest-api.sh`
```
curl -s 127.0.0.1:8083/connector-plugins | jq
curl localhost:8083/connectors
curl -s 127.0.0.1:8083/connectors/source-twitter-distributed/tasks | jq
curl -s 127.0.0.1:8083/connectors/source-twitter-distributed/status | jq
curl -s -X PUT 127.0.0.1:8083/connectors/source-twitter-distributed/resume
curl -s -X PUT 127.0.0.1:8083/connectors/source-twitter-distributed/pause
curl -s -X DELETE 127.0.0.1:8083/connectors/file-stream-demo-distributed

#### 9) Create a new Connector
curl -s -X POST -H "Content-Type: application/json" --data '{"name": "file-stream-demo-distributed", "config":{"connector.class":"org.apache.kafka.connect.file.FileStreamSourceConnector","key.converter.schemas.enable":"true","file":"demo-file.txt","tasks.max":"1","value.converter.schemas.enable":"true","name":"file-stream-demo-distributed","topic":"demo-2-distributed","value.converter":"org.apache.kafka.connect.json.JsonConverter","key.converter":"org.apache.kafka.connect.json.JsonConverter"}}' 

###### 10) Update Connector configuration
curl -s -X PUT -H "Content-Type: application/json" --data '{"connector.class":"org.apache.kafka.connect.file.FileStreamSourceConnector","key.converter.schemas.enable":"true","file":"demo-file.txt","tasks.max":"2","value.converter.schemas.enable":"true","name":"file-stream-demo-distributed","topic":"demo-2-distributed","value.converter":"org.apache.kafka.connect.json.JsonConverter","key.converter":"org.apache.kafka.connect.json.JsonConverter"}' 127.0.0.1:8083/connectors/file-stream-demo-distributed/config | jq

```

#### demo sink postgres
jdbc sink connector
https://docs.confluent.io/3.2.0/connect/connect-jdbc/docs/sink_connector.html#quickstart
landoo IU new jdbc sink connector with properties file from demo-postgres folder
connect to a postgres in docker (mapped to a local standard port) using psql or Sqlelectron client.
```sql
select count(*) from "public"."demo-3-twitter" limit 10;
```
if the source is running the count of messages in the table will be increasing

## Ch7 writing kafka connector GitHubSourceConnector
#### Plan
* * Dependencies
* ConfigDef
* Connector
* Schema & Struct
* Source Partition & Source Offsets
* Task

steph wrote a connector for education pupsposes connector source : https://github.com/simplesteph/kafka-connect-github-source/tree/v1.1

Checkout it to `~/repos/_t/maarek/kafka-connect-github-source`

Download it to local dir : `clearn/stream/kafka/kafka-connect-maarek/szi/ch8-writeconnector/kafka-connect-github-source-1.1`

Connector task: create a source  stream of issues and pull requests from a repository of our choice
The stream will pick up any update to any issues,  will use a Rest Client to Query that API

github api:
https://developer.github.com/v3/issues/#list-issues-for-a-repository

kubernetes repo used as source example https://github.com/kubernetes/kubernetes

Example of using api requests
.

```bash
## get list of open issues
curl -s -H "Accept: application/vnd.github.v3+json" -X GET https://api.github.com/repos/kubernetes/kubernetes/issues | jq

## get list of closed issues since a cetain date
curl -s -H "Accept: application/vnd.github.v3+json" -X GET https://api.github.com/repos/kubernetes/kubernetes/issues?state=closed&since=2017-01-01T00:00:00Z | jq
```

kafka connect maven artifact:
https://github.com/jcustenborder/kafka-connect-archtype
Intellij create new maven project from maven artifact id:
course maven artifact:
**groupId: io.confluent.maven,  artifact: kafka-connect-quickstart version: 0.10.0.0**

latest jcusterborder params

```maven
mvn archetype:generate \
    -DarchetypeGroupId=com.github.jcustenborder.kafka.connect \
    -DarchetypeArtifactId=kafka-connect-quickstart \
    -DarchetypeVersion=2.4.0
```

#### Create a new project for connector
Intellij: new Project: maven : create from archetype : Add archetype

* specify (not the latest from jcusterborder)
	* GroupId :  io.confluent.maven
	* ArtifactId, kafka-connect-quickstart
	* Version  0.10.0.0
* in the list of archetype observe the newly added archetype
* press Next
* specify:
	* name: tutorial-connect-github-project
 	* location: `~/repos/clearn/stream/kafka/kafka-connect-maarek/szi/ch8-writeconnector/tutorial-connect-github-project`
	* own groupId: org.szi.kafka
	* artifact: tutorial-connect-github-project

The intellij project is created and the  build  on generated pom.xml is launched. 
Check the generated pom.xml  modifiy kafka version to *0.10.2.0* or  *2.4.0*

For the source  connector you need to modify under src classes:  MySourceConnector,  MySourceConnectorConfig,  MySourceTask, VersionUtil


#### Work through the maarelk's project source in kafka-connect-github-source-1.1
Copy classes from maareks's project kafka-connect-github-source-1.1  to your project: tutorial-connect-github-project 

* GitHubSourceConnectorConfig
	* define the connnector config  with 2 fields having validators
	* check  the corresponding test

* GitHubSourceconnector
	* main connector class 	which has a bunch of boilerplate methods to implement
```
		* public String version()
		* start(Map<String,String> map)
		* public Class<? extends Task> taskClass ()
		*  public List<Map<String,String>>  taskConfigs(int i),
		* stop()  
		*  publicConfigDev config()
```
The most interesting is taskConfigs()

`config/GitHubSourceConnectorExample.properties`    shows a reference config for a source connector

org.szi.kafka.GitHubSourceConnectorTest  is a test for Connect class. 

#### Write a schema

The Url https://developer.github.com/v3/issues/#list-issues-for-a-repository
shows a structure of a reponses and helps to define a schema

`GitHubSchemas` shows selected fields for different schema

In `GitHubSchemas`  observe  how `VALUE_SCHEMA` contains  `USER_SCHEMA`  and `PR_SCHEMA`. 

KEY_SCHEMA and VALUE_SCHEMA are defined and will be used to convert responses from GitHubAPI  with http client. 

###### Data Model

Model POJOs are in the `org.szi.kafka.model` package
* `User` is a big Entity and has  method `fromJson(JSONObject jsonObject)`
* `Issue` is a biggest Entity,  has fields for `User` and `PullRequest`
check test `org.szi.kafka.model.UserTest`  and `IssueTest`

Once the project mvn compiles and packages,  run

`./run.sh`  to build and start a docker with standalone deployed connector . check its source as well as a Dockerfile . 

###### GitHubApi  Http client
Uses unirest  library
class `GitHubAPIHttpClient`
Important methods:
	*  getNextIssues()  //makes a rest calll and interprets response with recursive calls
	*  getNextIssuesAPI() //actual http call with unirest 
	*  constructUrl()  //constructs a string for rest endpoint with parameters

Check test `GitHubAPIHttpClientTest` which I have added

NB!  the dependency com.mashape.unirest.unirest-java:v1.4.9 is superceded by com.konghq.unirest-java:v3.10.00

######  Source Partitions, Source Offset
Confluent connector is stateless so its state is stored in kafka specific topics

Source partition and Source offset stores where your connector tracks which data it processes
*source offset* is about the timestamp of last read data
*Source partition*: is the set of params values which form a unique stream of data

e.g.  for githubConnector :
* source partition : { github_owner, gihub_repo}
* source offset :  { last_called_message_timestamp}

class `GitHubSourceTask` has:
```
* method  `private Map<String, String> sourcePartition()`
* method ` private Map<String, String> sourceOffset(Instant updatedAt) `
```
those are used in  `private SourceRecord generateSourceRecord(Issue issue)`

######  Source Task

class `GitHubSourceTask`   this class does the actual job  , implements:
```
* public String version()
* public void start(Map<String,String> map)
	* initialise source offset and source partition 	
* public List<SourceRecord> poll()  throws InterruptedException  // main method
	* calls httpClient,  creates an Array of source records  
	* loops of issues and foreach creates a SourceRecord  		
* public void stop()
```
class `GitHubSourceTaskTest`
tests the httpClient call and getting a list of issues

###  Deploy connector
##### Deploy connnector on standalone mode
```bash
mvn clean package
./run.sh
```
which does 
```
##include project dependency jars in $ClassPath
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"

##build docker image using Dockerfile in the project folder
docker build . -t simplesteph/kafka-connect-source-github:1.0

#run this docker image with offsets mounted as volume
docker run --net=host --rm -t \
 -v /tmp/offsets:/kafka-connect-source-github/offsets \
 simplesteph/kafka-connect-source-github:1.0
##NB you need to have kafka cluster running
## start it by running docker-compose up in the project folder
```
make sure  that config folder  has  GitHubSourceConnectorExample.properties and worker.properties

Observe in logs that connector runs

open kafdrop  at  http://localhost:9000   and observe messages in  `github-issues` kafka topic   written by connector.


##### Deploy connnector on landoop cluster

start a landoop docker image with your connector mapped as a volume

```
docker run -it --rm -p 2181:2181 -p 3030:3030 -p 8081:8081 -p 8082:8082 -p 8083:8083 -p 9092:9092 -e ADV_HOST=127.0.0.1 -e RUNTESTS=0 -v ~/repos/clearn/stream/kafka/kafka-connect-maarek/szi/ch8-writeconnector/tutorial-connect-github-project/target/tutorial-connect-github-project-1.0-SNAPSHOT-package/share/java/tutorial-connect-github-project:/connectors/GitHub landoop/fast-data-dev
```
goto landoop ui  at http://localhost:3030   click on browse logs -> connect-distributed.log
goto connectors New : GitHubSourceConnector

and create config based on `config/GitHubSourceConnectorExample.properties`

Observe  the working connector, go to check messages under the kafka topic github-issues

https://www.confluent.io/product/connectors

advanced docs
https://www.confluent.io/wp-content/uploads/Partner-Dev-Guide-for-Kafka-Connect.pdf?x18424

## ch9 advanced concepts

install local kafka  in /opt/sft
`config/connect-distributed.properties`
copy this file to /opt/sft/kafka/config
cd /opt/sft/kafka   and launch connect-distributed
```
bin/connect-distributed.sh config/connect-distributed.properties
#check connect cluster running without any connectors
curl -s http://localhost:8084 | jq
```
download postgres source connector from debezium.io
`mv debezium-connector-postgres /opt/sft/kafka_2.12-2.5.0/connectors/`
`curl -s http://localhost:8084/connector-plugins/`  shows that debezium connector is available
`curl http://localhost:8085/connector-plugins | jq` shows a 2nd worker in connet-cluster  available

optionally run a landoop ui (https://github.com/lensesio/kafka-connect-ui)
```
docker run --net=host --rm -it  -e "CONNECT_URL=http://127.0.0.1:8084" landoop/kafka-connect-ui
```
landooup ui is at http://localhost:8000    check  ui  of older cluster at localhost:3030  and observe system topics  for your 2nd  kafka-connect cluster














