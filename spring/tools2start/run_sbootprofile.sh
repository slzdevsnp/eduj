#!/bin/bash

function print_help(){
        echo  usage: $0  --profile=local --port=8080
}


##### main ######
for ARG in "$@"
do
        KEY=$(echo $ARG | cut -f1 -d=)
        VAL=$(echo $ARG | cut -f2 -d=)

        case "$KEY" in
                --profile) profile=${VAL} ;;
                --port) port=${VAL} ;;
                *)              
        esac
done

if [ "$ARG" = "" ] 
then
        print_help
        exit 0
fi
## biz logic

    
echo "expected to run this command from springboot app project top dir"

mvn spring-boot:run -Dspring-boot.run.profiles=${profile} \
 -Dspring-boot.run.jvmArguments=\'-Dserver.port=${port}\'