package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.csv.CsvClientEngagementFactory;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.csv.CsvClientEngagementRepository;

public class EngagementCreationExample
{
    public static void main(String[] args) throws Exception
    {
        final ClientEngagementFactory factory = new CsvClientEngagementFactory();
        try (final ClientEngagementRepository repository = new CsvClientEngagementRepository("example.csv"))
        {
            final EngagementCreationExample engagementCreationExample
                = new EngagementCreationExample(factory, repository);

            engagementCreationExample.run();
        }
    }

    private final ClientEngagementFactory factory;
    private final ClientEngagementRepository repository;

    public EngagementCreationExample(final ClientEngagementFactory factory, ClientEngagementRepository repository)
    {

        this.factory = factory;
        this.repository = repository;
    }

    public void run()
    {
        final ClientEngagement engagement1 = factory.create("Pluralsight", 20);
        final ClientEngagement engagement2 = factory.create("Pluralsight", 150);
        final ClientEngagement engagement3 = factory.create("O'Reilly", 100);

        repository.add(engagement1);
        repository.add(engagement2);
        repository.add(engagement3);
    }
}
