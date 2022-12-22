# Java 8 features 

(various features on java8+)

## packt Learning-Java-9---Functional-Programming-v-
* safari 
*  https://github.com/PacktPublishing

* Demos: corelang module Generics
  * packt.j9fprog.GenericMethods
    * GenericMethodsMain: generic methods testing from GenericMethods
    * packt.j9fprog.fmapping
      * StreamBaseExample: decortication of stream() api
      * FunctionalInterfaces: passing functions as args
      * LambdaBaseExample: base example of lambda function
      * FilteringData, MappingData  of streams
      * ComplexFunctions: flatmap of listsOfLists, reduce of lists

## packt hands-on functional programming with java
* safari
* https://github.com/PacktPublishing/Hands-On-Functional-Programming-with-Java
* module FuncProg  top package: packt.hofprogj 

## ch 1 understanding functional programming 

### benefits of function programming

* state of the data is no changeable
  * objects are immutable
  * code outputs consistently the same result
  * problems divisible to sub problems and thus parallelizable

**labmda expressions**  are pillars of functional programming

a lambda expression is an anonymous class with a single method

! demo  packt.hofprogj.anonclasses 
    anonThread  and AnonThreadLambda.  the later defines a lambda expression instead of anon class.

### interface vs functional interface
**Normal interface** is a collection of abstract methods.

It may also contain:
* constants
* default methods (can have a body)
* static methods (can have a body)
* nested types

**Functional Interface** can have only o abstract method.

Its implementation depends on lambda expression

## ch 2 working with lambda expressions
* v. implementing the syntax of lambda expression
  demo FuncIntfImplSyntax  shows implementation of Functional Interfaces
* v. replacing anonymous classes
  * demo 2 helloAnon //classic interface def but lambda implementation
  * demo 3 ParamAnon
* v. using method references
* 
* v. best practices for using lambda expression
  * 