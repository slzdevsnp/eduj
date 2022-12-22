#!/bin/bash

function print_help(){
        echo  usage: $0 --topic=topic --broker=localhost:9092 --nmsg=1
}


##### main ######
for ARG in "$@"
do
        KEY=$(echo $ARG | cut -f1 -d=)
        VAL=$(echo $ARG | cut -f2 -d=)

        case "$KEY" in
                --topic) topic=${VAL} ;;
                --broker) broker=${VAL} ;;
                --nmsg) nmsg=${VAL} ;;
                *)              
        esac
done

if [ "$ARG" = "" ] 
then
        print_help
        exit 0
fi
## biz logic
timeoutms=1000
echo "kafka-console-consumer.sh --bootstrap-server ${broker} --topic ${topic} --from-beginning --timeout-ms ${timeoutms} | tail -n ${nmsg} | jq -r '.'"
kafka-console-consumer.sh --bootstrap-server ${broker} --topic ${topic} --from-beginning --timeout-ms ${timeoutms} | tail -n ${nmsg} | jq -r '.'