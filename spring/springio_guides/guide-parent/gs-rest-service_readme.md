https://spring.io/guides/gs/rest-service/

ij project: /home/zimine/repos/clearn/spring/springio_guides/guide_parent


We build a service that accept HTTP GET  at http://localhost:8080/greeting


## Initial Setup

to skip the basics do 
`git clone https://github.com/spring-guides/gs-rest-service.git`

Goto spring Initializr to create the skeleton project with pom.xml from  https://start.spring.io
select:
* release: 2.3.7
* group : com.example
* artifact: rest-service
* package name: com.example

Dependencies:
* Spring Web

download  rest-service.zip

unzip it under ij project: /home/zimine/repos/clearn/spring/springio_guides/guide_parent

in the rest-service/pom.xml  point parent pot to guide-parent
```xml
	<parent>
		<groupId>com.example</groupId>
		<artifactId>guide-parent</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>
```

In dependencies you should have *spring-boot-starter-web* , *spring-boot-starter-test*  and also include  *lombok* .



## Classes
### create a ressource representation (model) class
`src/main/java/com/example/restservice/model/Greeting.java`

#### create a RestController  class `GreetingController`

## running
```
#compile
mvn clean package

#run
java -jar rest-service/target/rest-service-0.0.1-SNAPSHOT.jar
```

In the browser Access: http://localhost:8080/greeting

http://localhost:8080/greeting?name=Michael

or on the comand line
```
curl -X GET http://localhost:8080/greeting?name=Slava
```


## tdd
Write a class `GreetingControllerTests` with annotations @SpringBootTest , @AutoConfigureMockMvc

Pay attention how MockMvc RequestBuilders, ResultHandlers are imported.
