# Spring Framework: Creating your fist spring boot application.

by pluralsight

## Plan

### Course Overview

* Course Overview

### Using Spring Boot to Create Applications

* Introduction

* What Is Spring Boot?

* Demo: Creating a Spring Boot App with Spring Initializr

* Demo: Importing and Setting up a Spring Boot App

* Demo: Overview of a Spring Boot App

* ! Demo: Creating a Spring Boot App with IntelliJ

* ! Demo: Using the Spring Boot CLI

* Understanding Spring Boot Starters
* Summary

### ch Building Apps with Spring Boot


* Introduction

* Application Overview

* !! ок Setting up the Database

*  Demo: Connecting to the Database
    * put jdbc url postgres:postgres (local osx psg) in resources/application.properties
* Demo: Creating JPA Models

*  Demo: Working with JPA Relationships

*  Demo: Working with Binary Data Types

*  Demo: Creating JPA Repositories
  * repositories java classes
*  Demo: Creating Spring API REST Controllers - Part 1

*  Demo: Creating Spring API REST Controllers - Part 2

*    Demo: Handling Serialization Issues and Running the App
  * postman, configure requests in postman, start with http://localhost:8080/api/v1/sessions/
  * cycling problem -> DTOs
  * handler property

* Summary

 
### ch Working with Spring Boot Config and Environment Needs

* Introduction

* How to Customize and Override Spring Boot
    * order of precedence
* Demo: Locating and Working with Config Files

* ! Demo: How to Change Database Connections

* ! Demo: How to Handle Different Environments

* ! Demo: Setting Properties with YAML ~~ (pick properties files or yaml files)

* ! Demo: How to Use Spring Properties in Code

* Demo: Overriding Spring Boot with Java Config

* Creating Your Own Auto-configuration Class
  * not very clear

* Summary


### Deploying Spring Boot Applications

* Introduction

* Spring Boot Containerless Architecture

* ! Demo: Switching out the Spring Boot Embedded Container

* ! Demo: Creating Executable JAR Deployments

* Common Cloud Supported Platforms

* Demo: Deploying Spring Boot to the Cloud

* What About WAR Deployments?

* ! Demo: Creating a Spring Boot  WAR Deployment
  
* Summary


## ch3 buidling 

#### setting up the db

```bash
cd szi/psql
git clone https://github.com/dlbunker/ps-first-spring-boot-app.git
```
instructions to set up the db 

https://github.com/dlbunker/ps-first-spring-boot-app/tree/master/database/postgresql

##osx16gb localst postgres installation

container approach
```
docker pull postgres:12

#creates and runs a container with a mounted volume whith host port 5433 mapped to container port 5432

docker run --name postgres12 -p 5433:5432   -e POSTGRES_PASSWORD=9lalala9  -v /Users/zimine/zgoogledrive/zrepos/slzdevsnp/clearn/rest/create_first_springboot_app/szi:/mnt/osx -d postgres:12
#
```
connect to container's bash

`docker exec -ti postgres12 bash `
```
#in the container
cd /mnt/osx/psql/ps-first-spring-boot-app/database/postgresql

psql -U postgres -c 'create database conference_app'

psql -U postgres -d conference_app -f create_tables.sql

psql -U postgres -d conference_app -f insert_data.sql
```

JDBC info for osx local postgres
```
JDBC URL: `jdbc:postgresql://localhost:5432/conference_app`

Username: `postgres`
Password: `postgres`
```


On osx in dbeaver configure a local connection to 
psg localhost:5432



