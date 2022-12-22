rabbitmq_docker

resources:
https://codeburst.io/get-started-with-rabbitmq-on-docker-4428d7f6e46b?gi=c8d5c9b9c51c


### pull the docker image
```
docker pull rabbitmq:3-management
```

### run the docker image
5672 is used for client connections,  15672 is for rabbitmq management website

--rm  : automatically remove container when it exits
-it  : run interactively

```
docker run --rm -it --hostname my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
``` 
this starts the container  with log console. Ctr-C  stops the container

http://localhost:15672 starts the web interface  guest:guest

https://www.rabbitmq.com/getstarted.html   is a page with official tutorials

