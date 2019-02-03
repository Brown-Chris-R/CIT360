package TREexample;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class CallableExample implements Callable<String> {
    public String id;

    public CallableExample(String identifier)
    {
        this.id = identifier;
    }

    @Override
    public String call() throws Exception {
        String identifier;
        switch (id) {
            case "A": identifier = "Apple";
                      break;
            case "B": identifier = "Banana";
                      break;
            case "C": identifier = "Cherry";
                      break;
            case "D": identifier = "Dragon Fruit";
                      break;
            default: identifier = "unknown";
        }

        // Generate a random number between 1 and 1000.
        // Use this number to sleep a random duration
        GenerateNumber aNumber;
        aNumber = new GenerateNumber();
        int randomNumber = aNumber.generate(1) * 10;

        sleep(randomNumber);
        int atomicCounter = ThreadRunnableExecutorExample.getCounter().increment();
        System.out.println("Thread " + Thread.currentThread().getName() +  ", Id is " + id + ". Atomic Counter is: " +
                atomicCounter);
        return identifier;
    }
}