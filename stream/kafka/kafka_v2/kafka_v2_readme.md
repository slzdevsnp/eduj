course_notes
============
practice setup small cluster with1 broker ,1 zookeeper

ch kafka theory 101
--------------------
kafka cluster nodes called brokers 

partitions of all topics distributed across cluster nodes
partitions replicated  to multiple brokers

kafka cluster
    broker
        topic
            partition 0
            partition 1

101 theory  (17:30)
topics:

topic == a stream of functionaly the same type of data
    <==> table in db  id is its name
    split in partitions
       partition is ordered, incremental id  == offset
       message 0 has offset 0
       offsets are per partitions
       order is guaranteed only within a partition
       data default retention = 1 week
       once data in partition it can be changed (immutable)
              data is assigned randomly to a partition unless a key is provided

    
    topic can be regular (messages can expire) or compacted (records don't expire based on time or space bounds. users delete messages)

 brokers:
    servers in cluster
    have numeric ID
    each broker contains certain topic partitions
    after connecting to any borker (a bootstrap broker) you are connected to the entire cluster
    good default number of brokers is 3  (can be 100)
    the multiple partitions of a topic are distributed across brokers

topic replication
    should have a replication factor  > 1 (usually between 2 and 3 i.e  2 or 3 partition replicas)
    at any time only one broker can be a leader for a given partition. only leader can receive and server data for partition
    => each partition has : 1 leader , multiple ISR (in-sync replica)
    zookeper assigns leaders and ISRs

producers:
    sends data in round-robin to multiple brokers
    sends modes:
        ack=0     : producer won't wait for acknowledge (data loss is possible) (Dangerous)
        ack=1     : default. producer wil wait for the leader acknowledgment (limited data loss)
        ack=all   : leader + replicas acknowledgment (no data loss)

    message keys:
        producer can chose to send a key with the message(string, number, etc)
        if key==null, data is sent round robin
        if a key is sent then all messages for that key will always to to the same partition
        key e.g. = track_id
        same key always go to same partition
        to which partition a key is sent is determined by  the key's hashing 

consumers:
    consumer read data from a topic
    consumers know which broker to read from
    if broker is in failre, consumer know how to recover
    data is read in order **within** each partition
    consumers read  data in consumer groups
    usually you have as many consumers as the number of partitions to allow parallel processing
     
    consumer offsets  (aka like checkpointing)
    __consumer_offsets
    when a message is processed a __consumer_offsets  topic is updated
    if consumer dies, we are able to read back from where  it left

    consumers choose whn to commit offsets
    3 delivery semantics
        at most once:
            offsets committed as soon as the message is rcvd  (danger to lose the msg)
        at least once: (usually preferred)
            offset are committed after  the message is processed 
                (can have duplicate processing of messages. ) make sure  processing is idempotent. i.e. duplicate 
                processng does not impact your system.  Idepotent consumer
        exactly once:
             for kafka => kafka workflows using kafka streams api       

kafka broker discovery:
    only one connection to the bootstrap broker
    each broker knows about all brokers, topics and partitions (metadata)

zookeeper
    manages brokers, keeps list of them
    makes leader election for partition
    sends notifications to kafka in case f changes (e.g. new topic, broker)
    zookper by design operates with odd number of servers (3,5,..)
    zoookeper has a leader (handles writes)  the rest of followers (handle reads)


kafka guarantees
    messages are appended to a topic-partition in the order the  are sent
    consumers read messages in the order stored in a topic partition
    with replicate factor N, produces and consumers can tolerate up to N-1 brokers being down
    so replicion factorof 3 is good idea:
        alows 1 broker to b taken down for maintenance
        allows for another boker to be ke down unexpectedly
    the same key always go to the same partition



APIs:
    producer  api (publish streams of records)
    consumer api (subscribes and process streams of records)
    connector api (executes a reusable producer and consumer apis to link topics to existing apps) from v 0.9
    streams api (converts input streams to output and produces a result)    from v 0.10


                                | kafka     |    
                                |broker 1   |
source systems -> producers ->  |broker 2   | -> consumers  -> target systems
                                |...        |

                                    |
                                |zookeeper  |       

section 5 installing & starting kafka
------------------------
install and setup kafka in the path
NB! kafka works only java8 as of now
http://kafka.apache.org/downloads
    binary
    choose scala 2.12  tgz
    at 19.10.2019  v 2.3.0  gets a mirror link
    download  kafka_2.12-2.3.0.tgz
    tar zxvf into /opt/sft
create data folders
mkdir /Users/zimine/sdata/kfd
cd kdf ; mkdir zookeeper ; mkdir kafka

in /opt/sft/kafka_2.12-2.3.0/config/zookeeper.properties  modify dataDir to
dataDir=/Users/zimine/sdata/kfd/zookeeper

in /opt/sft/kafka_2.12-2.3.0/config/server.properties modify log.dirs to 
log.dirs=/Users/zimine/sdata/kfd/kafka

add $KAFKA/bin to $PATH in .bash_profile
export KAFKA_HOME=/opt/sft/kafka_2.12-2.3.0
export PATH=$PATH:$KAFKA/bin

#start  zookeeper  daemon process  (starts on port 2181)  must ran always
zookeeper-server-start.sh /opt/sft/kafka_2.12-2.3.0/config/zookeeper.properties

#start  kafka server daemon process
kafka-server-start.sh /opt/sft/kafka_2.12-2.3.0/config/server.properties

the data dir under kfd  start to get filled with files 

section 6 cli
------------------------

kafka cli kafka-cheat-sheet.md
https://gist.github.com/ursuad/e5b8542024a15e4db601f34906b30bb5


kafka-topics

depr
kafka-topics.sh --zookeeper localhost:2181 

#replication factor can not exceed number of brokers
kafka-topics.sh --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3 --replication-factor 1
kafka-topics.sh --bootstrap-server localhost:9092 --list
kafka-topics.sh --bootstrap-server localhost:9092 --topic first_topic --describe
 #shows topics partitions
kafka-topics.sh --bootstrap-server localhost:9092 --topic second_topic --delete

kafka-console-producer

kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic first_topic
    >type msg  and press enter,  repeat n times. 
    Ctrl-C  to get out
 zimine$ kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic first_topic  --producer-property acks=all
 > another msg

## this will create new_topic behind with 1 partition and 1 replication factor
kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic new_topic  

so always first create a topic with kafka-topics befond sending messages to it
 in server.properties  change the param num.partitions to a new value


kafka-console-consumer
#starts to listen any new incoming messages  to first_topic
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic

#to process all the messages in topic  (observe that order is within partitions)
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning

# with 3 consumers each will be processing next message (we have 3 partitions in first_topic)
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-app
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-app
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-app


#if you kill 1 consumer, the first consumer will be rcving 2 msgs and the 2nd consumer 1 msg

#--from-beginning flag with the group option will set message offset to the last msg
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-second-app --from-beginning

#only newer messages will be processed

kafka-consumer-groups

kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --list
#see details in group  my-second-app
kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --describe --group my-second-app
kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --describe --group my-first-app
    #pay attention to CURRENT-OFFSET and LOG-END-OFFSET

if LAG is > 0  than this group has lagging messages,   relaunch a console-consumer with this group to process these lagging messages 


when a consumer with a given group is launched,  if you try to kafka-consumer-groups --describe,
it wil show you the consumer id the host info etc.  which is useful 

resetting offsets in a group
kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --group my-first-app --topic first_topic --reset-offsets --to-earliest --execute

##replay all messages in this topic for this group
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-app

##shift backwards by two msg per partition
kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --group my-first-app --topic first_topic --reset-offsets --shift-by -2 --execute

#now kafka-console-consumer.sh  will process only 6 messages 2 * 3 partitions


##producer with keys

###ui tools
kafka manager    https://github.com/yahoo/kafka-manager
http://www.kafkatool.com/

kafkacat
https://github.com/edenhill/kafkacat)
#tutorial
https://medium.com/@coderunner/debugging-with-kafkacat-df7851d21968


section 7 kafka java programming 101  (sun 10:49 - 13:00)
------------------------

we will create a kafka project
maven, projec sdk is java 8

google   kafka maven to add dependencies

kafka documentation
https://kafka.apache.org/documentation/

ref project:  part2/kafka-basics

tutorial1.ProducerDemo  #basic sending of 1 record
tutorial1.ProducerDemoWithCallback  #sends 10 msgs with a callback used to log metadata
tutorial1.ProducerDemoKeys #add keys to messages 

cont 10:49

tutorial1.ConsumerDemo
tutorial1.ConsumerDemoGroups
      if more then 1 consumers with the same group, the group is rebalanced to connect consumer instances with distinct partitions 
      if 3 ConsumerDemoGroups processes are launched. then each is rebalanced to read from 1 of 3 partitions
      if 1 ConsumerDemoGroups process is terminated, the two other process will detect it and rebalance again

tutorial1.ConsumerDemoWithThread
     implement consumer with thread,
     gracefull shutdown if exit or stop signal are received
        run it with ProducerDemoWithCallBack

tutorial1.ConsumerDemoAssignSeek  (based on ConsumerDemo)
    seek specific messages from a specified  offset


kafka bi-direction compatibility
    older client (v1 ) can alk to newer broker (2.0) AND  newer client (2.0 ) can talk to older broker (1.1)
    => always use the latest client libarary version

configure producer: https://kafka.apache.org/documentation/#producerconfigs
configure consumers:  https://kafka.apache.org/documentation/#consumerconfigs

NB! producing  messagins with key ensures that same keys go to the same partition
NB! adding a group.id  allows consumers with that group to resume at the right offset
NB! multiple consumers with the same group.id will read from distinct partitions


section 8,  kafka real world project  13:56   s 9 19:23  s 10  03:00
breaks for 2, 3 hours
------------------------
get tweets from tweeter, put them into elastic search

refs:
Twitter Java Client: https://github.com/twitter/hbc
Twitter API Credentials: https://developer.twitter.com/
elastic search consumer
java client
https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.4/java-rest-high.html
elastic search setup
https://www.elastic.co/guide/en/elasticsearch/reference/current/setup.html
OR https://bonsai.io/


getting twitter developer account.  
start from Apply button  on https://developer.twitter.com
once done, click on create an app 
created twitter app  kafka_v2_learn
under keys and tokens
generate access token
save  consumer api keys and access tokens in keeweb

goto github twitter java client

create a new idea project   unde sz  kafka-producer-twitter

tutorial2.TwitterProducer  (connect to twitter and gets tweets with specified terms)
    1. need to create a topic  
    kafka-topics.sh --bootstrap-server localhost:9092 --topic twitter_tweets --create --partitions 6 --replication-factor 1
    1.1 launch a cli consumer
    kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic twitter_tweets
    #put a following term to kafka
    send your own tweet to find it in kafka producer  log and in kafka cli consumer

acks=0  most performant, because broker does not notify producer
acks=1  == kafka 2 default,  is only leader sends notification 
        (but replication is not guaranteed.
        if leader broker goes offline, but replicas haven't replicated data, we have a data loss)
acks=0  leader and all replicaas sends ack (adds latency)
in.insync.replicas = 2
 (if 3 brokers, replication factor 3, exceptions if > 1 broker down)
#the safest setup
(acks=alll, replication factor=3, min.insync.replicas=2)

===producer retries===
transient failures. developers must handle data exceptions to prevent the data loss
retries
    default:  0 for kafka <= 2.0
    defaults to 2147483647 for kafka >= 2.1
retry.backoff.ms  = 100 ms
producer timeout  KIP-91 kafka 2.1
delivery.timeouts.ms = 120000 ms == 2 mins

==idempotent producer===
    the prob: producer can introduce duplicate messages in kafka due
    to network errors
    idempotent producer sends a mmsg with a MsgRequestId
    which allows broker to detect duplicates 
    idempotent producers are great to guarantee a stable and safe pipeline
    max.in.flight.requests=5 (kafka >= 1.0)
producerProps.put("enable.idempotence",true)

##set propertiesor  for safe producer

===message compression NB !!
"compression.type" 'none' default,  'gzip', 'lz4', 'snappy'
https://blog.cloudflare.com/squeezing-the-firehose/

comporession ratio up to 4x
better througput, 
better network latency
better disk utilization
high cpu usage 
consider testing snappy or lz4 

==Producer batching===
in.flight.requests=5 means  up to 5 messages individually sent at the same time
smart batching allows kafka increase throughput, while maitaining very low latency
    batches have lower compression ratio so better efficiency
    linger.ms  : n of ms a producer wating before sending a batch (dflt 0)
    i.e put  linger.ms=5,  we increase changes of messages to be sent in batch
    if batch.size is full befor ethe end of linger.ms perido it will be sent right away 
batch.size == max number of bytes in a batch dflt = 16 kb
increasing to 32 kb, 64 kb
batch is allocated per partition

use  Kafka Producer Metrics to see averages on the incoming stream
run TwetterProducer with high throughput config on popular terms


===producer default partition and how keys ara hashed ==
keys hashed with "murmur2" algo
do not change it.. 
targetPartition = Utils.abs(Utils.murmur2(record.key())) % numPartitions;
    the same key will go to the same partition, 
    adding partitions to a topic alters the formula

==max.block.ms & buffer.memory
if the producer produces faster than the broker can tak, records buffered in memory
dflt: buffer.memory=3355432 (32 MB)
if buffer isfull than  .send() method start to block (won't return immediately)
max.block.ms=60000  60 secs.  the time the .send() will block until throwing exception.     

s10 elastic search  consumer
------------
its a db
https://www.elastic.co/downloads/elasticsearch
OR
bonsai.io  == hosted elastic search
created  free poc cluster in 
saved credentials in access keys

for local elastic installation use
https://insomnia.rest  client
https://www.getpostman.com/   rest client

#doc on elastic search
https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started.html

GET /_cluster/health
GET /_cat/nodes

creating an index
PUT /twitter

GET /_cat/indices?v

# store a document
PUT /twitter/tweets/11
{
"course":"kafka for begs",
"instructor":"Marek",
"module":"ElasticSearch"
}

#get a document
GET /twitter/tweets/11

#delete a document
DELETE /twitter/tweets/11

#delete an index
DELETE /twitter

## create module kafka-consumer-elasticsearch
tutorial3.ElasticSearchConsumer 
    first run inserts a dummy json in ES
    second run to insert crude tweets in ES
        verify in ES with GET /twitter/tweets/i3qh620BkjVcihM681fj

== delivery semantics 
at most once
    offsets commited as soon as the message batch is rcvd
    if processing goes wrong, the message will be lost
at least once (dflt behavior)
    offsets are commited after the message is processed
    this can result in duplicate processing of messages,  so make sure your processing is
    idempotent
exactly-once :  only for kafka to kafka workflow using kafka streams

==consumer offset commit strategies
1. enable.auto.commit = true & synchronous processing of batches
    offsets commited automatically for your at regular interval auto.commit.interval.ms=5000

==consumer offset reset behavior
auto.offset.reset = latest|earliest|none
offset.retention.minutes


==replaying data
reset offsets
kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --group kafka-demo-elastic --reset-offsets --topic twitter_tweets --execute --to-earliest
kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --group kafka-demo-elastic --describe

==consumer heartbeat thread
if consumers change,  the partition list is rebalanced
session.timeout.ms (10000  ms dflt)
heartbeat.interval.ms (3000 ms dflt)

max.poll.interval.ms (default 5 minutes) # max amount of time between two .poll() calls before
declaring the consumer is dead 


s11 === kafka ecosystem and real world arch

==Kafka connect
https://www.confluent.io/hub/
source connectors
sink connectors

==kafka connect twitter
search https://www.confluent.io/connectors/  for tweeter

https://github.com/jcustenborder/kafka-connect-twitter

add a dir  kafka-connect,  download jars from the git repo releases
todo: do it later 

==kafka streams
!NB   in tutorial4.StreamFilteTweets   correct json processing with try extractUserFollowersInTweet

#create a new topic
kafka-topics.sh --bootstrap-server localhost:9092 --topic important_tweets --create --partitions 3 --replication-factor 1
## cli consumr to listen to important tweets
# run TwitterProduer and tutorial4.StreamsFilterTweets
zimine$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic important_tweets

==Schema Registry
    need to validate incoming data for an expected format 
    Confluent Schema Registry
    and Apache Avtro as the data format (better than json, has a learning curve)
    Free and opensource
== which api to use
https://medium.com/@stephane.maarek/the-kafka-api-battle-producer-vs-consumer-vs-kafka-connect-vs-kafka-streams-vs-ksql-ef584274c1e


s 13  real world insights
-----------------------
how to choose number of partitions
each partition  throughput  of mew MBs/s

more partitions == better parallelism, better throughput, more consumers to read
ability to leverage more brokers 
   -> more elections from zookeepr, more files opened on Kafka

  guidelines
  partitions per topic
     small cluster < 6 brokers  2 x # brokers
     big cluser > 12 brokers    1 x * #brokers
    adjust for number of consumers to run in parallel 
    adjust for producer throughput

    test!  every kafka cluster will have different performance 

!! nb useful
case study movieFlix
case study  getTaxi
case study mySocialMedia
case study MyBank
case study Big Data Ingestion
case study  logging and metrics aggregation



    replication factor. 
    should be at least 2, usually 3, maximum 4 
    guideline  set it to 3 to get started 
    never set it to 1 in production

    commonly accepted, broker should not hold 2K to 4K partitions  across all tppics

    a kafka cluster should have a max of 20K partitions 

