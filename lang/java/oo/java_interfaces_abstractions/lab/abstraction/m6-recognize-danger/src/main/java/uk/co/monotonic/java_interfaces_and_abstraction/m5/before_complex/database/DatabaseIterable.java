package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.database;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.ClientEngagement;

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
