#  Core Java 11 Advanced 2nd ed

by Cay S. Hortsmann

8h:

## Plan
* L1  Java 8 interfaces and lambdas ()  (30m)
* L2 Streams  (76m)
* L3 Processing Input and Output (67m)
* L4 Concurrent Programming (80m)
* L5 Annotations (33m)
* L6 Java Platform Module System (36m)
* L7 Compiling and Scripting (25m)
* L8 Internationalization (33m)
* L9 Date and Time (17m)
* L10 Java Database Connectivity (32m)
* L11 XML (27m)

## L1 Interfaces and Lambda expressions
factor 3
interfaces since Java 8
* static methods in interfaces
* default methods in interfaces. (can't access to instance attributes )
	* default method can call an abstract method	 	
	* default methods help solve an interface evolution problem

Any interfaces method is `abstract`, `default`, `static`, `private`, or `private static`.

demo `l01.interfaces.EmployeeSortTest`   implement new Comparator inner class   

concept of Functional interface

demo `l01.inerfaces.EmployeeLambdaDemo`  how to create lists of objects from List of String passing Object Constructor reference.

Example compare on 2 criterias

```java
Arrays.sort(staff,
            Comparator.comparingDouble(Employee::getSalary)
                      .thenComparing(Employee::getName));
```
## L2 Streams
t:20:20
####  understand the stream concepts
Streams represent efficient computation
Streams have lazy evaluation

approach:
* first create a stream
*  transform a stream with  1..n steps
	* each step yields another stream 
	* nothing computed yet
* produce result wit terminal operation
	* forces execution of lazy operations
	* afterwards the streams can no longer be used

### be able to create streams
streams creation:
* `Stream<T> stream = collection.stream()`
* `Stream<T> stream = Stream.of(array)`
* `Stream<String> song = Stream.of("gently", "down", "the", "road");`
* `Stream<String> silence = Stream.empty();`

Infinite Streams
*  `Stream<Double> reandoms = Stream.generate(Math::random);`
*  `Stream<BigInteger> seq = Stream.iterate(BigInteger.ZERO, n->n.add(BigInteger.ONE));`

demo `l02Streams.creating.CreatingStreams`

various ways to create streams


####  transform streams into other streams

most important stream operations
* filter
* map
* flatMap
* limit
* concat
* skip
* distinct
* sorted
* peek  (returns a stream)

demo `l02Srtreams.transforming.WordsDictionaryDemo`

demo `l02Srtreams.transforming.FilterMapDemo`



#### Work with Optional type

```java
Optional<String > optionalString1 = Optional.empty();
Optional<String> optionalString = Optional.ofNullable(null);
String result = optionalString.orElse("N/A");
```
cheat sheet
* grab the data:
```
data=opt.orElse(defaultValue);
data=opt.orElseGet(() -> ...);
data=opt.orElseThrow(SomeException::new);
data=opt.orElseThrow(); // same as get, throws noSuchElementException
```
* if/the/else with lambdas:
```
opt.ifPresent(value -> ...);
opt.ifPresentOrElse(value ->..., () -> ...); //
```
* transform/substitute:
```
anotherOpt = opt.filter(value-> ...).map(value -> ...).or(() -> ... );
```

Don't create an Optional to make a null check.

Optional is intended as a **return** type

demo:  `l02Streams.optional.OptionalDemo`

#### Place Stream results into collections
```
List<String> result = stream.collect(Collectors.toList());
Set<String> uresult = stream.collect(Collectors.toSet());
String result = stream.collect(Collctors.joining(", "));
```
summarizing
```
IntSummaryStatistics summary = stream.collect(Collectors.summarizingInt(String::length));
double averageWordLength = summary.getAverage();
int maxWordLenght = summary.getMax();
```

demo:  `l02Streams.collecting.CollectingResults`

#### Place steream results into maps

demo `l02Streams.downstream.DownStreamCollectors`

#### Streams on primitive types
* InstStream, LongStream, DoubleStream
* OptionalInt, OptionalLong, OptionalDouble
* getAsInt , getAsLong, getAsDouble

demo `l02Streams.primitive.StreamPrimitiveTypeDemo`

#### speed up stream with parallel streams
`Stream.parallel()`

demo `l02Streas.parallel.ParallelStreams`

## Ch4 Concurrent Programming

##### Executors
`Runnable` interface
Define Runnable task, instantiate an exdecutor than executor.execute  the task

demo `runnable.RunnableDemo` shows thtat threads  order is non-deterministic..
demo  `callable.CallableDemo` shows  _ExecutorService_   launching  lists of _Callable_ tasks with _Future_ result

#### strategies for sharing data among threads
* confinement 
	* don't share data among tasks
* immutability
	* share only immutable data structures
* locking (least attractive)
	* temporarily block other tasks when carrying out updates 
	* can be expensive as other tasks wait idly, can be dangerous  (deadlocks )
	* best left for experts
demo `raceCondition.Demo` , `raceCondition.Demo2`  shows problems when sharing data between threads

#### parallel algos
demo `parallelSort.ArraysParallelSortDemo`

####  threadsafe data strucutres
_ConcurrentHashMap_, _BlockingQueue_
demo `threadsafeStructures.ConcurrentHashMapDemo` fills map elements from parallel threads
demo `threadsafeStructures.BlockingQueueTest`  Producer Consumer  pattern with a bounded queue

#### atomic variables
* thread-safe counters
* used to build thread-safe data structures

demo `atomic.AtomicLongDemo`  _AtomicLong.incrementAndGet()_

demo `atomic.LongAdderDemo_`  same  logic as in AtomicLongDemo. But executes faster.

#### low-level locks

demo `locks.DockDemo`  _ReentrantLock_
dmeo `locks.MyQueueDemo`  with _notify()_ , _notifyAll()_  in _MyQueue_ class.

#### interruption
demo `interruption.InterruptionDemo` shows use of _ExecutorService.invokeAny()_

#### thread local
demo `threadLocal.ThreadLocalDemo` shows _ThreadLocal.withInitial( lambda)

#### completable futures
`CompletableFuture<T>`   implements  `CompletableStage<T>` . This allows completable futures to be composed. 

demo `completableFutures.CompletableFutureDemo`  NB!  shows  _CompletableFuture_ usage for async calculations.


##### OS processes
demo `osprocesses.OsProcessDemo`

NB! to do Baeldung Concurrency

## ch5 Annotations

