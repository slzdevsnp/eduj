package org.slzdevsnp.datetime;

import org.junit.Ignore;
import org.junit.Test;

import java.time.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class DayLightSavingTimeTest {

    enum DayType {
        STANDARD_TIME, DAYLIGHT_SAVING_TIME, TO_DAYLIGHT_SAVING_TIME, TO_STANDARD_TIME
    };


    @Test
    public void givenSysDefaultVerifyZoneId() {
        ZoneId z = ZoneId.systemDefault();
        System.out.println("system zoneId:" + z);
        assertThat(z, not(nullValue()));
    }

    @Test
    public void givenPassedVerfyZoneId() {
        ZoneId z = ZoneId.of("Europe/Berlin");
        System.out.println("berlin zoneId:" + z);
        assertThat(z.toString(), is("Europe/Berlin"));
    }

    @Ignore
    @Test
    public void givenDateNowCheckDayType() {
        ZoneId z = ZoneId.of("Europe/Zurich");
        DayType dtype = getSystemDSTType(LocalDate.now(),z);
        System.out.println(String.format("date %s type:%s",LocalDate.now().toString(),dtype));
        //assertThat(dtype, is(DayType.DAYLIGHT_SAVING_TIME));

        assertThat(getSystemDSTType(LocalDate.of(2020,10,12),z), is(DayType.DAYLIGHT_SAVING_TIME) );
        assertThat(getSystemDSTType(LocalDate.of(2020,11,1),z), is(DayType.STANDARD_TIME ) );

        //23 hours in to_standard time day,  25 hours in  to_daylight_saving_time date
        assertThat(getSystemDSTType(LocalDate.of(2020,10,25),z), is(DayType.TO_STANDARD_TIME ) );
        assertThat(getSystemDSTType(LocalDate.of(2020,3,29),z), is(DayType.TO_DAYLIGHT_SAVING_TIME ) );
        //10 years ealier
        assertThat(getSystemDSTType(LocalDate.of(2010,3,28),z), is(DayType.TO_DAYLIGHT_SAVING_TIME ) );
        assertThat(getSystemDSTType(LocalDate.of(2010,10,31),z), is(DayType.TO_STANDARD_TIME ) );

    }

    @Test
    public void givenDateCheck2Zones() {
        ZoneId z = ZoneId.of("Europe/Zurich");
        ZoneId u = ZoneId.of("UTC");
        LocalDateTime  ldt = LocalDateTime.of(2018, 10,30,0,0 );
        ZonedDateTime dtz1 = ldt.atZone(z);
        ZonedDateTime dtz2 = dtz1.withZoneSameInstant(u);

        System.out.println("ldt" + ldt + " in cet: " + ldt.atZone(z) + " in utc:" + dtz2);

    }


    @Test
    public void givenDSTDayCheckHoursUTC_CET_Correct() {

        ZoneId cetZone = ZoneId.of("Europe/Zurich");

        LocalDate dstShortD = LocalDate.of(2020, 3, 29);    //02 am  23 hrs
        LocalDate dstLongD = LocalDate.of(2020, 10, 25);  //03 am   25 hrs

        System.out.println("############### Spring");
        iterateDaylyHoursMapped2CET(dstShortD,cetZone);
        iterateDaylyHoursMapped2CET(LocalDate.of(2020, 3, 30),cetZone);

        System.out.println("############## Autumn");
        iterateDaylyHoursMapped2CET(dstLongD,cetZone);
        iterateDaylyHoursMapped2CET(LocalDate.of(2020, 10, 26),cetZone);
    }

    @Test
    public void givenDSTDayCheckSequenceOfdays() {
        ZoneId cetZone = ZoneId.of("Europe/Zurich");
        int ndays = 3;
        LocalDate  dtstart1 =  LocalDate.of(2020, 3, 28);
        for (int i = 0; i<ndays; i++){
            iterateDaylyHoursMapped2CET(dtstart1.plusDays(i),cetZone);
        }

        LocalDate  dtstart2 =  LocalDate.of(2020, 10, 24);
        for (int i = 0; i<ndays; i++){
            iterateDaylyHoursMapped2CET(dtstart2.plusDays(i),cetZone);
        }

    }


        private void iterateHours(ZonedDateTime utcStart, int hmax, ZoneId zoneId){
        for(int h = 0; h<hmax; h++){
            ZonedDateTime utc_curr = utcStart.plusHours(h);
            ZonedDateTime etc_matching = utc_curr.withZoneSameInstant(zoneId);
            System.out.println("hour: " + h + " utc: " + utc_curr  + " cet: " + etc_matching) ;
        }

    }
    private void iterateDaylyHoursMapped2CET(LocalDate localStart, ZoneId zoneId){
        int hmax = 24;
        if ( getSystemDSTType(localStart,zoneId)  == DayType.TO_STANDARD_TIME){
            hmax = 25;
        }else if (getSystemDSTType(localStart,zoneId)  == DayType.TO_DAYLIGHT_SAVING_TIME ){
            hmax = 23;
        }
        LocalDateTime locDtStart = localStart.atStartOfDay();
        ZonedDateTime cetStart = locDtStart.atZone(zoneId);
        ZonedDateTime utcStart = cetStart.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("\n #### Day: " + localStart + " cet: " + cetStart + " utc: " + utcStart  + "\n-----");
        iterateHours(utcStart,hmax,zoneId);
    }





    private void printHours(LocalDateTime ldt, int h, ZoneId zoneId){
        System.out.println("tz dates for day: " + ldt.toString() + "\n-----");
        for(int i = 0; i<h;i++) {
            LocalDateTime ldtc = ldt.plusHours((long) i);
            System.out.println("hour:" + i
                    + " localDTime " + ldtc.toString()
                    + " zonedDtime " + ldtc.atZone(zoneId).toString()
                    + " utcDtime: " + ldtc.atZone(zoneId).withZoneSameInstant(ZoneId.of("UTC")));
            if (i == 23) { System.out.println("-----------"); }
        }
    }

    public static DayType getSystemDSTType(LocalDate cal, ZoneId zoneId) {
        DayType status = DayType.DAYLIGHT_SAVING_TIME;
        LocalDateTime testDate = cal.atStartOfDay();
        ZonedDateTime zdt = ZonedDateTime.of(testDate, zoneId);
        // Find type of day

        if (zdt.getZone().getRules()
                .isDaylightSavings(testDate.toInstant(zdt.getOffset())))
            status = DayType.DAYLIGHT_SAVING_TIME;
        else
            status = DayType.STANDARD_TIME;
        // Check the day after
        testDate = testDate.plusDays(1);
        zdt = ZonedDateTime.of(testDate, ZoneId.systemDefault());
        // Find type of day after
        if (zdt.getZone().getRules()
                .isDaylightSavings(testDate.toInstant(zdt.getOffset()))) {
            if (status != DayType.DAYLIGHT_SAVING_TIME)
                status = DayType.TO_DAYLIGHT_SAVING_TIME;
        } else {
            if (status == DayType.DAYLIGHT_SAVING_TIME)
                status = DayType.TO_STANDARD_TIME;
        }
        return status;
    }
}
