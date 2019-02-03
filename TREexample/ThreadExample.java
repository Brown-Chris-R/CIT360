package TREexample;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadExample extends Thread {
    private String message;

    public ThreadExample(String msg) {
        this.message = msg;
    }

    public void run() {
        // Generate a random number between 1 and 1000.
        // Use this number to sleep a random duration
        GenerateNumber aNumber;
        aNumber = new GenerateNumber();
        int randomNumber = aNumber.generate(1);

        try {
            sleep(randomNumber);
            int atomicCounter = ThreadRunnableExecutorExample.getCounter().increment();
            System.out.println(message + " from " + Thread.currentThread().getName() + ". Atomic Counter is: " + atomicCounter);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
