#!/bin/bash

btopic0=viewrecords
btopic1=recresults
btopic2=json_incoming

npartitions=1
repl_factor=1

bootsrv=localhost:9092

echo deleting topics..
kafka-topics.sh --bootstrap-server ${bootsrv}  --delete --topic ${btopic0}
kafka-topics.sh --bootstrap-server ${bootsrv}  --delete --topic ${btopic1}
kafka-topics.sh --bootstrap-server ${bootsrv}  --delete --topic ${btopic2}

echo creating topics..
kafka-topics.sh --bootstrap-server ${bootsrv}  --partitions ${npartitions} --replication-factor ${repl_factor}  --create --topic ${btopic0}
kafka-topics.sh --bootstrap-server ${bootsrv}  --partitions ${npartitions} --replication-factor ${repl_factor}  --create --topic ${btopic1}
kafka-topics.sh --bootstrap-server ${bootsrv}  --partitions ${npartitions} --replication-factor ${repl_factor}  --create --topic ${btopic2}

echo "now topics .."
kafka-topics.sh --bootstrap-server ${bootsrv} --list 


