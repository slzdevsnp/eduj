package baeldung.funcinterface;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Slf4j
public class FunctionalInterfaceUnitTest {

    @Test
    public void whenPassingLambdaToComputeIfAbsent_thenTheValueGetsComputedAndPutIntoMap() {
        Map<String, Integer> nameMap = new HashMap<>();
        //computeIfAbsent has params: key and java.util.function(K,V)
        // this line insert a k,v pair into nameMap
        Integer value = nameMap.computeIfAbsent("John", String::length);

        assertEquals(new Integer(4), nameMap.get("John"));
        assertEquals(new Integer(4), value);
        assertEquals(1, nameMap.size());
        //inserting another k,v pair
        nameMap.computeIfAbsent("Maria", String::length);
        assertEquals(2, nameMap.size());
    }

    @Test
    public void whenComposingTwoFunctions_thenFunctionsExecuteSequentially() {

        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";
        //principle of function composition, (call a default method .compose() on a function)
        Function<Integer, String> quoteIntToString = quote.compose(intToString);

        assertThat("'5'", is(quoteIntToString.apply(5)));

        Function<String, String> diamonds = s -> "<" + s + ">";
        Function<Integer, String> diamondsQuoteIntToString = diamonds.compose(quoteIntToString);
        assertThat("<'5'>", is(diamondsQuoteIntToString.apply(5)));
    }

    @Test
    public void whenUsingCustomFunctionalInterfaceForPrimitives_thenCanUseItAsLambda() {
        //short is a primitive 16 bits type with integers, int type is 32 bit
        short[] array = {(short) 1, (short) 2, (short) 3};


        byte[] transformedArray = transformArray(array, s -> (byte) (s * 2));

        ShortToByteFunction s2bfunc = s -> (byte) (s * 2);
        byte[] transformedArraySame = transformArray(array, s2bfunc);


        byte[] expectedArray = {(byte) 2, (byte) 4, (byte) 6};
        assertThat(expectedArray, is(transformedArray));
        assertThat(expectedArray, is(transformedArraySame));
    }

    public byte[] transformArray(short[] array, ShortToByteFunction f) {
        byte[] transformedArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            transformedArray[i] = f.applyAsByte(array[i]);
        }
        return transformedArray;
    }

    @Test
    public void whenUsingKnownPrimitivFunction() {
        DoubleToIntFunction myd2ifun = (double v) -> (int) (v * 2);
        assertThat(myd2ifun.applyAsInt(1.0), is(2));
        assertThat(myd2ifun.applyAsInt(2.1), is(4));
    }

    @Test
    public void whenUsingBiFunction_thenCanUseItToReplaceMapValues() {
        Map<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 40000);
        salaries.put("Freddy", 30000);
        salaries.put("Samuel", 50000);
        //This is biFunciton
        salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue + 1 : oldValue + 10000);

        assertEquals(new Integer(50000), salaries.get("John"));
        assertEquals(new Integer(30001), salaries.get("Freddy"));
        assertEquals(new Integer(60000), salaries.get("Samuel"));
    }

    @Test
    public void whenUsingBiFunctionWithLongerLogic() {
        Map<String, Integer> salariesBase = new HashMap<>();
        salariesBase.put("outlier", 40000);
        salariesBase.put("inline", 30000);
        salariesBase.put("bottom", 20000);

        // if Bifunction logic is longher then 1 line, put it in a separate function def
        salariesBase.replaceAll((name, oldValue) -> transformSalaryValue(name,oldValue) );

        assertThat(salariesBase.get("outlier"), is(Integer.valueOf(80000)));
        assertThat(salariesBase.get("inline"), is(Integer.valueOf(35000)));
        assertThat(salariesBase.get("bottom"), is(Integer.valueOf(20000)));
    }

    private Integer transformSalaryValue(String key, Integer oldValue){
        Integer newVal;
        switch(key){
            case "outlier": newVal = oldValue * 2;break;
            case "inline": newVal = oldValue + 5000; break;
            case "bottom": newVal = oldValue; break;
            default:
                throw new RuntimeException("non existing map value");
        }
        return newVal;
    }

    @Test
    public void whenUsingSupplierToGenerateValue_thenValueIsGeneratedLazily() {

        //simulate  a long time to obtain ann argument
        Supplier<Double> lazyValue = () -> {
            Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
            return 9d;
        };

        double valueSquared = squareLazy(lazyValue);

        assertThat(valueSquared, is(81d));

    }
    public double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    @Test
    public void whenUsingSupplierToGenerateNumbers_thenCanUseItInStreamGenerate() {

        //fibonacci sequence formula, an array is used to store mutable variables inside lambda
        final int[] fibs = { 0, 1 };
        //the lambda func as an arg for Stream.generate implements a Supplier functional interface
        Stream<Integer> fibonacci = Stream.generate(() -> {
            int result = fibs[1];
            int fib3 = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fib3;
            return result;
        });

        List<Integer> fibonacci5 = fibonacci.limit(5)
                .collect(Collectors.toList());

        assertEquals(new Integer(1), fibonacci5.get(0));
        assertEquals(new Integer(1), fibonacci5.get(1));
        assertEquals(new Integer(2), fibonacci5.get(2));
        assertEquals(new Integer(3), fibonacci5.get(3));
        assertEquals(new Integer(5), fibonacci5.get(4));
    }

    @Test
    public void whenUsingConsumerInForEach_thenConsumerExecutesForEachListElement() {
        List<String> names = Arrays.asList("John", "Freddy", "Samuel");
        names.forEach(name -> log.debug("element {} has a lenght of {} chars",name,name.length()));
    }

    @Test
    public void whenUsingBiConsumerInForEach_thenConsumerExecutesForEachMapElement() {
        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 25);
        ages.put("Freddy", 24);
        ages.put("Samuel", 30);

        ages.forEach((name, age) -> log.debug("key is {}  as a name value is : {} as an age",name,age));
    }

    @Test
    public void whenUsingPredicate(){
        List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");
        //a predicate is a func which receives a  value and returns a boolean
        List<String> namesWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        assertThat(namesWithA.size(), is(2));
    }

    @Test
    public void whenUsingUnaryOperatorWithReplaceAll_thenAllValuesInTheListAreReplaced() {
        List<String> names = Arrays.asList("bob", "josh", "megan");
        names.replaceAll(e -> e.toUpperCase());
        //or
        //names.replaceAll(String::toUpperCase);

        assertThat(names.contains("BOB"), is(true));
        assertThat(names.contains("bob"), is(false));
        assertThat(names.size(), is(3));

        names.replaceAll(e -> "'"+e+"'");
        names.forEach( e -> log.debug("after operator the names list is {}",e));
        assertThat(names.contains("'BOB'"), is(true));

    }

    @Test
    public void whenUsingBinaryOperatorWithStreamReduce_thenResultIsSumOfValues() {

        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);

        int sum = values.stream()
                .reduce(0, (i1, i2) -> i1 + i2);
        int sumFromBase = values.stream()
                .reduce(10, (i1, i2) -> i1 + i2);
        assertThat(37, is(sum));
        assertThat(47, is(sumFromBase));

    }

    @Test
    public void whenPassingLambdaToThreadConstructor_thenLambdaInferredToRunnable() {
        log.debug("base thread: {}",Thread.currentThread().getName());
        Thread thread = new Thread(() ->
                log.debug("Hello From Another Thread:{}",Thread.currentThread().getName()));
        thread.start();
        log.debug("forked thread: {}",thread.getName());
    }

}
