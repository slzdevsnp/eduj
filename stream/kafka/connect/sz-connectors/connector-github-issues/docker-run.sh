#!/usr/bin/env bash

docker run --net=host --rm -t \
	    -v $(pwd)/offsets:/connector-github-issues/offsets \
           szi/connector-github-issues:1.0