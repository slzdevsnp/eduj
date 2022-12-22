#!/bin/bash

repo="docker-spring-boot-postgres_bld"
tag="latest"
app="docker-spring-boot-postgres_bld"

docker image rm  ${repo}:${tag}
docker build -t ${repo}:${tag} --build-arg JAR_FILE=target/${app}-*.jar   -f ./Dockerfile_build_docker_script  .
