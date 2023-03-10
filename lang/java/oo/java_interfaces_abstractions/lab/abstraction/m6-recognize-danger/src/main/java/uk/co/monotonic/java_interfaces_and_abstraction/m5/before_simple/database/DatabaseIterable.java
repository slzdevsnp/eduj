package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_simple.database;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_simple.ClientEngagement;

import java.sql.ResultSet;
import java.util.Iterator;

class DatabaseIterable implements Iterable<ClientEngagement>
{
    private ResultSet resultSet;

    public DatabaseIterable(final ResultSet resultSet)
    {
        this.resultSet = resultSet;
    }

    @Override
    public Iterator<ClientEngagement> iterator()
    {
        return new DatabaseIterator(resultSet);
    }
}
