package uk.co.monotonic.java_interfaces_and_abstraction.m3;

import java.util.concurrent.ExecutorService;

public class RepositoryException extends RuntimeException
{
    public RepositoryException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
