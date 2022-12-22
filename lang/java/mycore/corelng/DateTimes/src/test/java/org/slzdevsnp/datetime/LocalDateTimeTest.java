package org.slzdevsnp.datetime;


import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocalDateTimeTest {

    @Test
    public void given_now_getLocalDateTime(){
        LocalDateTime nowLdt = LocalDateTime.now();
        System.out.println("now LocalDateTime (corresponds to myzone clocl):"+nowLdt);
    }

    @Test
    public void testParsingDateTimeZ(){
         DateTimeFormatter formatterA
                 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter formatterZ
                = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        String created_dtstr = "2020-03-02T13:52:56.446Z";
        ZonedDateTime dtCrez = ZonedDateTime.parse(created_dtstr,formatterZ);
        System.out.println("ldt_cre:"+dtCrez.toString());
        System.out.println(dtCrez.getZone());
        assertThat (dtCrez , not(nullValue()));
    }

    @Test
    public void shouldParsePointConnectString() {
        final String dtstr = "17.02.2021 18:00:00";

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime  dt = LocalDateTime.parse(dtstr, formatter);
        System.out.println("dt: " +dt.format(formatter));
        assertThat(dt, isA(LocalDateTime.class));

    }
    @Test
    public void shouldParsePointConnectMetaString() {
        final String dtstr = "2020-10-21 09:37:06";

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime  dt = LocalDateTime.parse(dtstr, formatter);
        ZonedDateTime  zdt = dt.atZone(ZoneId.of("Z"));
        System.out.println("dt: " +dt.format(formatter));
        System.out.println("zdt: " + zdt);
        assertThat(dt, isA(LocalDateTime.class));

    }


    @Test
    public void testFormattedZonedNow(){
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyyMMdd'-'HH:mm:ss.SSS");
        Instant now = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        System.out.println("ldt:"+ldt);
        ZonedDateTime udt = now.atZone(ZoneId.of("UTC"));
        System.out.println("udt:"+udt);
        //print with date formatter
        System.out.println("fmt ldt:"+formatter.format(ldt));
        System.out.println("fmt udt:"+formatter.format(udt));
        System.out.println(formatter.format(Instant.now().atZone(ZoneId.of("UTC"))));

    }


    @Test
    public void givenNumbersCheckComponents() {
        LocalDateTime ldtFriday =
                LocalDateTime.of(2020, 11, 6, 22, 0, 0,
                        333000000);
        System.out.println("local datetime:" + ldtFriday);
        System.out.println("its local:" + ldtFriday.toLocalDate());
        System.out.println("its local time:" + ldtFriday.toLocalTime());
        //asserts
        assertThat(ldtFriday.toLocalDate().getDayOfWeek(), is(DayOfWeek.FRIDAY));
        assertThat(ldtFriday.toLocalTime().getHour(), is(22));
        assertThat(ldtFriday.toLocalTime().getMinute(), is(0));
        assertThat(ldtFriday.toLocalTime().getNano(), is(333000000));

    }

    @Test
    public void givenDateAddLocalTime() {

        //LocalDate ld = LocalDate.now();
        LocalDate ld = LocalDate.of(2020,11,6);
        LocalDateTime ldt = ld.atStartOfDay();
        System.out.println("today start of day local datetime:" + ldt);
        LocalTime lt = LocalTime.parse("22:01");
        ldt  = ldt.plusHours(lt.getHour()).plusMinutes(lt.getMinute());
        assertThat(ldt.toString(), is("2020-11-06T22:01"));
    }

    @Test
    public void givenZstrparseLocalDateTime() {
        String dstr = "2020-10-28T00:30:00Z";
        //LocalDateTime ldt1 = LocalDateTime.parse(dstr); ///exception
        LocalDateTime ldt2 = LocalDateTime.parse(dstr, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime ldt3 = LocalDateTime.parse(dstr, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        //LocalDateTime ldt4 = LocalDateTime.parse(dstr, DateTimeFormatter.ISO_LOCAL_DATE); //exception
        //System.out.println(ldt1);
        System.out.println(ldt2);
        System.out.println(ldt3);
        //System.out.println(ldt4);
        //assertThat(ldt1, not(nullValue()));
    }

    @Test
    public void givenStringParseLocalDateTime() {
        String ystr="2020";
        String day="2021-01-08";
        String yystr = ystr.concat("-01-01T00:00:00Z");
        System.out.println("yystr " + yystr);
        LocalDateTime ldty = LocalDateTime.parse(yystr, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime ldtd = LocalDateTime.parse(day.concat("T00:00:00Z") , DateTimeFormatter.ISO_DATE_TIME);

        assertThat(ldty, isA(LocalDateTime.class));
        assertThat(ldtd, isA(LocalDateTime.class));

        System.out.println("ldty " + ldty);
        System.out.println("ldtd " + ldtd);
    }
}
