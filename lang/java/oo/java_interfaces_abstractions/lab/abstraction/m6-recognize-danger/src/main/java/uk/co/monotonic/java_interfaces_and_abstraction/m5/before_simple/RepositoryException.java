package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_simple;

public class RepositoryException extends RuntimeException
{
    public RepositoryException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
