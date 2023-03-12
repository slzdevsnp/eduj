package rsb.reactor;

import java.util.concurrent.TimeUnit;

public class CodeTimer {
    public static void time(Runnable codeBlock, TimeUnit timeUnit){
        long st = System.nanoTime();
        //execute the code block
        codeBlock.run();
        long et = System.nanoTime();
        long elapsedTime  = et - st;

        long convertedTime;
        String timeUnitName;
        switch (timeUnit) {
            case NANOSECONDS:
                convertedTime = elapsedTime;
                timeUnitName = "nanoseconds";
                break;
            case MICROSECONDS:
                convertedTime = TimeUnit.NANOSECONDS.toMicros(elapsedTime);
                timeUnitName = "microseconds";
                break;
            case MILLISECONDS:
                convertedTime = TimeUnit.NANOSECONDS.toMillis(elapsedTime);
                timeUnitName = "milliseconds";
                break;
            case SECONDS:
                convertedTime = TimeUnit.NANOSECONDS.toSeconds(elapsedTime);
                timeUnitName = "seconds";
                break;
            default:
                throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
        }
        System.out.println("Elapsed time: " + convertedTime + " " + timeUnitName);
    }

}


