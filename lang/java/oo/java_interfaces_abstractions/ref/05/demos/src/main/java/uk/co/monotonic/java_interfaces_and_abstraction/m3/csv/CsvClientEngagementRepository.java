package uk.co.monotonic.java_interfaces_and_abstraction.m3.csv;

import uk.co.monotonic.java_interfaces_and_abstraction.m3.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.ClientEngagementRepository;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.Query;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CsvClientEngagementRepository implements ClientEngagementRepository
{
    private final List<ClientEngagement> engagements;
    private final CsvPersistor persistor;

    private int nextId = 1;

    public CsvClientEngagementRepository(final String path)
    {
        persistor = new CsvPersistor(path);
        engagements = persistor.load();
    }

    @Override
    public void add(final ClientEngagement clientEngagement) throws RepositoryException
    {
        if (clientEngagement.getId() == NO_ID)
        {
            engagements.add(clientEngagement);
            clientEngagement.setId(nextId++);
        }
    }

    @Override
    public void remove(final ClientEngagement engagementToRemove) throws RepositoryException
    {
        if (engagements.removeIf(engagement -> engagement.getId() == engagementToRemove.getId()))
        {
            engagementToRemove.setId(NO_ID);
        }
    }

    @Override
    public Iterable<ClientEngagement> find(final Query query) throws RepositoryException
    {
        return engagements.stream().filter(filterOf(query)).collect(Collectors.toList());
    }

    private Predicate<? super ClientEngagement> filterOf(final Query query)
    {
        final String client = query.getClient();
        return engagement -> engagement.getHoursWorked() >= query.getAtLeastHoursWorked() &&
                             (client == null || engagement.getClient().equals(client));
    }

    @Override
    public void close() throws Exception
    {
        persistor.save(engagements);
    }
}
