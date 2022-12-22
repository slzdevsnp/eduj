#!/bin/bash

function print_help(){
        echo  usage: $0 --topics=topic1,topic2 --broker=localhost:9092 --lastfirst=-1
}


##### main ######
for ARG in "$@"
do
        KEY=$(echo $ARG | cut -f1 -d=)
        VAL=$(echo $ARG | cut -f2 -d=)

        case "$KEY" in
                --topics) topics=${VAL} ;;
                --broker) broker=${VAL} ;;
                --lastfirst) lastfirst=${VAL} ;;
                *)              
        esac
done

if [ "$ARG" = "" ] 
then
        print_help
        exit 0
fi
## biz logic
#split a string of topics into array
IFS=',' read -ra topic_array <<< "$topics"

for t in "${topic_array[@]}"
do
	#echo kafka-run-class.sh kafka.tools.GetOffsetShell -broker-list ${broker} --topic ${t} --time ${lastfirst} 
	kafka-run-class.sh kafka.tools.GetOffsetShell -broker-list ${broker} --topic ${t} --time ${lastfirst} 
done
