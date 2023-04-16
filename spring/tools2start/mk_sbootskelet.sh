#!/bin/bash

function print_help(){
        echo  usage: $0  --sbversion=3.0.4 --jversion=17 --deps=webflux,data-jpa --groupid=org.szi --artifactid=mymodule-app --modulename=mymodule --packagename=dev.szi.xyz --dirname=mymodule-app
}


##### main ######
for ARG in "$@"
do
        KEY=$(echo $ARG | cut -f1 -d=)
        VAL=$(echo $ARG | cut -f2 -d=)

        case "$KEY" in
                --sbversion) sbversion=${VAL} ;;
                --jversion) jversion=${VAL} ;;
                --deps) deps=${VAL} ;;
                --groupid) groupid=${VAL} ;;
                --artifactid) artifactid=${VAL} ;;
                --modulename) modulename=${VAL} ;;
                --packagename) packagename=${VAL} ;;
                --dirname)  dirname=${VAL} ;;
                *)              
        esac
done

if [ "$ARG" = "" ] 
then
        print_help
        exit 0
fi
## biz logic
## to show available dependencies
## spring init --list  

echo "creating skeleton springboot app based on passed params.."

spring init --build=maven \
 --packaging=jar \
 --boot-version=${sbversion} \
 --java-version=${jversion} \
 --dependencies=${deps} \
 --group-id=${groupid} \
 --artifact-id=${artifactid} \
 --package-name=${packagename} \
 --name=${modulename} \
 --force \
  ${dirname}

