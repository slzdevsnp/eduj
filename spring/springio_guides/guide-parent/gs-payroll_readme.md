# Building REST services with Spring
https://spring.io/guides/tutorials/rest/

ref project top gthub repo: 
https://github.com/spring-projects/spring-hateoas-examples

timing:
20-12-24: 12:30 - 15:00  (what makes something RESTfull)
20-12-25  01:30 - 03:30  (till last testing)
NB! Ret ubiquitoous is not a standard per se, but an approach

Rest APIs provide means to build:
* backward compatible Apis
* evolvable APIs
* Scaleable services
* Securable services
* spectrum of stateless to stateful services

#### synopsys
We shall crate a simple payroll services that manages employees of a company. We'll store employee objects in (H2 in-memory) database, adn access them via JPA. Then we'll wrap that with the Spring MVC layer.

## Getting starting
in Spring Initializr add dependencies: Web, JPA,H2

* use for project name "payroll" , and generate it 
* unzip the downloaded zip  to ~/repos/clearn/spring/springio_guides/guide_parent
* rename the folder go gs-payroll
* modify gs-payroll/pom.xml  to point to a project's parent pom, update the dependencies in pom  as in other child projects

## JPA
in pom.xml the dependency is `<artifactId>spring-boot-starter-data-jpa</artifactId>`
### jpa entities
* create an @Entity *Employee*  class in package `com.example.payroll.model`
* use Lombok to write less code
We end up having a full entitty class like below:
```java
import lombok.*;
import ... ;
@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee {

    private @Id @GeneratedValue Long id;  //pk
    private String name; //attributes
    private String role;
}
```
Spring JPA will handle tedious DB interactions

### JPA repositories
Repositories are interfaces with methods to support CRUD operations with back end data store

*NB*: Besides JPA Spring has other repository implementations such as: MongoDB, SpringData GemFire, SpringData Cassandra.

Create a class *EmployeeRepository* in `com.example.payroll.repository`
<br> The bare definition with Entity type (`com.example.payroll.model.Employee`) ensures the basic CRUD
```java

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
```

## SpringBootAppliction
@SpringBootApplication is a meta annotation that pulls *component scanning, autoconfiguration*, and *property support*. 

We will preload SpringBoot app with data from class LoadDatabase. <br>
Create class `LoadDatabase` in package config.<br>
It has a @Bean `CommandLineRunner` which gets executed by SpringBoot when applicatio context is loaded.

Start the PayrollAppliction and observe in logs that two preloaded employees get generatged.

## Controller
create class *EmployeeController*  in package `endpoint` with annotation @RestController

* @RestController indicates taht data returned by each method will be written into the response body instead of rendering a template
* `EmployeeRepository` is injected by constructor
* We define routes for operations (@GetMapping, @PostMapping, @PutMapping, @DeleteMapping

add class `EmployeeNotFoundAdvace` with @ControllerAdvice annotation in package `endpoint` to generated an http 404 code. when `EmployeeNotFoundException` is thrwn

## testing rest controller
compile, package the gs-payroll project<br>
in terminal cd to the gs-payroll and run the springboot app using spring-boot-maven-plugin
```bash
./mvnw clean spring-boot:run
```
When the app start your can test it immediately
```
curl -v  http://localhost:8080/employees
#or
curl -X  GET http://localhost:8080/employees | jq
```
check that @ContollerAdvice produced 404 code
```
curl -X  GET http://localhost:8080/employees/99
```
to create a new employee
```
curl -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'
```

to update a user 
```
curl -X PUT localhost:8080/employees/3 -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'
```

to delete an employee
```
curl -X DELETE localhost:8080/employees/3
```

check file at project root `requests.http`

##  what makes something RESTful

add dependency into the project's `pom.xml`
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```
This dependency helps to inject links to the json outputs from rest calls

Add class *EmployeeModelAssembler* to simplify link creating  and change the EmployeeController to return `EntityModel<Employee>`  
 
Compile and run the app  and rerun the same GET requests. You will observe responses with links.
<br> The responses are formated  using *HAL*  a lightweight mediatype, which allows encoding not just data but also hypermedia controls. alerting consumers to other parts of the API they can navigate toward. The response produced looks like:
```json
{
  "id": 1,
  "name": "Bilbo Baggins",
  "role": "burglar",
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees/1"
    },
    "employees": {
      "href": "http://localhost:8080/employees"
    }
  }
}
```
  
A true RESTful service also supports the evolution of the api

## Evolving REST APIs
Key problem: how to evolve the api whithout breaking old clents. 

UseCase: split *Employee* attribute `name`  into `firstName` and `lastName`.

The strategy: Do not delete old attributes from your JSON representation add new attributes.
The Json below will support old clients and new clients
```json
{
  "id": 1,
  "firstName": "Bilbo",
  "lastName": "Baggins",
  "role": "burglar",
  "name": "Bilbo Baggins",
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees/1"
    },
    "employees": {
      "href": "http://localhost:8080/employees"
    }
  }
}
```

Modify the *Employee* entity to include new attributes and to construct getter and setter for older *name* attribute

Modify *EmployeeController::newEmployee()* method to use *ReponseEntity* . 
<br>Now when creating a new record, the response will produce the 201 code  as well as a *Location* header
```
POST http://localhost:8080/employees

HTTP/1.1 201 
Location: http://localhost:8080/employees/3
Content-Type: application/hal+json
Transfer-Encoding: chunked
Date: Fri, 25 Dec 2020 01:46:06 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 3,
  "firstName": "Samwise",
  "lastName": "Gamgee",
  "role": "gardener",
  "name": "Samwise Gamgee",
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees/3"
    },
    "employees": {
      "href": "http://localhost:8080/employees"
    }
  }
}
```

Perform the same code modification  on *EmployeeController::replaceEmployee* to use *ReponseEntity*.

Do the same code modification on *EmployeeController::deleteEmployee*

Test PUT and DELETE requests from requests.http  to observe HTTP codes

## Building links into your REST API
Show that Rest helps to keep coupling weak between domain objects.

Create @Entity class *Order*. Include *Status* enum in the same file

Create @RestController *OrderController*  with similar methods as in *Employee*  plus add 2 actions *complete()* and  *cancel()*

Create *OrderModelAssembler*   anbd see how to create conditional links with "cancel" and "complete" actions. 

Now test in requests.http
```bash
curl -v http://localhost:8080/orders
```
to observe that order/4 in status "IN_PROGRESS"  has 2 links for 2 *valid* changes of state, complete and cancel.

Try to cancel the the already canceled order
```bash
 curl -v -X DELETE http://localhost:8080/orders/4/cancel
```
to observe "Method not allowed"    message with HTTP code 405 

## Summary
* to support evolution of APIs, don't remove old fields. Instead support them
* use rel-based links so clients dont' have to hard code URIs
* retain old links as long as possible. Keep the rels so older clients have a path onto the new features.
* Use links, not payload data to instruct clients when various state-driving operations are available

The effort to build *RepresentationModelAssembler*  implementation for each resource type (done easier with HATEOAS) can ensure the clients  can upgrade with ease as you evolve your APIs.

## github repo
https://github.com/spring-projects/spring-hateoas-examples.

subprojects (old names)
* nonrest - smple spring vmc app with no hypermedia
* rest  - spring vmc + spring hateoas with hal representation for each resource
* evolution - rest app where a field is evolved but old data is retained for backward compatibility
* liks - rest app where confiditonal links are used to signal valid state changes to clients

