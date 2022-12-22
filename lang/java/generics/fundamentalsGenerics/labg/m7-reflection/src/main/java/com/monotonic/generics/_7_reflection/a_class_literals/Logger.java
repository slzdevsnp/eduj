package com.monotonic.generics._7_reflection.a_class_literals;

public class Logger
{
    private String value;

    public Logger(String value)
    {
        this.value = value;
    }

    public void log()
    {
        System.out.println(value);
    }
}
