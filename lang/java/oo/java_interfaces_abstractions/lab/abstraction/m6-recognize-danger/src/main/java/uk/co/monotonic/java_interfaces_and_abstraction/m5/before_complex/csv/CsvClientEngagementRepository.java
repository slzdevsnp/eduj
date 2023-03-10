package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.csv;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.ClientEngagementRepository;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.Query;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.RepositoryException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CsvClientEngagementRepository implements ClientEngagementRepository
{
    private final List<CsvClientEngagement> engagements;
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
        final CsvClientEngagement csvClientEngagement = (CsvClientEngagement) clientEngagement;

        if (csvClientEngagement.getId() == NO_ID)
        {
            engagements.add(csvClientEngagement);
            csvClientEngagement.setId(nextId++);
        }
    }

    @Override
    public void remove(final ClientEngagement clientEngagement) throws RepositoryException
    {
        final CsvClientEngagement engagementToRemove = (CsvClientEngagement) clientEngagement;

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
