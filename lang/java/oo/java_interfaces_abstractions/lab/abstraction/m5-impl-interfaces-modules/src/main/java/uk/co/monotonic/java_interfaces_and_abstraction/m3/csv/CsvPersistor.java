package uk.co.monotonic.java_interfaces_and_abstraction.m3.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.ClientEngagement;
import uk.co.monotonic.java_interfaces_and_abstraction.m3.RepositoryException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CsvPersistor
{
    private static final int CLIENT_COL = 0;
    private static final int HOURS_WORKED_COL = 1;

    private final String path;

    CsvPersistor(final String path)
    {
        this.path = path;
    }

    List<ClientEngagement> load()
    {
        try (CSVReader reader = new CSVReader(new FileReader(path)))
        {
            final List<ClientEngagement> engagements = new ArrayList<>();
            final Iterator<String[]> iterator = reader.iterator();
            while (iterator.hasNext())
            {
                final String[] row = iterator.next();
                final String client = row[CLIENT_COL];
                final int hoursWorked = Integer.parseInt(row[HOURS_WORKED_COL]);
                engagements.add(new ClientEngagement(client, hoursWorked));
            }
            return engagements;
        }
        catch (IOException e)
        {
            throw new RepositoryException("unable to load contents of " + path, e);
        }
    }

    void save(final List<ClientEngagement> engagements)
    {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(path)))
        {
            engagements.forEach(engagement ->
            {
                final String[] row = {
                    engagement.getClient(),
                    String.valueOf(engagement.getHoursWorked())
                };
                csvWriter.writeNext(row);
            });
        }
        catch (IOException e)
        {
            throw new RepositoryException("Unable to save contents of " + path, e);
        }
    }
}
