package uk.co.monotonic.java_interfaces_and_abstraction.m5.after_complex.csv;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.after_complex.ClientEngagement;

class CsvClientEngagement implements ClientEngagement
{
    private int id;
    private final String client;
    private final int hoursWorked;
    private final double revenueReceived;

    public CsvClientEngagement(final String client, final int hoursWorked, final double revenueReceived)
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

    @Override
    public double getRevenueReceived()
    {
        return revenueReceived;
    }
}
