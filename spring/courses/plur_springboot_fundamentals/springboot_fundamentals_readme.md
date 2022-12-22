
# Sring Boot Fundamentals

##### pluralsight author: Kesha Williams 1:38

### course code from github
https:gitlab.com/videolearning/spring-fundamentals

## short plan
* overview
* introducing spring boot
* bootstrapping a simple app
* accessing data with spring boot
* configuring a spring boot mvc app
* building a restfull web app
* building a graphql server app
* enabling actuators, metrics
* testing with spring boot


## 1 Course Overview 1m 47s

* Course Overview 1m 47s

## 2 Introducing Spring Boot and Its Benefits
 10m 2s

* Getting Started 1m 57s

* Learning Path 1m 18s

* Spring Boot Overview 2m 26s

* ! Features of Spring Boot 3m 32s
  * easier dependency management
  * spring boot cli (groovy scripts)
  * actuator (moniging spring boot app)

* Summary 46s

## 3 Bootstrapping a Simple Application

16m 41s

* Overview 38s
  * Trackzilla app

* Demo: Spring Initializr 5m 25s
    * http://start.spring.io
  
* Demo: Spring Boot CLI 3m 21s
  * on mac brew package for spring boot CLI
  * `spring init --dependencies=web,data-jpa projname`
  * `cli/`  app groovy scripts to launch spring through cli `spring run app.groovy`
  
* Demo: Auto Configuration 3m 8s
    * application.properties  debug logging
    * https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
  
* Spring Boot Annotations 36s

* Spring Boot Properties 1m 15s

* Spring Boot Profiles 1m 28s
  * applications-${profile}.properties

* Summary 45s

## 4 Accessing Data with Spring Boot and H2

12m 14s

* Overview 1m 1s

* H2 Database 1m 34s
  * h2 console http://localhost:8080/h2

* ORM with JPA 1m 49s

* Entities 49s

* Demo: H2 Integration 4m 52s

* Demo: H2 Console 1m 33s * Summary 32s

## 5 Configuring a Spring MVC Application with Spring Boot

12m 28s !(to review)

* Overview 1m 12s

* MVC Design Pattern 2m 47s

* Demo: MVC in Action 4m 57s

* Packaging and Deployment 1m 34s

* Demo: Uber Jar 1m 4s

* Summary 52s

## 6 Building a RESTful Web Application with Spring Boot

11m 57s

* Overview 59s

* REST Architecture 59s

* Demo: RESTful Web Application 3m 7s
* Demo: Testing with cURL 1m 4s  

* Response Formats 40s

* Exception Handling 1m 0s

* Demo: Exception Handling 3m 22s NB

* Summary 43s

## 7 Building a GraphQL Server with Spring Boot

15m 11s

* Overview 1m 4s
  * graphql is a querying language to query APIs

* GraphQL Overview 2m 17s

* GraphQL Dependencies 39s

* GraphQL Schemas

41s
* Demo: Schemas 2m 5s

* Query Operations 1m 7s

* Mutations 1m 34s

* Exceptions 1m 0s

* Demo: GraphiQL 4m 0s NB! http://localhost:8080/graphiql

* Summary 39s

## 8 Enabling Actuators, Metrics, and Health Indicators

6m 19s

* Overview 50s

* Actuator 2m 54s !NB http://localhost:8080/actuator

* Custom Endpoints 2m 6s

* Summary 27s

## 9 Testing with Spring Boot

11m 38s

* Overview 58s

* Testing Overview 29s

* Unit Testing 5m 17s

* ! Integration Tests 3m 25s
  * @RunWith(SpringRunner.class)
  * WebEnvironment.RANDOM_PORT

* Course Recap 1m 27s


## ch overview
spring boot is a spring with an easier config

case study: bug tracking app trackzilla 
* controller
* application service
* ticket service
* h2 database
 
prereqs: java8 mvn3 
prereq courses: { creating your fist spring boot app, spring the big picture, spring fundamentals, getting started with spring data jpa }

**spring boot**  helps to boot a spring app with a minimal configuration

spring components:
* spring mvc
* spring session
* spring data
* spring amqp
* spring security
* spring batch
  
Spring uses xml configuration which is verbose.

Later versions of spring introduced annotations to reduce the amount of configuration

Spring boot simplifies: 
* configurations
* managing java dependencies in a smart way.
* deployment (war files)

Spring boot features
* automatic configuration
  * saves time
  * configures app based on included libraries
  * maven dependencies configured automatically
* starter dependencies
  * spring-boot-starter-web (spring mvc rest,tomcat,jackson)
  * spring-boot-starter-test (junit, mockito,hamrest,spring-core,spring-test)
  * spring-boot-starter-data-jpa (jpa,hibernate,jdbc,..)
  * spring-boot-starter-thymeleaf
  
* command line interface
* actuator

Spring Boot CLI
* Groovy

Actuator
* monitor running app
* manage via http endpoints or JMX
* health status, metrics, loggers, audit events, http trace



