https://spring.io/guides/gs/consuming-rest/

ij project: /home/zimine/repos/clearn/spring/springio_guides/guide_parent


We will consume a restful web service to to retrieve a random Spring Boot quotation
at https://gturnquist-quoters.cfapps.io/api/random.

The pom.xml has same dependencies as Building a RESTful Web Service guide

## Initial Setup

`git clone https://github.com/spring-guides/gs-consuming-rest.git`


In Spring Initializr  the only needed dependency is *Web*. 

## Classes

### create a Model Quote object
### crate a Value object   
Those two are to match a json structure returned from the quotation service

## create class ConsumingRestApplication
It contains a @Bean `RestTemplate`  and a @Bean `CommandLineRunner`

## run the app
`java -jar ./gs-consuming-rest/target/gs-consuming-rest-0.0.1-SNAPSHOT.jar`
Observe that on the command line you see 1 random quote fetched

