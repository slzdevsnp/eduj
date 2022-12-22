package packt.j9fprog.fmapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamBaseExample {

    static void baseExampleA(){
        Integer a[] = new Integer[] {3,1,2,5,7,6,9,8,0,11,13,12};
        List<Integer> lstOfNumbers =  Arrays.asList(a);

        //lazy eval
        List<String> lstOfStrings = lstOfNumbers
                .stream()
                .filter(number -> number % 2 ==0) //paired numbers
                .sorted()
                .map(e -> Double.valueOf(e))  // to doubles
                .map(e -> e.toString())
                .collect(Collectors.toList());
        System.out.println("list after transforms to strings:"+lstOfStrings);

        String firstEl = lstOfNumbers
                .stream()
                .filter(number -> number % 2 ==0)
                .sorted()
                .map(e -> Double.valueOf(e))
                .map(e -> e.toString())
                .findFirst()
                .get() ;
        System.out.println("first element:" + firstEl);

    }

    public static void main(String[] args) {
        //baseExampleA();
        oidParamsExample();
    }


    static void oidParamsExample(){
        Integer iid[] = new Integer[] {1103,66297,6630, 80862,103508,103509};
        List<Integer> oids = Arrays.asList(iid);
        String  oidstr= oids.stream()
                .map(e -> "id="+e.toString())
                .collect(Collectors.joining("&"));

        System.out.println("deb:"+ oidstr);

    }

}
