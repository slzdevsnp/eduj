package com.tutorialspoint.basics


class Duck {
    String getName() {
        'Duck'
    }
}

class Cat {
    String getName() {
        'Cat'
    }
}

Duck duck = new Duck()
Cat cat = new Cat()

//the below shows a dynamic typing
// list can have objects of differnet types
def list = [duck, cat]
list.each { obj ->
    println(obj.getName())
}

