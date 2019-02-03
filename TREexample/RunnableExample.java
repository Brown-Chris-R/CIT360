package TREexample;

public class RunnableExample implements Runnable {
    private int runnableInt;

    public RunnableExample(int number)
    {
        this.runnableInt = number;
    }

    public void run()
    {
        int atomicCounter = ThreadRunnableExecutorExample.getCounter().increment();
        System.out.println("Thread " + Thread.currentThread().getName() + " received the number "
                + runnableInt + ". The Atomic Counter is: "
                + atomicCounter);

    }

}
