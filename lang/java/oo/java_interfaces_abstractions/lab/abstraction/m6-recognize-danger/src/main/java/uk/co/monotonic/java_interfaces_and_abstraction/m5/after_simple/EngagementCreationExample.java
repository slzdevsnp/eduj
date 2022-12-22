package uk.co.monotonic.java_interfaces_and_abstraction.m5.after_simple;


import uk.co.monotonic.java_interfaces_and_abstraction.m5.after_simple.csv.CsvClientEngagementRepository;

import java.io.File;

public class EngagementCreationExample
{
    public static void main(String[] args) throws Exception
    {
        File file = new File("example.csv");
        if (file.exists())
        {
            file.delete();
        }

        try (final ClientEngagementRepository repository = new CsvClientEngagementRepository(file.getAbsolutePath()))
        {
            final EngagementCreationExample engagementCreationExample
                = new EngagementCreationExample(repository);

            engagementCreationExample.run();
        }
    }

    private final ClientEngagementRepository repository;

    public EngagementCreationExample(ClientEngagementRepository repository)
    {

        this.repository = repository;
    }

    public void run()
    {
        final ClientEngagement engagement1 = new ClientEngagement("Pluralsight", 20, 100);
        final ClientEngagement engagement2 = new ClientEngagement("Pluralsight", 150, 1000);
        final ClientEngagement engagement3 = new ClientEngagement("O'Reilly", 100, 10_000);

        repository.add(engagement1);
        repository.add(engagement2);
        repository.add(engagement3);
    }
}
