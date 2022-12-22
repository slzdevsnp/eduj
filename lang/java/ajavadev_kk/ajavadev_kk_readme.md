# Advanced Java Development

by Ken Kousen

## plan
* interfaces, inheritances and objects
* generics & collections
* new io packages
* concurrency in java 
* workign with RDBs
* networking  !!
    * urls, streams + parse json + socket
* testing with junint
* inner classes
    * lambdas
* new features in java 8
    * lambdas, method refs, streams, concurr collections, java.time


## prereqs

### setup mysql in docker

Done using docker-compose.yml 


#### setup gradle projects advancedava_ref  and advancedjava_szi

* repos/slzdevsnp/clearn/java8/ajavadev_kk/advancedjava_ref
    * OK setup in intellij
    * running the task `gradlew build`required first to compile, then to execute the groovy file
        src/main/groovy/database/initialize_database.groovy   in intellij

    * OK setup in intellij myslq connection with user jpa

* repos/slzdevsnp/clearn/java8/ajavadev_kk/advancedjava_szi
    * OK initial setup in intellij via gradle init  + import 



## part 1  interfaces, inheritance and objects
(4h)   factor 3

### abstract classes and methods

oop.Employee
* has abstract method getPay(); so it is an abstract class 
* so cannot be instantiaded

oop.Salaried extends Employee

### Using abstract classes

* debug into instantiation of Salaried extending Employee
  inside oop.UseHR


### implementing interfaces

`interfaces.TasSorter`  sorts `interfaces.Task` which implements Comparable

### static and defaut methods in interfaces
Since java 8 interfaces can have instance methods and default methods.

A default method allows an interface to define an implementation which is used by default in a situation where a concrete class failes to provide and implementation for that method.

Prior to java 8 interface had stubs of abstract methods and constants

it makes sense to have static methods in interfaces

e.g interface Stream

`interfaces.defaults.Company`  `interfaces.defaults.Employee`
`interfaces.defaults.CompanyEmployee`

class always overrides method definition in interface

how to call defaut methods in interface
```java
public class CompanyEmployee implements Company, Employee  {
    ...
     @Override
    public String getName() {
        return String.format("%s working for %s",
                Employee.super.getName(), Company.super.getName()); //way to call interface default methods
    }
}
```

`interfaces.defaults.DefaultMethodsDemo` shows use of static and default interface methods

### Overriding toString, equals and hashCode

`toString()` is inherited from Object 


is SalariedEmployee equal to Employee  ? currently it is a discussion

in `oop.Employee`  use intellij to generate equals() and hashCode()

!NB overriding   method have same name AND same signature
vs  overload   method have same name and different signature.

15:40 pause:
17:26 back

**the deal with hasCode**
demo `oop.StoreNumbers`  which fast returns if an int is stored in array

* hash code is a mechanism to convert an object to an integer. It enables a rapid a lookup of an object in a collection

* 2 equal objects must return the same hash code

* objects which are not equal e.g. two strings may (rarely) return the same hash code

`oop.Employee.hasCode()`  `interfaces.Tassk.hashCode()`

###  Using Exceptions Effectively

`Exception` class extends `Throwable`

`RuntimeException` is special because it is unchecked at compile time

Checked exceptions are checked at compile time. In C++ all exceptions are unchecked. 

`exceptions.Arithmetic` demo

ArithmeticException extends RunTimeException. It is not detected in a compile time.  Check all child Exceptions under RunTimeException.

Try with resources is inside this demo.  `try (BufferedReader br = ..)}`

Java check vs unchecked Exceptions  dilemma.

Java open-source projects written by Experts catch checked Exceptions and re-throw them as unchecked Exception. So they are not caught at compile time. 


## part 2 Generics and Collections
0.9 h factor 2

#### Generic Types
Generics are becoming increasingly important

Original motivation of generics : ensuree the same type in a collection

demo `generics.GenericCollectionDemo`

nonGenerics vs genericList

`generics.Tuple`  is a class defined with two geneeric types

`generics.TupleDemo`  calls Tuple with different types 

`Tuple<>`  on the right. Types are inferred from the left

At the compilatio stage types are erased. Everyting is of type object with appropriate castings

`generics.PairDemo`  with `Pair` class with a single T object

non-static and static `swap()` method



#### Type Bounds and WildCards

`generics.wildcards.HRDemo`

shows `generics.wildcards.HR` use of wild cards in generic cards in
```
printEmpAndSubclassNames(<List<? extends Employee> employees)
```
is anyType
which is a child of Employee

NB: PECS --> produces uses extends, consumes uses super

`generics.repairables.RepairShopDemo`  shows the usage of `Repairable` interface with default method fix()  called from `RepairShop.fixAll`


### Part 3 the enew I/O packages
0.25h  factor 1

#### Path and Paths

Paths.get(String...)  //callable with many String ar

`newio.PathDemos`   how to get current , parent root path and convert it toAbsolutePath()

#### File manipulation

`newio.FilesDemos`

create, delete directories
resolve files,  copy, and delete files

in java `File` is an abstract representation of File

nio.Files has a ton of static methods.


### Part 4 Concurrency in Java
(52m)
factor 2.5

Thread class  and runnable interface . wait(), notify() notify_all() are low level  methods in object class


#### threads, runnables and executor service

simple Thread starting

demo `concurrency.UseMyThread`  usage of simple `concurrency.MyThread`

demo `concurrency.UseMyRunnable` use of concurrency.MyRunanble

demo `concurrency.UseExecutorService`   starts Runnable

#### Callables and Futures 

* Collable is an interface
* Future is an  interface, represents a result of async computation

demo `concurrency.callables.CallablesDemo`

demo `concurrency.callables.FileLinesCounter`  complete example how to use callables anf futures


#### Locks and Latches

demo `concurrency.locks.LocksDemo`  demoCounter() unsychronized  demoSyncCounter() 
synchronized  demoLockedCounter() with locks  demoAtomicCounte()  using Atomic

demo `concurrency.latch.LatchDemo`  5 threads  with CountDownLatch
clear demo

#### The Producer/Consumer problem

demo `concurrency.blockingqueue.ProducerConsumerDemo`  use of BlockingQueue


### part 5: working with relational databases
(40m) factor 2

#### TraditionalJDBC Classes



demo: `database.jdbc.JdbcPersonDAO`   database.jdbc.JdbcPersonDAOTest    classic approach with connection in every method (inefficient)

classic approach. no transactions.  every request a new connnection is requested

demo `database.jpa.JpaPersonDAO`   `database.jpa.JpaPersonDAOTest`

based on hibernate.   (hibernated dependency is added to bild.gradle)

hibernate needs `src/main/resources/META-INF/persistence.xml`  check this file for hibernate properties  in a real world , combine it with spring  or springBoot .  grails == groovy framework  


### part 6: Networking
(40m)   17:06 - 17:56
factor 1

##### java.Net Package

`java.net`  package   URL  class

demo `networking.LocalAdress`  

demo `networking.ParseURL`  parts from url string decomposition


##### working with URLs and Streams

demo `networking.Geocoder`  `networking.GeocoderTest`



https://developers.google.com/maps/documentation/geocoding/start

class URLEncoder   test `networking.GeocoderTest`


####  Parsing JSON Data

dependency:    'javax.json:javax.json-api:1.1.4' where reference javax.json implementation resides 'org.glassfish:javax.json:1.1.4'




#### Parsing JSON Data

demo `networking.Geocoder`  `networking.GeocoderTest`

check method `void fillInLatLng(Location location)`  and `@Test fillInLatLng()`

`import javax.json.*;`



#### The Socket and ServerSocket

`Socket` and `ServerSocket` classes. (simple to understand).

 They underly behind higher order classes like URL 


demo `networking.sockets.EchoServer`  and `networking.sockets.EchoClient`  run them two

###  part 7 Testing with JUnit
(40 min)  factor 1

#### the JUnit Annotations

TDD is great for code refactoring.   demos in  `test.java.junit`

At the time of course  junit 4 in prod. in 2020 junit 5 in prod.

demo `junit.ListTests`  has `@BeforeClass` and `@Before` and `@After` , `@AfterClass`

demo `junit.AssertTests`

#### Writing TestCases
Good developers write a lot of testCases

demo `junit.AssertTests`   (comes from JUnit project)   has hamcrest  matchers.  Hamcrest is a transitive dependency of junit4.
Use of  `@Ignore` to skip a particular test but not comment it out. 

#### Testing for Exceptions
demo `junit.ListTestsWithException`  check  method  `Test(expected = NullPointerException.class) public void nullListThrowsNPE()`   and line `@Rule public ExpectedException`

A true unit test is a test of a class in isolation. 

Mockit is one of libraries  for Mock Objects:  https://site.mockito.org/

### part 8 Inner Classes
(28 m)   factor 4 (slack)

#### Static and Anonymous Inner Class
A Class defined inside in another class.
Inner Class has access to private attributes of its container (an Outer class).

If Inner class is declared as private the outside world does not know about it.
If Inner is declared public, the outer class can instantiate it.  NB Inner class is a part of the Outer. Inner cannot isntantiate outside the outer classs.. demo `innerclasses.UseInnerClasses`.
A static Inner class   does require instatiation of the Outerclass  `new Outer.InnerStatic().myMethod();`

demo `innerclasses.FileDemo` calling a `JavaFilter` Class and also an anonymous class.

#### Single Abstract Method interfaces and Lambdas
Reminder: since Java8 one can have default and static methods along method signatures defined in Interfaces. 

demo `innerclasses.SortStringsDemo` calling class `StringSorter`

Inner classes are useful when  one wants to access private fields in Outer class or when one needs to create a one time use class. (anon class with single abstract method. this is replaced by lambda pattern).

### part 9 New Features in Java8
factor 3
A Push into functional programming paradigm with immutable objects.


#### Lambdas 
java8 new package `java.util.Function`.
Interfaces there are all annotation with `@FunctionalInterface`
In java even  a static method needs to leave in a Class.
With lambdas  functions become first-class objects. 
Demo `lambdasstreams.LambdasDemo`
`java.util.function.Consumer` has `java.util.function.DoubleConsumer`, `IntConsumer`, `LongConsumer`  for specified types  to save on type unboxing. 
This is a general Pattern 

#### Method References 
in C++  this is a function pointer.
References in Java:
```java
ClassName::staticMeethodName
ClassName::instanceMethodName
ClassName:new
```
demo `lambdasstreams.MethodReferencesDemo` and `lambdasstreams.FileFilterDemo`  to see streams

#### Streams
NB!
demo  in tests `lambdasstreams.StreamsDemoTest` using `lambdasstreams.StreamsDemo`

demo `lambdasstreams.UsePerson`
demo `lambdasstreams.SortingDemoTest` using  `SortingDemo`
demo `lambdasstreams.ProcessDictionary`

Java 8 gives a rich interfaces for stream processing .

#### Concurrent Collections
Streams are lazy

demo `lambdasstreams.Summarizing` NB `Collectors.summarizingDouble()`
try to increas lenght of array to 1E7  to see all thread kicking in

demo `lambasstreams.UsingCollect`

`stream()` produces a brand new object 
```java
List<String> evens = wstrings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList()); //produces new collection
```

demo `lambasstreams.LazyStreams`
shows that streams are evaluated lazily. only the necessary computations kick in

demo `lambasstreams.CollectionsDemo` with method measuring times
NB! `stream()` vs  `.parallelStream()` 

##### java.time  Package
Fix the mess with`Date` and `Calendar` classes  

`java.time` package to fix it.  
* Instant
*  Duration
*  Period
* LocalDate
* LocalTime
*  LocalDateTime
* ZonedDateTime
* enum DayOfWeek, Month, Year, ...

demo `lambasstreams.JavaTimeDemos`
play with `LocalDate`, `LocalDate.of()`, `Instant.now()`, `Instant.now().plus()`, `Duration`, LocalDate.until(LocalDate,..`),  timezones

(67m)



