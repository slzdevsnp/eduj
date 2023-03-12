package org.slzdevsnp.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Slf4j
public class CodeTimerTest {

    @Test
    public void testCodeTimer(){
        double maxIter = 1E9;
        CodeTimer.time(() -> {
            // Arbitrary code block to be timed
            for (long i = 0; i < Double.valueOf(maxIter).longValue(); i++) {
                Math.sqrt(i);
            }
        }, TimeUnit.MILLISECONDS);
        assertThat(true, is(true));
    }

}
