package org.slzdevsnp.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class DateTimeFormatTest {

    private final DateTimeFormatter formatterIsoD  = DateTimeFormatter.ISO_DATE;
    private final  DateTimeFormatter formatterIsoLd  = DateTimeFormatter.ISO_LOCAL_DATE;

    private  final DateTimeFormatter formatterIsoOffD  = DateTimeFormatter.ISO_OFFSET_DATE;


    private  final DateTimeFormatter formatterIsoZdt  = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    private final  DateTimeFormatter formatterIsoDt  = DateTimeFormatter.ISO_DATE_TIME;
    private  final DateTimeFormatter formatterIsoLdt  = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private  final DateTimeFormatter formatterIsoOffdt  = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private final ZoneId zoneZurich = ZoneId.of("Europe/Zurich");
    private final ZoneId zoneCET = ZoneId.of("CET");
    private final ZoneId zoneZ = ZoneId.of("Z");




    @Test
    public void testDateFormatting(){
        LocalDate ldate =  LocalDate.of(2022,2,28);
        log.info("date  in ISO_DATE format: {}",  ldate.format(formatterIsoD));
        log.info("date  in ISO_DATE format: {}",  ldate.format(formatterIsoLd));
        assertThat(ldate.format(formatterIsoD),  is("2022-02-28"));
        assertThat(ldate.format(formatterIsoLd),  is("2022-02-28"));

        ZonedDateTime zdt = ZonedDateTime.of(2022,2,28,22,1,59,333, zoneZurich);
        log.info("zdt  in ISO_OFFSET_DATE format: {}",  zdt.format(formatterIsoOffD));
        assertThat(zdt.format(formatterIsoOffD),  is("2022-02-28+01:00"));
    }

    @Test
    public void testDateTimeFormattingToString(){
        ZonedDateTime zdtWinter = ZonedDateTime.of(2022,2,28,22,1,59,333, zoneZurich);
        ZonedDateTime zdtSummer = ZonedDateTime.of(2022,4,28,22,1,59,666, zoneZurich);

        log.info("zdt  in ISO_ZONED_DATE_TIME format: {}",  zdtWinter.format(formatterIsoZdt));
        log.info("zdt  in ISO_DATE_TIME format: {}",  zdtWinter.format(formatterIsoDt));
        log.info("zdt  in ISO_LOCAL_DATE_TIME format: {}",  zdtWinter.format(formatterIsoLdt));
        log.info("zdt  in ISO_OFFSET_DATE_TIME format: {}",  zdtWinter.format(formatterIsoOffdt));
        log.info("zdt  in ISO_DATE_TIME format: {}",  zdtWinter.format(formatterIsoDt));
        assertThat(zdtWinter.format(formatterIsoLdt),  is("2022-02-28T22:01:59.000000333"));
        assertThat(zdtWinter.format(formatterIsoOffdt),  is("2022-02-28T22:01:59.000000333+01:00"));

        assertThat(zdtWinter.format(formatterIsoZdt),  is("2022-02-28T22:01:59.000000333+01:00[Europe/Zurich]"));
        assertThat(zdtSummer.format(formatterIsoZdt),  is("2022-04-28T22:01:59.000000666+02:00[Europe/Zurich]"));
        //convert to UTC
        assertThat(zdtSummer.withZoneSameInstant(zoneZ).format(formatterIsoZdt),  is("2022-04-28T20:01:59.000000666Z"));
    }

    @Test
    public void testDateTimeParsing(){
        ZonedDateTime zdt = ZonedDateTime.of(2022,2,28,22,1,59,333, zoneZurich);
        ZonedDateTime zdtBis = ZonedDateTime.of(2022,2,28,22,1,59,333, ZoneId.of("+0100"));
        ZonedDateTime zdtBisA = ZonedDateTime.of(2022,2,28,22,1,59,0, ZoneId.of("+0100"));

        ZonedDateTime zdt1 = ZonedDateTime.parse("2022-02-28T22:01:59.000000333+01:00[Europe/Zurich]", formatterIsoDt);
        ZonedDateTime zdt2 = ZonedDateTime.parse("2022-02-28T22:01:59.000000333+01:00", formatterIsoOffdt);

        log.info("zdt1: {}",  zdt1);
        log.info("zdt2: {}",  zdt2);

        assertThat(zdt1,  is(zdt));
        assertThat(zdt2,  is(zdtBis));

        ZonedDateTime zdt1_a = ZonedDateTime.parse("2022-02-28T22:01:59+01:00", formatterIsoDt);
        log.info("zdt1_a: {}", zdt1_a);
        assertThat(zdt1_a,  is(zdtBisA));

        // so we shall use DateTimeFormatter.ISO_DATE  and  DateTimeFormatter.ISO_DATE_TIME;

    }

   @Test
   public void testDateAndDateTimeLikronUsedFormats(){
        String localtime    = "2022-07-05T23:31:23.728+02:00";
        String localtimeCet = "2022-07-05T23:31:23.728+02:00";
        String utcTime      = "2022-07-05T21:31:23.728Z";

       ZonedDateTime zdtCet= ZonedDateTime.of(2022,7,5,23,31,23,728000000, ZoneId.of("+0200"));
       ZonedDateTime zdtZ= ZonedDateTime.of(2022,7,5,21,31,23,728000000, zoneZ);


        ZonedDateTime zdtLocalTime = ZonedDateTime.parse(localtime, DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime zdtLocalTimeCet = ZonedDateTime.parse(localtimeCet, DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime zdtUtcTime = ZonedDateTime.parse(utcTime, DateTimeFormatter.ISO_DATE_TIME);

        log.info("parsed \n zdtLocalTime: {} \n zdtLocalTimeCet : {} \n zdtUtcTime: {} ", zdtLocalTime, zdtLocalTimeCet, zdtUtcTime);
        assertThat(zdtLocalTime,  is(zdtCet));
        assertThat(zdtLocalTimeCet,  is(zdtCet));
        assertThat(zdtUtcTime,  is(zdtZ));

        //day
        String day = "2022-07-05";
        LocalDate ld = LocalDate.parse(day, DateTimeFormatter.ISO_DATE);
        assertThat(ld, is(LocalDate.of(2022,7,5)));

   }






}
