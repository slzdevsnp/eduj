package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.database;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.ClientEngagement;

class DatabaseClientEngagement implements ClientEngagement
{
    private int id;
    private final String client;
    private final int hoursWorked;

    public DatabaseClientEngagement(final String client, final int hoursWorked)
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
