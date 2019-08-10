import java.util.Random;

/**
 * This class implements operations about class .
 *
 * @author Arya Khaligh
 * @version 1.0
 * @since 2018-03-05
 */


public class QueueSystem {

    private Queue queue;

    /**
     * This constructor makes a queue and 30 persons and enter them to th queue .
     */
    public QueueSystem() {
        queue = new Queue();
        Person person;
        Random rand = new Random();

        int temp;
        int time = 0;

        for (int i1 = 1; i1 < 31; i1++) {
            temp = rand.nextInt(3);
            time += temp;
            person = new Person("person" + i1, time);
            queue.enqueue(person);
            person.setExitTime(person.getTime() + i1 * 2);
        }
    }

    /**
     * The program uses this method to print persons , entering and waiting time and average waiting time .
     */

    public void print() {
        float avg = queue.waitingAvg() ;

        for (int i1 = 0; i1 < 30; i1++) {
            queue.print();
            queue.dequeue();
        }
        System.out.println("\nAverage waiting time : " + avg);
    }
}
