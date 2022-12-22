package uk.co.monotonic.java_interfaces_and_abstraction.m5.after_complex.database;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.after_complex.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.after_complex.ClientEngagementFactory;

public class DatabaseClientEngagementFactory implements ClientEngagementFactory
{
    @Override
    public ClientEngagement create(final String client, final int hoursWorked, final double revenueReceived)
    {
        return new DatabaseClientEngagement(client, hoursWorked, revenueReceived);
    }
}
