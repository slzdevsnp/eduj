#!/bin/bash

#mvn -Dmaven.test.skip=true clean package

docker build -t kafka-connect-sz-connectors-local \
  -f Dockerfile .