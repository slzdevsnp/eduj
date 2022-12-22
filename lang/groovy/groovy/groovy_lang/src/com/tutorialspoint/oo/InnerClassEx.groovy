package com.tutorialspoint.oo


class Outer {
    String name;

    def callInnerMethod() {
        new Inner().methodA()
    }

    class Inner {
        def methodA() {
            println(name);
        }
    }
}

Outer outobj = new Outer()
outobj.name = "Joe"
outobj.callInnerMethod()


