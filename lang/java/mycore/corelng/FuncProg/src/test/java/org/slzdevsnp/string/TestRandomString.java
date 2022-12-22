package org.slzdevsnp.string;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TestRandomString {
    private String chars="1234567890abcdefghiklmnopqrstuvwxyz";
    int stringLength=16;

    @Test
    void givenGetRandomString() {
        String randomString = "";
        for (int i=0; i < stringLength; i++) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(chars.length());
            randomString += chars.substring(randomInt,randomInt+1);
        }
        System.out.println("rs: " +randomString);

        assertThat(randomString.length(), is(stringLength));
    }

    @Test
    void givenRandomCheckRandomness(){
        String st1 = getRandomString(10);
        String st2 = getRandomString(10);
        System.out.println("st1:"+st1+" st2:"+st2);
        assertThat(st1, not(st2));
    }

    @Test
    void givenDateMakeString() {
        String dt1 = "2021-01-01T00:00:00";
        String dt2 = "2021-01-01T00:00:58";

        LocalDateTime  ldt1 = LocalDateTime.parse(dt1, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime  ldt2 = LocalDateTime.parse(dt2, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .truncatedTo(ChronoUnit.MINUTES);

        System.out.println("epochToSec: " + ldt1.toEpochSecond(ZoneOffset.UTC));

        assertThat(ldt1, equalTo(ldt2));
        assertThat(ldt1.toEpochSecond(ZoneOffset.UTC),equalTo(ldt2.toEpochSecond(ZoneOffset.UTC)));
    }

    @Test
    void givenTimeMakeSerieName(){
        //int tdNumber = ctx.getThreadNum();
        int tdNumber = 0;
        String prefix="jmeter-";
        long epochts=LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toEpochSecond(ZoneOffset.UTC);

        String tseriename = prefix +  String.valueOf(epochts)+"_" + String.valueOf(tdNumber);
        String dsTseriename="ds_"+tseriename;
        System.out.println("tserie:"+tseriename);
       assertThat(tseriename, not(nullValue()));
    }

    private String getRandomString(int stringLength){
        String chars="1234567890abcdefghiklmnopqrstuvwxyz";
        String  randomString = "";
        for (int i=0; i < stringLength; i++) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(chars.length());
            randomString += chars.substring(randomInt,randomInt+1);
        }
        return randomString;
    }

}
