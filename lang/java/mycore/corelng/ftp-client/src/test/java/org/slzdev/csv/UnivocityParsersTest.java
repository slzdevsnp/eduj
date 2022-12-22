package org.slzdev.csv;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@Slf4j
public class UnivocityParsersTest {
    private CsvParser parser1;
    private CsvParserSettings settings1;

    private CsvParser parser2;
    private CsvParserSettings settings2;

    private static final String file1 = "csvdata/example.csv";
    private static final String file2 = "csvdata/5005320_Pwr_PCA_CON_ECop_AUT_F_2021-02-18.CSV";
    private static final String file3 = "csvdata/5113563_Hyd_PCA_Temp_ECMND_Perc_ESP_F_2021-02-15.CSV";
    private static final String file4a = "csvdata/5002060_Pwr_EEX_PRO_Solar_DEU_A_2021-02-15.CSV";

    @Before
    public void setup() {
        settings1 = new CsvParserSettings();
        settings1.getFormat().setLineSeparator("\n");
        settings1.setHeaderExtractionEnabled(true);
        parser1 = new CsvParser(settings1);

        settings2 = new CsvParserSettings();
        settings2.getFormat().setLineSeparator("\n");
        settings2.getFormat().setDelimiter("|");
        settings2.setNumberOfRowsToSkip(1L); //skips the first line
        settings2.setHeaderExtractionEnabled(true);
        parser2 = new CsvParser(settings2);

    }

    @Test
    public void shouldReadBasicFile1() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file1);
        List<String[]> allRows = parser1.parseAll(is);
        //log
        allRows.stream().forEach(x -> {
            log.debug("0: {}  1: {} 2: {}  3: {} 4: {}", x[0], x[1], x[2], x[3], x[4]);
        });
        assertThat(allRows.size(), greaterThan(0));
    }

    @Test
    public void shouldBasicParse() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file1);
        parser1.beginParsing(is);
        String[] row;
        int cnt = 0;
        while ((row = parser1.parseNext()) != null) {
            cnt++;
            log.debug("row - {}", Arrays.toString(row));
        }
        assertThat(cnt, is(5));

    }

    @Test
    public void shoudlParseWithIterator() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file1);
        //using actual iterator
        for (String[] row : parser1.iterate(is)) {
            log.debug("r - {}", Arrays.toString(row));
        }
    }

    @Test
    public void shouldParseTimeSeriesWithIterator() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file2);
        long rowcnt = 0;
        for (String[] row : parser2.iterate(is)) {
            rowcnt++;
            log.debug("r - {}", Arrays.toString(row));
        }
        assertThat(rowcnt, is(666L));
    }

    @Test
    public void shouldParseWRowProcessor() {
        RowListProcessor rowProcessor = new RowListProcessor();
        settings2.setProcessor(rowProcessor);

        parser2 = new CsvParser(settings2);

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file2);
        parser2.parse(is);
        String[] headers = rowProcessor.getHeaders();
        log.debug("headers - {}", Arrays.toString(headers));
        List<String[]> rows = rowProcessor.getRows();
        log.debug("row 0 - {}", Arrays.toString(rows.get(0)));
        assertThat(rows.size(), is(666));
    }

    @Test
    public void shouldProcessWithPcBeanForecast() {
        BeanListProcessor<PointConnectDataPoint> rowProcessor
                = new BeanListProcessor<>(PointConnectDataPoint.class);
        settings2.setProcessor(rowProcessor);
        parser2 = new CsvParser(settings2);

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file2);
        parser2.parse(is);
        List<PointConnectDataPoint> dpoints = rowProcessor.getBeans();
        assertThat(dpoints.size(), is(666));
        dpoints.stream().forEach(x -> log.debug(x.toString()));
    }

    @Test
    public void shouldProcessWithPcBeanActual() {
        BeanListProcessor<PointConnectDataPoint> rowProcessor
                = new BeanListProcessor<>(PointConnectDataPoint.class);
        settings2.setProcessor(rowProcessor);
        parser2 = new CsvParser(settings2);

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file4a);
        parser2.parse(is);
        List<PointConnectDataPoint> dpoints = rowProcessor.getBeans();
        assertThat(dpoints.size(), is(425));
        dpoints.stream().forEach(x -> log.debug(x.toString()));
    }

    @Test
    public void parseWithCsvRoutine() {
        CsvRoutines routines = new CsvRoutines(settings2);
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file4a);
        for(PointConnectDataPoint rec : routines.iterate(PointConnectDataPoint.class, is)){
            log.debug("rec - {}", rec);
        }
    }
}
