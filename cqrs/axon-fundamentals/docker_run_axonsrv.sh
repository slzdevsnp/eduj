#!/bin/sh

docker run -d --name axonserver \
	-p 8024:8024 -p 8124:8124 \
	-e AXONIQ_AXONSERVER_DEVMODE_ENABLED=true axoniq/axonserver 
