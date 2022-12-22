package com.tutorialspoint.basics

class Methods {
    // static methods (local
    static int sum(int a, int b){
        return a+b
    }
    //public
    double product(double a, double b){
        return a*b
    }


    //instance methods
    def DisplayName(){
        println("this is how methods work in groovy")
    }
    def DisplayName(String msg){
        println("method with a param: "+ msg)
    }

    //run from inside main in intellij
    static void main(String[] args){
        Methods m = new Methods()
        m.DisplayName()
        m.DisplayName('hoho')
        //static method
        println("sum of 2 ints: " + Methods.sum(5,8))
    }
}


//run at this place in intellij
Methods m = new Methods()
println("product : "+ m.product(10.0, 5.0))



