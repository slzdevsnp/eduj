package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.database;

import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.ClientEngagementRepository;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.Query;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.RepositoryException;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DatabaseClientEngagementRepository implements ClientEngagementRepository
{
    private Connection connection;

    private final PreparedStatement addStatement;
    private final PreparedStatement removeStatement;

    public DatabaseClientEngagementRepository(final Connection connection)
    {
        this.connection = connection;

        try
        {
            connection.createStatement().executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " +
                        "client_engagements (" +
                        "id INT IDENTITY," +
                        "client VARCHAR(15) NOT NULL," +
                        "hoursWorked INT NOT NULL" +
                        ")");

            addStatement = connection.prepareStatement(
                    "INSERT INTO client_engagements (client, hoursWorked) VALUES (?, ?)",
                    RETURN_GENERATED_KEYS);

            removeStatement = connection.prepareStatement("delete from client_engagements where id = ?");
        }
        catch (SQLException e)
        {
            throw new RepositoryException("Could not initialize repository", e);
        }
    }

    @Override
    public void add(final ClientEngagement clientEngagement) throws RepositoryException
    {
        final DatabaseClientEngagement databaseClientEngagement = (DatabaseClientEngagement) clientEngagement;

        try
        {
            addStatement.setString(1, clientEngagement.getClient());
            addStatement.setInt(2, clientEngagement.getHoursWorked());
            addStatement.executeUpdate();

            final ResultSet keys = addStatement.getGeneratedKeys();
            if (keys.next())
            {
                final int id = keys.getInt("id");
                databaseClientEngagement.setId(id);
            }
        }
        catch (SQLException e)
        {
            throw new RepositoryException("Failed to add: " + clientEngagement, e);
        }
    }

    @Override
    public void remove(final ClientEngagement clientEngagement) throws RepositoryException
    {
        final DatabaseClientEngagement databaseClientEngagement = (DatabaseClientEngagement) clientEngagement;

        try
        {
            removeStatement.setInt(1, databaseClientEngagement.getId());
            if (removeStatement.executeUpdate() > 0)
            {
                databaseClientEngagement.setId(NO_ID);
            }
        }
        catch (SQLException e)
        {
            throw new RepositoryException("Failed to remove: " + clientEngagement, e);
        }
    }

    @Override
    public Iterable<ClientEngagement> find(final Query query) throws RepositoryException
    {
        try
        {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                        "FROM  client_engagements " +
                        "WHERE " + generateWhere(query));

            return new DatabaseIterable(resultSet);
        }
        catch (SQLException e)
        {
            throw new RepositoryException("Failed to query: " + query, e);
        }
    }

    private String generateWhere(final Query query)
    {
        final StringBuilder builder = new StringBuilder();

        final boolean hasClientClause = query.getClient() != null;
        if (hasClientClause)
        {
            builder.append("client = '")
                   .append(query.getClient())
                   .append("'");
        }

        final boolean hasHoursWorkedClause = query.getAtLeastHoursWorked() > 0;
        if (hasHoursWorkedClause)
        {
            if (hasClientClause)
            {
                builder.append(" and ");
            }

            builder.append(" hoursWorked > ")
                   .append(query.getAtLeastHoursWorked());
        }

        if (!hasClientClause && !hasHoursWorkedClause)
        {
            return "true";
        }

        return builder.toString();
    }

    @Override
    public void close() throws RepositoryException
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            throw new RepositoryException("Failed to close DB connection", e);
        }
    }
}
