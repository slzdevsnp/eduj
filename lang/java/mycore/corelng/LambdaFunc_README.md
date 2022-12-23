

# LambdaFunc module

## article Lambda Expressions and Functional Interfaces: Tips and Best Practices
* https://www.baeldung.com/java-8-lambda-expressions-tips  

gitrepo : eugen tutorials/core-java-modules/core-java-lambdas  pkg com.baeldung.java8.lambda.tips    


See test class  `baeldung.lambdaexpr.FunctionalInteracesLambdasUnitTest`


A lambda is an anonymous function  handled as a first class citizen. i.e. we can pass it or return from a method

Lambda expressions should be short, ideally one line of code (exprssion vs narrative)

## article Functional Interfaces in Java 8
* https://www.baeldung.com/java-8-functional-interfaces

gitrepo : eugen tutorials/core-java-modules/core-java-lambdas  pkg com.baeldung ??

see test class  `baeldung.funcinterface.FunctionalInterfacesUnitTest`

Any interface with a SAM(Single Abstract Method) is a functional interface.
Its implementation may be treated as lambda expressions.

It's recommended that all functional interfaces have an informative @FunctionalInterface annotation.
This is to communicate its intent and allow a compiler to generate an error. 

### functions
Function interface is a interface with 1 method with 1 param returning another param.

Principle of functions compositions: `compose` method. 

primitive types functions 

BiFunctions.

Suppliers when arg takes long time to generate, or create sequences

Consumers, when a func returns void

Predicates.

Operators, BinaryOperators

Runnable and Callable interfaces are also functional interfaces.


