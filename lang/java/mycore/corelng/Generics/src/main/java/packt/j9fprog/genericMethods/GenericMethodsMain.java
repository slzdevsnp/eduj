package packt.j9fprog.genericMethods;

import packt.j9fprog.genericMethods.model.GenPair;
import packt.j9fprog.genericMethods.model.Grade;

import java.util.List;

public class GenericMethodsMain {
    public static void main(String args[]) {
        List<Double> someDoubles = GenericMethods.getListOfDoubles(10);
        printList(someDoubles, "initial Doubles");

        List<String> strings = GenericMethods.toListOfStrings(someDoubles);

        printList(strings, "doubles 2 strings");
        List<Integer> listOfInts = GenericMethods.sortAndTransformListOfNumbers(someDoubles);

        List<GenPair> pairs = GenericMethods.listOfSomethingToSomethingElse(someDoubles, GenericMethodsMain::pairMapper);
        printList(pairs, "doubles 2 GenPair via mapper");

        List<Grade> grades = GenericMethods.listOfSomethingToSomethingElse(someDoubles, GenericMethodsMain::gradeMapper);
        printList(grades, "doubles 2 Grade via mapper");

    }

    private static GenPair pairMapper(double input) {
        return new GenPair("I'm a pair!", input);
    }

    private static Grade gradeMapper(double input) {
        String gradeString;

        if (input > 0.5) {
            gradeString = "Pass";
        } else {
            gradeString = "Fail";
        }

        return new Grade((int) input, gradeString);
    }
    private static <T>  void printList(List<T> lst, String label){
        System.out.println("################");
        System.out.println(label);
        System.out.println("################");

        for (T element: lst){
            System.out.println(element.toString());
        }
    }
}
