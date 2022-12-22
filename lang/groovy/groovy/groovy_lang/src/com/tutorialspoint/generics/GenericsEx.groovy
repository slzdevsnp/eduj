package com.tutorialspoint.generics


public class ListType<T> {
    private T localt;

    public T get() {
        return this.localt;
    }

    public void set(T plocal) {
        this.localt = plocal;
    }
}


ListType<String> l = new ListType<>()
l.set('first string')
println "saved element: ${l.get()}"
