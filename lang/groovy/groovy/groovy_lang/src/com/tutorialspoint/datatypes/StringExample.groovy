package com.tutorialspoint.datatypes

class StringExample {
    static void main(String[] args) {
        String sample = "Hello world";

        //Print the 1st character in the string starting from the back
        println(sample[-1])
        println(sample[4])  // Print the 5 character in the string
        println(sample[1..2]) //Prints a string starting from Index 1 to 2
        println(sample[4..2]) //Prints a string starting from Index 4 back to 2

        println('aa' + 'bb') //concatentation
        println 'aa'*2  //repetition
        println  "string length: ${'aa'.length()} "

        println "minus op on strings  abba - ba: is  ${'abba'.minus('ba')} "
        println "concatenation aaa and bbb: ${'aaa'.concat('bbb')}"
    }
}
