package org.szi.l04Concurrent.locks;

//thread-safe Counter class
class Counter{
    private long value;
    public synchronized  long increment() { //intrinsic lock
        value++;
        return value;
    }
    public  long getValue(){ return value;};
}
