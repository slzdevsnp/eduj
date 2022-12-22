package com.monotonic.generics._6_compatibility.c_implications_of_erasure;

public class UncompilableException/*<T>*/  extends Exception  //UncompilableException<T> is banned
{
    public static void main(String[] args)
    {
        try
        {
            throw new UncompilableException();
        }
        catch (UncompilableException/*<T>*/ e)
        {
            System.out.println("we just threw UncompilableException");
            e.printStackTrace();
        }
    }
}
