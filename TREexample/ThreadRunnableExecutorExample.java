package TREexample;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadRunnableExecutorExample {
    private static AtomicCounter.Counter theCounter;

    public static void main(String[] args) {
        // Initialize the Atomic counter variable
        theCounter = new AtomicCounter.Counter();

        // Thread Example
        System.out.println("----- Start of Thread Example -----");
        Thread t1 = new ThreadExample("Hello Thread!");
        t1.setName("thread1");
        t1.run();

        (new ThreadExample("My first thread, isn't it lovely?")).run();
        System.out.println("-----  End of Thread Example  -----");


        System.out.println("----- Start of Runnable Example -----");
        // invoke 3 runnables
        Runnable runnable1 = new RunnableExample(42);
        new Thread(runnable1, "thread2").start();
        new Thread(runnable1, "thread3").start();
        new Thread(runnable1, "thread4").start();
        // inline way to invoke a runnable
        (new Thread(new RunnableExample(21))).start();

        // Create and run a runnable using lambda notation
        // Run the task in the current Thread
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("This message is from thread: " + threadName);
        };
        task.run();

        // Now Run the same task but in a separate thread
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("-----  End of Runnable Example  -----");

        // Demonstrate executors and callables
        System.out.println("----- Start of Executors Example -----");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> futureA = executor.submit(new CallableExample("A"));
        Future<String> futureB = executor.submit(new CallableExample("B"));

        try
        {
            System.out.println("futureA identifier: " + futureA.get());
            System.out.println("futureB identifier: " + futureB.get());
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(ThreadRunnableExecutorExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ExecutionException ex)
        {
            Logger.getLogger(ThreadRunnableExecutorExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Wait up to 15 seconds for shutdown of running executors
        executor.shutdown();
        try
        {
            if(!executor.awaitTermination(5, TimeUnit.SECONDS))
            {
                System.out.println("Forcing Shutdown now . . .");
                executor.shutdownNow();
            }
        }
        catch (InterruptedException ex)
        {
            System.out.println("Forcing Shutdown now . . .");
            executor.shutdown();
        }
        System.out.println("-----  End of Executor Example  -----");

        // Mark the end of the main method execution
        System.out.println("End of Main method!");
    }

    public static AtomicCounter.Counter getCounter()
    {
        return theCounter;
    }
}
