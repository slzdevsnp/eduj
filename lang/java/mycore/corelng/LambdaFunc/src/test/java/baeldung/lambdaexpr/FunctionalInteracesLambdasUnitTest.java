package baeldung.lambdaexpr;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Slf4j
public class FunctionalInteracesLambdasUnitTest {
    private UseFoo useFoo;

    @Before
    public void init() {
        useFoo = new UseFoo();
    }

    @Test
    public void functionalInterfaceInstantiationWithLambda() {
        final Foo foo = param -> param + "from lambda";
        final String result = useFoo.add("FunkyMessage ", foo);
        assertThat( result, is("FunkyMessage from lambda"));
    }

    @Test
    public void skippingFooInstantiation_standardFIParam() {
        final Function<String,String> fn = param -> param + "from lambda";
        final String result = useFoo.addWithStandardFI("FunctionMessage ", fn);
        assertThat( result, is("FunctionMessage from lambda"));
    }

    @Test
    public void defaultMethodFromExtendedInterface_whenReturnDefiniteString_thenCorrect() {
        final FooExtended fooExtended = string -> string;
        final String result = fooExtended.defaultCommon();

        assertThat("Default Common from Bar", is(result));
    }

    @Test
    public void lambdaAndInnerClassInstantiation_whenReturnSameString_thenCorrect() {
        final Foo foo = (param) -> param + "from Foo";

        //instantiate func inerface by inner class
        final Foo fooByIC = new Foo() {
            @Override
            public String method(final String string) {
                return string + "from Foo";
            }
        };
        //result is the same as with lambda expression
        assertThat(foo.method("Something "), is(fooByIC.method("Something ")));
    }

    @Test
    public void processorTest() throws Exception {
        Processor p = new ProcessorImpl();
        var result1 = p.processWithCallable(() -> "abc");
        var result2 = p.processWithSupplier(() -> "abd");
        assertThat(result1, is("abc"));
        assertThat(result2, is("abd"));
    }

    @Test
    public void accessVariablesFromDifferentScopes_whenReturnPredefinedString_thenCorrect() {
        //see how IC instatiation and lambda expression instatiation are different for using keyword 'this'
        assertEquals("Results: resultIC = Inner class value, resultLambda = Outer scope value",
                useFoo.scopeExperiment());
    }


    @Test
    public void shorteningLambdas_whenReturnEqualsResults_thenCorrect() {
        //prefer this approach to instatiate lambda expression
        final Foo foo = parameter -> buildString(parameter);

        final Foo fooHuge = parameter -> {
            final String result = "Something " + parameter;
            // many lines of code
            return result;
        };

        assertEquals(foo.method("Something"), fooHuge.method("Something"));
    }

    private String buildString(final String parameter) {
        final String result = "Something " + parameter;
        // many lines of code
        return result;
    }


    @Test
    public void mutatingOfEffectivelyFinalVariable_whenNotEquals_thenCorrect() {
        final int[] total = new int[1];
        total[0] = 0;
        //lambda expression can mutate effectively final variable
        final Runnable r = () -> total[0]++;
        r.run();

        log.info("total[0] = {}", total[0]);

        assertNotEquals(0, total[0]);
    }


}
