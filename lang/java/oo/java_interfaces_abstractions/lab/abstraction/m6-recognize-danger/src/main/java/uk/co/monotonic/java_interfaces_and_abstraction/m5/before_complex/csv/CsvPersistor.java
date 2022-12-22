package uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import uk.co.monotonic.java_interfaces_and_abstraction.m5.before_complex.RepositoryException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    List<CsvClientEngagement> load()
    {
        File file = new File(path);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                throw new RepositoryException("unable to create " + path, e);
            }
        }

        try (CSVReader reader = new CSVReader(new FileReader(path)))
        {
            final List<CsvClientEngagement> engagements = new ArrayList<>();
            final Iterator<String[]> iterator = reader.iterator();
            while (iterator.hasNext())
            {
                final String[] row = iterator.next();
                final String client = row[CLIENT_COL];
                final int hoursWorked = Integer.parseInt(row[HOURS_WORKED_COL]);
                engagements.add(new CsvClientEngagement(client, hoursWorked));
            }
            return engagements;
        }
        catch (IOException e)
        {
            throw new RepositoryException("unable to load contents of " + path, e);
        }
    }

    void save(final List<CsvClientEngagement> engagements)
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
