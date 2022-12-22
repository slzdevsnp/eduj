package org.slzdevsnp.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZonedDateTimeTest {


    private DateTimeFormatter formatterZ  = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    private DateTimeFormatter formatterL  = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private DateTimeFormatter formatterO  = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private DateTimeFormatter formatterOd  = DateTimeFormatter.ISO_OFFSET_DATE;


    @Test
    public void given_now_fromTs_inLocalTimeZone_toUtc() {
        LocalDateTime ldt = LocalDateTime.now();

        ZoneId zZoneId = ZoneId.of("UTC");
        ZoneId sysZone = ZoneId.systemDefault(); //from this system

        ZonedDateTime zldt = ldt.atZone(sysZone);
        //converting datetime from a system zone to utc zone
        ZonedDateTime zdtutc = zldt.withZoneSameInstant(ZoneId.of("UTC"));

        System.out.printf("utc zone: %s  system zone: %s\n", zZoneId, sysZone);
        System.out.printf("now dt %s in local zone: %s\n", zldt, sysZone);
        System.out.printf("now dt %s in utc zone %s\n", zdtutc, zZoneId);

        assert (zldt.toInstant().toEpochMilli() == zdtutc.toInstant().toEpochMilli());
    }

    @Test
    public void testParsingZDtWithDifferentDateTimeFormatters() {
        String dtstr = "2020-03-03T00:00:00.009+01:00[Europe/Zurich]";

        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        System.out.println("zdt:" + zdt);
        System.out.println("zone offset:" + zdt.getOffset());
        System.out.println("zone:" + zdt.getZone());
        assertThat(zdt.getZone(), is(ZoneId.of("Europe/Zurich")));
        assertThat(zdt.getOffset(), is(ZoneOffset.of("+01:00")));
        assertThat(zdt.toLocalDateTime().toString(), is("2020-03-03T00:00:00.009"));
        assertThat(zdt.format(formatterZ), is("2020-03-03T00:00:00.009+01:00[Europe/Zurich]"));
        assertThat(zdt.format(formatterO), is("2020-03-03T00:00:00.009+01:00"));
        assertThat(zdt.format(formatterOd), is("2020-03-03+01:00"));
        assertThat(zdt.format(formatterL), is("2020-03-03T00:00:00.009"));

    }



    @Test
    public void given_UTCDateTime_toLocalDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime ldtz = ldt.atZone(ZoneId.of("UTC"));
        LocalDateTime ldtl = ldtz.toLocalDateTime();
        System.out.printf("zoneDatetime %s converted to local datetime %s  by stripping zone info\n", ldtz, ldtl);
        assert (true);
    }


    @Test
    void givenStringZutcParseZonedDateTime() {
        String sdt = "2020-10-26T19:13:14.376Z[UTC]";
        ZonedDateTime zdt = ZonedDateTime.parse(sdt, formatterZ);
        System.out.println(zdt);
        assertThat(zdt.format(formatterZ), is(sdt));
        assertThat(zdt.getZone(), is(ZoneId.of("UTC")));
    }

    @Test
    void givenStringZutcParseZonedDateTimeAlt() {
        String sdt = "2021-03-15T08:28:02.826Z";
        ZonedDateTime zdt = ZonedDateTime.parse(sdt, formatterZ);
        System.out.println(zdt);
        assertThat(zdt.format(formatterZ), is(sdt));
    }

    @Test
    public void given_ZUTCStr_parse_ZonedDateTime() {
        String dtstr = "2020-01-02T09:15Z[UTC]";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        System.out.println("zdt:" + zdt);
        System.out.println("zone offset:" + zdt.getOffset());
        assert (zdt.getOffset().equals(ZoneOffset.UTC));
        assert (zdt != null);
    }




    @Test
    public void testParsingDateTimeZoneAsOffset() {
        String dtstr = "2022-07-06T00:00:00+02:00";
        //  DateTimeFormatter.ISO_ZONED_DATE_TIME  is used by default
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        System.out.println("zdt:" + zdt);
        System.out.println("zone offset:" + zdt.getOffset());
        System.out.println("zone:" + zdt.getZone());
        assertThat(zdt.getZone(), is(ZoneId.of("+02:00")));
        assertThat(zdt.format(formatterZ), is("2022-07-06T00:00:00+02:00"));
        assertThat(zdt.toString(),         is("2022-07-06T00:00+02:00"));
    }





    @Test
    public void given_ZUTCStr_epochmillis() {
        String dtstr = "2020-01-02T09:15Z[UTC]";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        long e = zdt.toInstant().toEpochMilli();
        System.out.println("zoned dt: " + zdt);
        System.out.println("epoch milli: " + e);

        String dtstr1 = "2020-01-02T10:15:00+01:00";
        ZonedDateTime zdt1 = ZonedDateTime.parse(dtstr1);
        long e1 = zdt1.toInstant().toEpochMilli();
        assert (e1 == e);
    }

    @Test
    public void given_ZStr_epochmillis() {
        String dtstr = "2020-06-09T07:43:13.490Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        long e = zdt.toInstant().toEpochMilli();
        System.out.println(String.format("zoned dt: %s  from zone: %s ; epoch milli: %d", zdt, zdt.getZone(), e));
        assert (zdt.getOffset().equals(ZoneOffset.UTC));  //zdt is in UTC
        assert (zdt != null);
    }

    @Test
    public void given_string_toZdt() {
        String dtstr = "2020-06-09T07:43:13.490Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        long e = zdt.toInstant().toEpochMilli();
        ZonedDateTime zzdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(e), ZoneId.of("UTC"));
        System.out.println(String.format("zone dt: %s", zzdt));
        assert (zzdt.toString().equals("2020-06-09T07:43:13.490Z[UTC]"));
        System.out.println(zzdt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }


    @Test
    public void given_string_toZdt_toFormattedString() {
        String dtstr = "2020-06-09T07:43:13.49Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        System.out.println(zdt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println(zdt.toString());
        assert (zdt.toString().equals("2020-06-09T07:43:13.490Z"));

        final DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        System.out.println("with formatter: " + zdt.format(dateTimeFormatter));
        assert (zdt.format(dateTimeFormatter).equals("2020-06-09T07:43:13.490Z"));

    }


    @Test
    void givenMillisToZdt() {
        long emilli = 1591688593490L;
        ZonedDateTime zzdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(emilli), ZoneId.of("Z"));
        System.out.println(String.format("zone dt: %s", String.valueOf(zzdt)));
        assert (zzdt.toString().equals("2020-06-09T07:43:13.490Z"));
    }

    @Test
    void givenMillisToZdtFormat() {
        long emilli = 1603836000000L;
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(emilli), ZoneId.of("Z"));
        assert (zdt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME).equals("2020-10-27T22:00:00Z"));
    }

    @Test
    void givenEpochSecondsToZdt() {
        long epochsecs = 1599263700L;
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(epochsecs), ZoneId.of("Z"));
        System.out.println(String.format("zone dt: %s", String.valueOf(zdt)));
        assert (zdt.toString().equals("2020-09-04T23:55Z"));
    }

    @Test
    void givenNowtoUTCString() {
        ZonedDateTime zdt = LocalDateTime.now().atZone(ZoneId.of("Z"));
        System.out.println(String.format("zdt of now:%s", zdt.toString()));
        assert (zdt != null);
    }

    @Test
    void givenLocalDateCheckDayLightSaving() {
        LocalDate ld = LocalDate.of(2020, 03, 29);
        LocalDateTime ldt = ld.atStartOfDay();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("CET"));
    }

    @Test
    public void shouldGetTodayDateAtZone() {

        ZonedDateTime zdt1 = ZonedDateTime.parse("2021-08-29T23:00:00Z",
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
        ZonedDateTime zdt2 = ZonedDateTime.parse("2021-08-30T01:00:00+02:00",
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
        LocalDate ld1 = zdt1.toLocalDate();
        LocalDate ld2 = zdt2.toLocalDate();
        System.out.println("zdt1 now:  " + zdt1 + " localdate: " + ld1);
        System.out.println("zd2 now:  " + zdt2 + " localdate: " + ld2);

        //so a correct way to get today in a zone will be
        /*
            ZonedDateTime ztoday = ZonedDateTime.now(ZoneId.of("CET"));
            LocalDate ldtoday = zdt.toLocalDate();
            ZonedDateTime ztodayAtStart= ldtoday.atStartOfDay(ZoneId.of("CET"));
         */

        assertThat(zdt1.format(formatterZ),
                is("2021-08-29T23:00:00Z"));
        assertThat(zdt2.format(formatterZ),
                is("2021-08-30T01:00:00+02:00"));
        assertThat(ld1, not(ld2));
        assertThat(ld1, is(LocalDate.of(2021, 8, 29)));
        assertThat(ld2, is(LocalDate.of(2021, 8, 30)));

    }

    @Test
    public void shouldCheckTruncated(){
        ZonedDateTime zdt0 = ZonedDateTime.parse("2021-08-29T23:00:00Z",
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
        ZonedDateTime zdt1 = ZonedDateTime.parse("2021-08-29T23:15:00Z",
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
        ZonedDateTime zdt2 = ZonedDateTime.parse("2021-08-29T23:45:00Z",
                DateTimeFormatter.ISO_ZONED_DATE_TIME);

        assertThat(zdt0.truncatedTo(ChronoUnit.HOURS), is(zdt0));
        assertThat(zdt1.truncatedTo(ChronoUnit.HOURS), is(zdt0));
        assertThat(zdt2.truncatedTo(ChronoUnit.HOURS), is(zdt0));
    }

    @Test
    public void given_ztimestamp_todate_timesmp() {
        String dtstr = "2020-06-09T07:43:13.49Z";
        ZonedDateTime zdt = ZonedDateTime.parse(dtstr);
        //zdt.getZone()
        LocalDate ld = zdt.toLocalDate();
        LocalDateTime ldt = LocalDateTime.of(ld, LocalTime.of(0,0,0));
        ZonedDateTime nzdt = ZonedDateTime.of(ldt, zdt.getZone());
        ZonedDateTime nzdtb = ZonedDateTime.of(zdt.toLocalDate(),
                LocalTime.of(0,0),
                zdt.getZone());
        System.out.println(zdt.toString());
        System.out.println(nzdt.toString());
        System.out.println(nzdtb.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        assert (zdt.toString().equals("2020-06-09T07:43:13.490Z"));
        assert (nzdtb.equals(ZonedDateTime.parse("2020-06-09T00:00:00Z")));
        assert (nzdtb.format(DateTimeFormatter.ISO_ZONED_DATE_TIME).equals("2020-06-09T00:00:00Z"));
    }

}