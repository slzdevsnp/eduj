package org.slzdevsnp.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class DurationStartOfTest {

    @Test
    public void givenNowCheckDuration() throws InterruptedException {

        ZonedDateTime zdt1 = ZonedDateTime.now();
        Thread.sleep(10L);
        ZonedDateTime zdt2 = ZonedDateTime.now();
        Duration  dlt = Duration.between(zdt1, zdt2);
        System.out.println("duration in miliis " +  dlt.toMillis());
        assertThat(dlt.toMillis(), greaterThanOrEqualTo(10L));
        assertThat(dlt.toMillis(), lessThan(20L));
    }

   @Test
    public void givenZonedDateTimeFindStartOfDay(){
       String dtstr = "2020-06-09T07:43:13.49Z";
       ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
       ZonedDateTime zdtStart = zdt.toLocalDate().atStartOfDay(zdt.getZone());
       assertThat(zdtStart, is(ZonedDateTime.parse("2020-06-09T00:00Z")));
       assertThat(ZonedDateTime.parse("2020-01-02T23:59:59Z").toLocalDate().atStartOfDay(ZoneId.of("Z")),
               is(ZonedDateTime.parse("2020-01-02T00:00Z")));
   }

    @Test
    public void givenZonedDateTimeFindStartOfWeek(){
        String dtstr = "2022-04-14T10:01:01.59Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        ZonedDateTime zdtWstart = zdt.toLocalDate()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay(zdt.getZone());
        assertThat(zdtWstart, is(ZonedDateTime.parse("2022-04-11T00:00Z")));
    }

    @Test
    public void givenZonedDateTimeFindStartOfMonth(){
        String dtstr = "2022-04-14T10:01:01.59Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        ZonedDateTime zdtStart = zdt.toLocalDate()
                .with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(zdt.getZone());
        log.debug("transformed date: {}", zdtStart);
        assertThat(zdtStart, is(ZonedDateTime.parse("2022-04-01T00:00Z")));
    }

    @Test
    public void givenZonedDateTimeFindStartOfYear(){
        String dtstr = "2022-04-14T10:01:01.59Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        ZonedDateTime zdtStart = zdt.toLocalDate()
                .with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(zdt.getZone());
        log.debug("transformed date: {}", zdtStart);
        assertThat(zdtStart, is(ZonedDateTime.parse("2022-01-01T00:00Z")));
    }

    @Test
    public void givenZonedDateTimeFindStartOfQuorter(){
        String dtstr = "2022-04-14T10:01:01.59Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        ZonedDateTime zdtStart = zdt.toLocalDate()
                .with(zdt.toLocalDate().getMonth().firstMonthOfQuarter())
                .with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(zdt.getZone());
        log.debug("transformed date: {}", zdtStart);
        assertThat(zdtStart, is(ZonedDateTime.parse("2022-04-01T00:00Z")));

        ZonedDateTime zdt1 = ZonedDateTime.parse("2022-03-31T23:00:00Z");
        assertThat( zdt1.toLocalDate()
                .with(zdt1.toLocalDate().getMonth().firstMonthOfQuarter())
                .with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(zdt.getZone()),
                is(ZonedDateTime.parse("2022-01-01T00:00:00Z")));
    }

    //should we do things like parsing hours and minutes


    @Test
    public void givenZonedDateTimeFindStartOfHour(){
        String dtstr = "2022-04-14T10:01:02.599Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        ZonedDateTime zdtShr =
        zdt.minusNanos(zdt.getNano()).minusSeconds(zdt.getSecond()).minusMinutes(zdt.getMinute());
        log.debug("transformed date: {}", zdtShr);
        assertThat(zdtShr, is(ZonedDateTime.parse("2022-04-14T10:00Z")));
    }

    @Test
    public void givenZonedDateTimeFindStartOfMinute(){
        String dtstr = "2022-04-14T10:01:02.599Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        ZonedDateTime zdtShr =
                zdt.minusNanos(zdt.getNano()).minusSeconds(zdt.getSecond());
        log.debug("transformed date: {}", zdtShr);
        assertThat(zdtShr, is(ZonedDateTime.parse("2022-04-14T10:01Z")));
    }

}
