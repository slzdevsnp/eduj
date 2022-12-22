package org.szi.l01Interfaces.interfaces;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeLambdaDemo {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeLambdaDemo.class);;

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Max Levin", "Karl Zeus", "Vlad Ko");
        Stream<Employee>  stream = names.stream()
                                .map(Employee::new);
        Employee[] folks = stream.toArray(Employee[]::new);
        logger.info("employees from names %s%n",Arrays.asList(folks));

        List<Employee>  emps = names.stream()
                                .map(Employee::new)
                                .collect(Collectors.toList());
        logger.info("same with shorter syntax");
        logger.info("employees from names %s%n",emps);

    }

    //example of a method as a closure
    public static void repeatMessage(String text, int delay){
        //closure
        ActionListener listener = event -> {
            System.out.println(text); //has access to attributes from outer scope
        };
        new Timer(delay, listener).start();
        logger.info("called repeat Message");
    }

}
