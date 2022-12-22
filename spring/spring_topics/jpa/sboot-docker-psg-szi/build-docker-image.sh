#!/bin/bash

app="sbdocker-psg"
repo=${app}
tag="latest"

docker image rm  ${repo}:${tag}
docker build -t ${repo}:${tag}  --build-arg JAR_FILE=target/${app}-*.jar   -f Dockerfile_build_docker_script  .
