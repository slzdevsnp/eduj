package org.slzdevsnp.datetime;





import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocalDateTimeCompareTest {


    @Test
    public void givenDailyRangeCheckDateTimeIn() {

        LocalTime lt = LocalTime.parse("22:00");
        int durationMinutes = 10;


        //asserts
        assertThat( diffMilliForDayInterval(LocalDateTime
                        .of(2020,11,6,21,59)
                ,lt,durationMinutes), is(0L));
        assertThat( diffMilliForDayInterval(LocalDateTime
                        .of(2020,11,6,22,7)
                ,lt,durationMinutes), is(3*60000L));
        assertThat( diffMilliForDayInterval(LocalDateTime
                        .of(2020,11,6,22,12)
                ,lt,durationMinutes), is(0L));
    }

    @Test
    public void givenDayOfWeekRangeCheckDateTimeIn() {
        LocalTime lt = LocalTime.parse("22:00");
        LocalDateTime ldt = LocalDateTime
                .of(2020,11,6,22,7);

        Calendar cal = Calendar.getInstance();
        cal.setTime( java.sql.Date.valueOf(ldt.toLocalDate()));
        System.out.println("calendar date before tranforms:"+cal.getTime());
        //Calendar.Friday = 6
        cal.setFirstDayOfWeek(Calendar.FRIDAY); //setFirstDayOfWeek(6)
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        System.out.println("calendar Date after transforms:"+cal.getTime());

        System.out.println("diffMillis till end of weekly break:"
                + diffMilliForWeekInterval(ldt,lt,48*60, 6));
        assertThat(diffMilliForWeekInterval(LocalDateTime
                .of(2020,11,6,22,7)
                ,lt,48*60, 6), is(172380000L));//47h53min on friday
        assertThat(diffMilliForWeekInterval(LocalDateTime
                        .of(2020,11,8,21,59) //1min on sunday
                ,lt,48*60, 6), is(60000L));
        assertThat(diffMilliForWeekInterval(LocalDateTime
                        .of(2020,11,5,21,59) //on thursday
                ,lt,48*60, 6), is(0L));
    }

    private long diffMilliForDayInterval(LocalDateTime ldt, LocalTime lt, int durationMin) {
        long  durationUntilMillis = 0L;
        LocalDateTime left = ldt.toLocalDate().atStartOfDay()
                .plusHours(lt.getHour())
                .plusMinutes(lt.getMinute());
        LocalDateTime right = left
                .plusMinutes(durationMin);

        if (ldt.isAfter(left) &&
                ldt.isBefore(right)) {
            durationUntilMillis = ChronoUnit.MILLIS.between(ldt, right);
        }
        return durationUntilMillis;
    }

    private long diffMilliForWeekInterval(LocalDateTime ldt, LocalTime lt, int durationMin, int dayOfWeek) {
        long  durationUntilMillis = 0L;

        Calendar cal = Calendar.getInstance();
        cal.setTime( java.sql.Date.valueOf(ldt.toLocalDate()));
        cal.setFirstDayOfWeek(dayOfWeek); //setFirstDayOfWeek(6)
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        LocalDateTime left =  cal.getTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().atStartOfDay()
                .plusHours(lt.getHour())
                .plusMinutes(lt.getMinute());

        LocalDateTime right = left
                .plusMinutes(durationMin);

        if (ldt.isAfter(left) &&
                ldt.isBefore(right)) {
            durationUntilMillis = ChronoUnit.MILLIS.between(ldt, right);
        }
        return durationUntilMillis;
    }

}
