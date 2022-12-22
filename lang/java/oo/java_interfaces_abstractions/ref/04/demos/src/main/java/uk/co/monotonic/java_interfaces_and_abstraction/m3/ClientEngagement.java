package uk.co.monotonic.java_interfaces_and_abstraction.m3;

public class ClientEngagement
{
    private final String client;
    private final int hoursWorked;

    private int id;

    public ClientEngagement(final String client, final int hoursWorked)
    {
        this.client = client;
        this.hoursWorked = hoursWorked;
    }

    public String getClient()
    {
        return client;
    }

    public int getHoursWorked()
    {
        return hoursWorked;
    }

    public int getId()
    {
        return id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClientEngagement{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", hoursWorked=" + hoursWorked +
                '}';
    }
}
