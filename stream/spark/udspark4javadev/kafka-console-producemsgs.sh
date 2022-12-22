#!/bin/bash

topic_name=viewrecords

function print_help(){
        echo  usage: $0 --topic=mytopic --broker=localhost:9092 --datadir=/tmp/data --filetype=json
}


##### main ######
for ARG in "$@"
do
        KEY=$(echo $ARG | cut -f1 -d=)
        VAL=$(echo $ARG | cut -f2 -d=)

        case "$KEY" in
                --topic) topic=${VAL} ;;
                --broker) broker=${VAL} ;;
                --datadir) datadir=${VAL} ;;
                --filetype) filetype=${VAL} ;;
                *)              
        esac
done

if [ "$ARG" = "" ] 
then
        print_help
        exit 0
fi
## biz logic

for f in ${datadir}/*${filetype}
	do
		echo "kafka-console-producer.sh --broker-list ${broker} --topic ${topic} < ${f}" 
		kafka-console-producer.sh --broker-list ${broker} --topic ${topic} < ${f} 
done
