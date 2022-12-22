package uk.co.monotonic.java_interfaces_and_abstraction.m3.in_memory;

import org.junit.After;
import org.junit.Test;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.Query;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.csv.CsvClientEngagementRepository;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static uk.co.monotonic.java_interfaces_and_abstraction.m3.database.DatabaseClientEngagementRepository.NO_ID;

public class CsvClientEngagementRepositoryTest
{
    private static final String PLURALSIGHT = "Pluralsight";

    private File file = File.createTempFile("database", "csv");
    private CsvClientEngagementRepository repository = new CsvClientEngagementRepository(file.getAbsolutePath());
    private ClientEngagement engagement = new ClientEngagement(PLURALSIGHT, 10);

    public CsvClientEngagementRepositoryTest() throws IOException
    {
    }

    @After
    public void tearDown() throws Exception
    {
        repository.close();
    }

    @Test
    public void shouldAddClientEngagement() throws Exception
    {
        repository.add(engagement);

        assertNotEquals(NO_ID, engagement.getId());
    }

    @Test
    public void shouldRemoveClientEngagement() throws Exception
    {
        repository.add(engagement);

        repository.remove(engagement);

        assertEquals(NO_ID, engagement.getId());
    }

    @Test
    public void shouldFindRelevantClientEngagements() throws Exception
    {
        repository.add(engagement);
        repository.add(new ClientEngagement("Foo", 20));
        repository.add(new ClientEngagement(PLURALSIGHT, 20));

        final Iterator<ClientEngagement> engagements =
                repository.find(new Query().atLeastHoursWorked(15).client(PLURALSIGHT)).iterator();

        assertTrue(engagements.hasNext());

        final ClientEngagement result = engagements.next();
        assertEquals(PLURALSIGHT, result.getClient());
        assertEquals(20, result.getHoursWorked());

        assertFalse(engagements.hasNext());
    }

}
