package com.monotonic.generics._1_what_and_why_of_generics;

public class CircularBuffer
{
    private final Object[] buffer;
    private int readCursor = 0;
    private int writeCursor = 0;

    public CircularBuffer(int size)
    {
        buffer = new Object[size];
    }

    // puts element in a a queue
    public boolean offer(Object value)
    {
        if (buffer[writeCursor] != null)
        {
            return false;
        }

        buffer[writeCursor] = value;
        writeCursor = next(writeCursor);
        return true;
    }

    //puls element out of a queue
    public Object poll() {
        Object value = buffer[readCursor];
        if (value != null)
        {
            buffer[readCursor] = null;
            readCursor = next(readCursor);
        }
        return value;
    }

    private int next(int index)
    {
        return (index + 1) % buffer.length;
    }
}
