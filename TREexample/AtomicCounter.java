package TREexample;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    // Have to nest a static class to hold the atomic integer since you aren't allowed to make a top-level class static
    // Only nested classes can be static
    static class Counter
    {
        private AtomicInteger counter = new AtomicInteger(0);

        // Just going to do a simple counter that increments. This might be similar to how a database keeps track of
        // the next available value for an auto-incrementing ID field in a table?
        public int increment()
        {
            int result = counter.getAndIncrement();
            return result;
        }
        public int value()
        {
            return counter.get();
        }
    }
}
