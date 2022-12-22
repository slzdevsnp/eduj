package uk.co.monotonic.java_interfaces_and_abstraction.m3.database;

import uk.co.monotonic.java_interfaces_and_abstraction.m3.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

class DatabaseIterator implements Iterator<ClientEngagement>
{
    private ResultSet resultSet;

    DatabaseIterator(final ResultSet resultSet)
    {
        this.resultSet = resultSet;
    }

    @Override
    public boolean hasNext()
    {
        try
        {
            return resultSet.next();
        }
        catch (SQLException e)
        {
            throw new RepositoryException("Unable to iterate the result set", e);
        }
    }

    @Override
    public ClientEngagement next()
    {
        try
        {
            final int id = resultSet.getInt(1);
            final String client = resultSet.getString(2);
            final int hoursWorked = resultSet.getInt(3);
            final ClientEngagement engagement = new ClientEngagement(client, hoursWorked);
            engagement.setId(id);
            return engagement;
        }
        catch (SQLException e)
        {
            throw new RepositoryException("unable to read engagement record", e);
        }
    }
}
