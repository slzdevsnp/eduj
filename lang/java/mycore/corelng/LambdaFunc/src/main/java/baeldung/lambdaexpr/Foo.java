package baeldung.lambdaexpr;

// see test  FunctionalInteracesLambdasUnitTest

/*the compiler will trigger an error in response to any attempt to break the
    predefined structure of a functional interface.
*/
@FunctionalInterface
public interface Foo {

    String method(String string);


    default void defaultMethod() {}
}
