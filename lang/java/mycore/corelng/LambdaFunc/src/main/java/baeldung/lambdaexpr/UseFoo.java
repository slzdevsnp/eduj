package baeldung.lambdaexpr;

import java.util.function.Function;

// see test  FunctionalInteracesLambdasUnitTest

public class UseFoo {

    public String add(String string, Foo foo){
        return foo.method(string);
    }

    public String addWithStandardFI(String string, Function<String,String> fn){
        return fn.apply(string);
    }

    private String value = "Outer scope value";

    public String scopeExperiment() {
        Foo fooIC = new Foo() {
            String value = "Inner class value";

            @Override
            public String method(String string) {
                return this.value;
            }
        };
        String resultIC = fooIC.method("");

        Foo fooLambda = parameter -> {
            String value = "Lambda value";
            return this.value;
        };
        String resultLambda = fooLambda.method("");

        return "Results: resultIC = " + resultIC +
                ", resultLambda = " + resultLambda;
    }


}
