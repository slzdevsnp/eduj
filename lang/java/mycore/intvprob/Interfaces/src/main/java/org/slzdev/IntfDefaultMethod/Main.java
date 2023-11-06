package org.slzdev.IntfDefaultMethod;

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car(); //implemented long time ago
        myCar.drive();
        myCar.honk(); // i can  call a new method from interface withtout modifing old Car class

        F1Car myBolid = new F1Car();
        myBolid.drive();
        myBolid.honk();  // in newly added type of car class i can have a non-default method impl

    }
}
