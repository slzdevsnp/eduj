package org.slzdevsnp.datetime;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocalDateTest {



    @Test
    public void givenLocalDateToday() {
        LocalDate  dtnow = LocalDate.now();
        System.out.println("localdate: "+ dtnow);
    }


    @Test
    public void givenFormatterMakeLocalDate() {
        DateTimeFormatter formatterCH
                = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterAltUS
                = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String todayStr = "02.10.2020";
        LocalDate parsedDate = LocalDate.parse(todayStr, formatterCH);
        assertThat(parsedDate.toString(), is("2020-10-02"));

        String formattedCh = parsedDate.format(formatterCH);
        assertThat(formattedCh, is("02.10.2020"));

        assertThat(parsedDate.format(formatterAltUS), is("2020.10.02"));
    }

    @Test
    public void givenChStringGetEpoch() {
        DateTimeFormatter formatterCH
                = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String todayStr = "02.10.2020";
        LocalDate ldate = LocalDate.parse(todayStr, formatterCH);
        assertThat(ldate.toEpochDay(), is(18537L));
        int i = Math.toIntExact(ldate.toEpochDay());
        assertThat(i, is(18537));

    }
}
