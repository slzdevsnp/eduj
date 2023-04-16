# danvega video
https://www.youtube.com/watch?v=CXbXoMIVpRg



# output of query for top level steps
```txt
1. Choose a database: First, you need to choose a database that you want to use for your application. Spring Boot supports various databases, including MySQL, Oracle, PostgreSQL, and MongoDB, to name a few.

2. Setup database connection: You need to set up the database connection in your Spring Boot application. You can use the spring.datasource.* properties in the application.properties file to configure the database connection properties, such as URL, username, and password.

3. Create a data model: Next, you need to create a data model class that represents the data you want to store in the database. This class should include the fields and methods required to interact with the data. You can use Java annotations, such as @Entity, @Id, and @GeneratedValue, to map the data model class to the database table.

4. Create a repository interface: You need to create a repository interface that defines the database operations for the data model class. Spring Data JPA provides a repository interface, which you can extend to perform CRUD operations on the database.

5. Create a controller: You need to create a REST controller class that handles HTTP requests and returns responses. The controller should include methods that map to the CRUD operations you want to perform on the data. You can use annotations such as @RestController, @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping to map the controller methods to HTTP requests.

6. Test the REST API: Finally, you need to test the REST API by sending HTTP requests to the endpoints defined in the controller and verifying the responses. You can use tools like Postman or cURL to send HTTP requests and check the responses.

```

# list of chatgpt queries
1. assuming you are  a SpringBoot expert what are the list of steps needed to create a REST API in Spring Boot that talks to a database ?
2. Create a Spring Boot Entity for a Post type that includes the following properties
 - id
- title
- content
- slug
- dateCreated
- dateUpdated    

3. Create a Spring Data Repository that extends JPaRepository for the Post class defined above and include all CRUD operations
4. Implement the REST API controllers using Spring MVC to handle HTTP requests and return responses
5. Write a CommandLineRunner bean that will insert a single post using he PostRepository




# steps to build the app
1. get top level steps with q 1
2. create skeleton in spring initializr  dependencies: web, data-jpa,h2  (  spring init --list | grep xx) Created with command `eduj/spring/tools2start/mk_sbootskelet.sh --sbversion=3.0.4 --jversion=17 --deps=web,data-jpa,h2 --groupid=dev.szi.danvega  --artifactid=blog  --modulename=blog  --packagename=dev.szi.danvega.blog --dirname=danvega_blog`

3. create data model for blog posts

Entities: 
**Post**
- id
- title
- content
- slug
- dateCreated
- dateUpdated  

4. run query 2 which creates **Post** model class and  copy this class in idea in _model_ package
5. run query to create repository, create a **PostRepostiory** interface  in pkg `repository` but without `@Repository`  chatGpt suggested to use *ResponseEntity*  but in video it was not yet suggested, so *ResponseEntity* was not used in  **PostController**. 
6. run query  to create *CommandLineRunner*. add `@Ben `  CommandLineRunner  to **CommandLineRunner**  and make run the springBoot app.  
7. go to **PostController**  click on small triangle near @GetMapping  select 'Generate request in HTTP Client'  adapt and save requst in file requests.http  and do the basic testing

8. szi run a query:for a PostController class below   write  a Test to  test  Controller  requests   with first  saving some testing Post objects in the PostRepository.    create a class **PostControllerTest**   add application-test.properties
