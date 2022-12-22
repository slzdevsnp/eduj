#!/bin/bash

docker run -it --rm \
 -p 2181:2181 -p 3030:3030 -p 8081:8081 -p 8082:8082 -p 8083:8083 -p 9092:9092 \
 -e ADV_HOST=127.0.0.1 -e RUNTESTS=0 \
 -v /home/zimine/repos/clearn/stream/kafka/kafka-connect-maarek/szi/ch7-write-source-connector/connector-github-issues/target/connector-github-issues-1.0-SNAPSHOT-package/share/java/connector-github-issues:/connectors \
 landoop/fast-data-dev:latest