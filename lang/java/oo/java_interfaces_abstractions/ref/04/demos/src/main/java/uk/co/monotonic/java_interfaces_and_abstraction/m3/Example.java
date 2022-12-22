package uk.co.monotonic.java_interfaces_and_abstraction.m3;

public class Example
{
    public static void main(String[] args)
    {
        ClientEngagementRepository repository = null;

        final Iterable<ClientEngagement> engagements = repository.find(new Query()
                .atLeastHoursWorked(5)
                .client("Pluralsight"));

        for (ClientEngagement engagement : engagements)
        {

        }
    }
}
