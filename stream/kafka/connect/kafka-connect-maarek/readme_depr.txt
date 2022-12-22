
latest readme is in inkdrop  edu:kafka:kafkaConnectHand-on


====
setup kafka connect from twitter

kafka connectors from file, twitter

kafka connect sinks into elastic search, into postgresql


course does not cover avro.  

full markdown log is  in ~/repos/slzdevsnp/lac/logs/lrn/clearn/stream/kafka/

code download:  from https://courses.datacumulus.com/downloads/kafka-connect-09a

 
#demos

## Demo 1
/szi/ch4-connect-cluster  is a docker connect cluster

ch4-connect-cluste/docker-compose.yml

is based on landoop which has a number of components loaded + elasticsearch + postgres 

1. check instructions in kafka-connect-tutorials-sources.ch 
> docker-compose up kafka-cluster
after image is pulled and started goto landoop UI  
localhost:3030
it takes 3-5 minutes to get 1 connector running logs_broker

docker-compose_latest.yml  is a docker file with latest image for kafka-cluster
>docker-compose  -f docker-compose_latest.yml  up kafka-cluster 


### demo  source connector  standalone
`source/demo1`  has config for that connector 

#### demo source connector file distributed 


### ch8 writing your own connector 
grab code from 
https://github.com/simplesteph/kafka-connect-github-source/tree/v1.1
