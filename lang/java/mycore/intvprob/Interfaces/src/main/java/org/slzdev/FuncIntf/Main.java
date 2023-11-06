package org.slzdev.FuncIntf;

public class Main {

    public static void main(String[] args) {

      Greeting greeting  = (name) -> System.out.println("Hello, " + name);
      greeting.sayHello("Alice"); //prints Hello, Alice

    }

}
