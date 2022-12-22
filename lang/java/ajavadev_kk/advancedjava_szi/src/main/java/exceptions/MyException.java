package exceptions;

public class MyException extends Exception {

    //default constr
    public MyException()
    {
        this("my default message");
    }
    //constructor with msg
    public MyException(String message)
    {
        super(message);
    }
}
