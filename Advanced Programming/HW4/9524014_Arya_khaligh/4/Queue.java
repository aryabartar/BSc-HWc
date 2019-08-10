/**
 * This class implements a queue which the people are waiting in it .
 *
 * @author Arya Khaligh
 * @version 1.0
 * @since 2018-03-05
 */

import java.util.ArrayList;

public class Queue {

    private ArrayList<Person> saf;

    public Queue() {
        saf = new ArrayList<Person>();
    }

    /**
     * For entering to queue .
     * @param person
     */

    public void enqueue(Person person) {
        saf.add(person);
    }

    /**
     * For exiting from queue .
     */
    public void dequeue() {
        saf.remove(0);
    }

    /**
     * For printing firs person in queue .
     */

    public void print() {
        Person tmp = saf.get(0);
        System.out.println("Person name : " + tmp.getName() +
                "   Entering time :  " + tmp.getTime() +
                "   Waiting time : " + tmp.getExitTime());
    }

    /**
     * This method calculates average waiting times .
     * @return average
     */
    public float waitingAvg() {
        int total = 0;
        float avg;

        for (Person temp : saf) {
            total += temp.getExitTime();

        }
        avg = (float) total / saf.size();
        return avg;
    }

}

