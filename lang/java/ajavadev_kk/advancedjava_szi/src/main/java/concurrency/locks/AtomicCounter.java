package concurrency.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    //modifiction is done transactionally atomically
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
