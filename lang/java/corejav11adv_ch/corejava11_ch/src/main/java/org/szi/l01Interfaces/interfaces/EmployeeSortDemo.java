package org.szi.l01Interfaces.interfaces;


import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class EmployeeSortDemo {

//    private static final Logger logger = LoggerFactory.getLogger(EmployeeSortDemo.class);

    private  void testSortCollWithMethodReference(List<String> l){
        log.info("## sorting on collecton with method referencing");
        l.sort(String::compareToIgnoreCase);

        log.info("words after sorting %s%n",l);
    }

    private  void testCompareEmployeesWithCompratorInner(Employee[] staff) {
        log.info("## sorting on compareTo in Employee class");
        Arrays.sort(staff);
        log.info(Arrays.toString(staff));

        log.info("### sorting employees on another criteria");
        //inner class
        class NameComparator implements Comparator<Employee> {
            @Override
            public int compare(Employee left, Employee right) {
                log.info("comparing " + left + " and " + right);
                return left.getName().compareTo(right.getName());
            }
        }
        Arrays.sort(staff, new NameComparator());
        log.info(Arrays.toString(staff));
    }
    private  void testCompareEmployeesWithLambda(Employee[] staff){
        //now the syntaxis for the same is so much shorter
        System.out.println("### sorting on compareTo implemented as lambda");
        Arrays.sort(staff,
                (left,right)-> right.getName().length() - left.getName().length());
        System.out.println(Arrays.toString(staff));
    }
    private   void testCompareEpmloyeesWith2comparison(Employee[] staff){
        System.out.println("### sort on 2 criterias");
        Arrays.sort(staff,
                Comparator.comparingDouble(Employee::getSalary)
                        .thenComparing(Employee::getName));
        System.out.println(Arrays.toString(staff));
    }

    public static void main(String[] args) {


        Employee[] emps = new Employee[3];

        emps[0] = new Employee("Harry Hacker", 75000);
        emps[1] = new Employee("Carl Cracker", 76000);
        emps[2] = new Employee("Tony Tester", 38000);

        log.debug("all tests started {}.","youppi");

        EmployeeSortDemo d = new EmployeeSortDemo();
        log.info("testCompareEmployeesWithCompratorInner");
        d.testCompareEmployeesWithCompratorInner(emps);
        log.info("testCompareEmployeesWithLambda");
        d.testCompareEmployeesWithLambda(emps);
        log.info("testCompareEpmloyeesWith2comparison");
        d.testCompareEpmloyeesWith2comparison(emps);

        List<String> words =
                Arrays.asList("this", "Is", "a", "list", "of", "Strings");
        log.info("testSortCollWithMethodReference");
        d.testSortCollWithMethodReference(words);

        log.debug("all tests done..");
    }


}