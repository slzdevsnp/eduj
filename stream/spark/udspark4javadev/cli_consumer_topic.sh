#!/bin/bash

topic_name=viewrecords

function print_help(){
        echo  usage: $0 --topic=mytopic --isfrombegin=false
}


##### main ######
for ARG in "$@"
do
        KEY=$(echo $ARG | cut -f1 -d=)
        VAL=$(echo $ARG | cut -f2 -d=)

        case "$KEY" in
                --topic) topic=${VAL} ;;
                --isfrombegin) isfrombegin=${VAL} ;;
                *)              
        esac
done

if [ "$ARG" = "" ] 
then
        print_help
        exit 0
fi
## biz logic
bootstrapsrv=localhost:9092

if [ "${isfrombegin}" = "true" ]
then
	echo "replaying all events from start offset and listening.."
	kafka-console-consumer.sh --bootstrap-server ${bootstrapsrv} --topic ${topic} --from-beginning 
else
 	echo "listening to new events.."	
	kafka-console-consumer.sh --bootstrap-server ${bootstrapsrv} --topic ${topic}  
fi

