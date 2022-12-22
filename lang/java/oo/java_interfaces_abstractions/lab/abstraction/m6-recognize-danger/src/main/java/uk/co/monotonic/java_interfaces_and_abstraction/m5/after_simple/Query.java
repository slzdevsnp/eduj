package uk.co.monotonic.java_interfaces_and_abstraction.m5.after_simple;

public class Query
{
    private String client;
    private int atLeastHoursWorked;

    public Query client(final String client)
    {
        this.client = client;

        return this;
    }

    public Query atLeastHoursWorked(final int atLeastHoursWorked)
    {
        this.atLeastHoursWorked = atLeastHoursWorked;

        return this;
    }

    public String getClient()
    {
        return client;
    }

    public int getAtLeastHoursWorked()
    {
        return atLeastHoursWorked;
    }

    @Override
    public String toString()
    {
        return "Query{" +
                "client='" + client + '\'' +
                ", atLeastHoursWorked=" + atLeastHoursWorked +
                '}';
    }
}
