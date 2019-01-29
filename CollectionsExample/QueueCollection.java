package CollectionsExample;

import java.util.LinkedList;
import java.util.Queue;

public class QueueCollection {
    public static void main(String[] args) {
        Queue<Integer> queueOfInts = new LinkedList<>();

        // Adds elements {0, 1, 2, 3, 4} to queue
        for (int i=10; i<=20; i++)
            queueOfInts.add(i);

        // Display contents of the queue.
        System.out.println("Elements of queue: "+queueOfInts);

        // To remove the head of queue.
        int removedele = queueOfInts.remove();
        System.out.println("removed element: " + removedele);

        System.out.println(queueOfInts);

        // To view the head of queue
        int head = queueOfInts.peek();
        System.out.println("head of queue: " + head);

        // Rest all methods of collection interface,
        // Like size and contains can be used with this
        // implementation.
        int size = queueOfInts.size();
        System.out.println("Size of queue: " + size);
    }
}
