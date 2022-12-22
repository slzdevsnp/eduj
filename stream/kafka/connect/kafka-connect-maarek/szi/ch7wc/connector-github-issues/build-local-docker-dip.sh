#!/bin/bash


docker build -t kafka-connect-szi-local \
  -f Dockerfile-dip .