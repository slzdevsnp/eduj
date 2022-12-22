package uk.co.monotonic.java_interfaces_and_abstraction.m5.after_simple;

public class ClientEngagement
{

    private int id;
    private final String client;
    private final int hoursWorked;
    private final double revenueReceived;

    public ClientEngagement(final String client, final int hoursWorked, final double revenueReceived)
    {
        this.client = client;
        this.hoursWorked = hoursWorked;
        this.revenueReceived = revenueReceived;
    }

    public String getClient()
    {
        return client;
    }

    public int getHoursWorked()
    {
        return hoursWorked;
    }

    public double getRevenueReceived()
    {
        return revenueReceived;
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
                ", revenueReceived=" + revenueReceived +
                '}';
    }
}
