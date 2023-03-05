package rsb.synchronicity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class OherTests {

    @Test
    void testFunctionConsumer(){
        List<String> strings = Arrays.asList("foo", "bar", "baz");
        Consumer<String> printString = s -> System.out.println(s);
        //for each element in collection the consumer function with a operation not returning a result is applied
        strings.forEach(printString);
        assertTrue(strings.size() > 0);
    }
}
