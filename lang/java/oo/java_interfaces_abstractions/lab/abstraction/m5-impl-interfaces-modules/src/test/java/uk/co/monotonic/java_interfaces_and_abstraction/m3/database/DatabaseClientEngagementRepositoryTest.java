package uk.co.monotonic.java_interfaces_and_abstraction.m3.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.Query;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static uk.co.monotonic.java_interfaces_and_abstraction.m3.database.DatabaseClientEngagementRepository.NO_ID;

public class DatabaseClientEngagementRepositoryTest
{
    private static final String PLURALSIGHT = "Pluralsight";

    static
    {
        try
        {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        }
        catch (ClassNotFoundException e)
        {
            throw new Error(e);
        }
    }

    private DatabaseClientEngagementRepository repository;
    private ClientEngagement engagement = new ClientEngagement(PLURALSIGHT, 10);

    @Before
    public void setUp() throws SQLException
    {
        final File dbDir = new File("db");
        if (dbDir.listFiles() != null)
        {
            for (File file : dbDir.listFiles())
            {
                assertTrue(file.delete());
            }
        }
        dbDir.delete();

        final Connection connection = DriverManager.getConnection(
            "jdbc:hsqldb:db/clients_database", "SA", "");


        repository = new DatabaseClientEngagementRepository(connection);
    }

    @After
    public void tearDown() throws SQLException
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
