import java.util.ArrayList;
import java.util.Scanner;


class MinHeap {

    private ArrayList<Long> list;

    public MinHeap() {

        this.list = new ArrayList<Long>();
    }


    public long getArrayListSize() {
        return list.size();
    }

    public void insert(long item) {

        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);

        while (parent != i && list.get(i) < list.get(parent)) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }


    public long extractMin() {

        if (list.size() == 0) {

            throw new IllegalStateException("MinHeap is EMPTY");
        } else if (list.size() == 1) {

            long min = list.remove(0);
            return min;
        }

        long min = list.get(0);
        long lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        minHeapify(0);

        return min;
    }

    private void minHeapify(int i) {

        int left = left(i);
        int right = right(i);
        int smallest = -1;

        if (left <= list.size() - 1 && list.get(left) < list.get(i)) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right) < list.get(smallest)) {
            smallest = right;
        }

        if (smallest != i) {

            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private int right(int i) {

        return 2 * i + 2;
    }

    private int left(int i) {

        return 2 * i + 1;
    }

    private int parent(int i) {

        if (i % 2 == 1) {
            return i / 2;
        }

        return (i - 1) / 2;
    }

    private void swap(int i, int parent) {

        long temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

}


public class Main {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            long t = scanner.nextLong();
            minHeap.insert(t);
        }

        long totalPrice = 0;
        long tempPrice = 0;
        while (minHeap.getArrayListSize() > 1) {
//            System.out.println("RUNNING WHILE");
            long var1 = minHeap.extractMin();
            long var2 = minHeap.extractMin();
            tempPrice = var1 + var2 ;
//            System.out.println(tempPrice);
            minHeap.insert(tempPrice);
            totalPrice += tempPrice;
        }
        totalPrice += tempPrice;
        System.out.println(totalPrice);
    }
}
