package org.szi.l04Concurrent.locks;

public class MyQueue {
    class Node {
        Object value;
        Node next;
    }

    ;
    private Node head;
    private Node tail;

    public synchronized void add(Object newValue) {
        Node n = new Node();
        if (head == null){
            head = n;
        }
        else {
            tail.next = n;
        }
        tail = n;
        tail.value = newValue;
        notifyAll();  //we put a value into the queue, wake up all waiting threads.
    }

    public synchronized Object remove() {
        if (head == null) return null;
        Node n = head;
        head = n.next;
        return n.value;
    }

    public synchronized Object take() throws InterruptedException {
        while (head == null) wait(); // put this therad to waiting before somebody adds at element to empty queue
        Node n = head;
        head = n.next;
        return n.value;
    }
}