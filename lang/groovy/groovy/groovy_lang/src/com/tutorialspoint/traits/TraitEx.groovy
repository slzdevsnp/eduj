package com.tutorialspoint.traits

/*
Traits are a structural construct of the language which allow −

Composition of behaviors.
Runtime implementation of interfaces.
Compatibility with static type checking/compilation

 */

//trait can be seen as an interface with default implementation and state

interface Total {
    void DisplayTotal()
}

trait Marks implements Total{
    void DisplayMarks() {
        println("Display Marks");
    }
    void DisplayTotal(){
        println("Display Total")
    }
}

class Student implements Marks {
    int StudentID
    int Marks1;
}

def s = new Student()
s.StudentID=1
s.Marks1=10
s.DisplayMarks()
s.DisplayTotal()