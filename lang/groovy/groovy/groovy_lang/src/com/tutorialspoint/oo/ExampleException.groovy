package com.tutorialspoint.oo

class ExampleException {
    static void main(String[] args) {
        try {
            def arr = new int[3];
            arr[5] = 5;
        } catch(Exception ex) {
            println("Catching the exception");
            println(ex.getMessage())
            println(ex.getStackTrace())
        } finally {
            println("the final  bock")
        }
        println("Let's move on after the exception");
    }
}

