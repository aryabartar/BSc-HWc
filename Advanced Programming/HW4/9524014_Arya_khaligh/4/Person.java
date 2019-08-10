/**
 *This class implements persons who enter the queue .
 *
 * @author Arya Khaligh
 * @version 1.0
 * @since 2018-03-05
 */

public class Person {

    private String name;
    private int time;
    private int exitTime;

    /**
     * This constructor initializes person name and entrance time to queue
     * @param name
     * @param time
     */

    public Person(String name, int time) {
        this.time = time;
        this.name = name;
    }

    /**
     * This method is for getting name .
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method is for getting time .
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * This method is for setting exit time .
     * @param exitTime
     */
    public void setExitTime(int exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * This method is for getting exit time .
     * @return
     */
    public int getExitTime() {
        return exitTime;
    }
}
