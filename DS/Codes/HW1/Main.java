import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String q_info = reader.nextLine();
        String weights = reader.nextLine();

        String[] q_info_array = q_info.split(" ");
        int[] q_info_int = new int[2];
        for (int i = 0; i < q_info_array.length; i++) {
            q_info_int[i] = Integer.parseInt(q_info_array[i]);
        }

        String[] wights_array = weights.split(" ");
        int[] weights_int = new int[q_info_int[0]];
        for (int i = 0; i < wights_array.length; i++) {
            weights_int[i] = Integer.parseInt(wights_array[i]);
        }

        Arrays.sort(weights_int);
        for (int i : weights_int) {
            System.out.print(i + " ");
        }
        System.out.println("");
        int counter = 0;
        int pointer = q_info_int[0] - 1;
        for (int i = 0; i < q_info_int[0]; i++) {
            System.out.println("&&&First if (i) running i is : " + i);
            System.out.println("&&&Pointer is : " + pointer);

            if (pointer == i) {
                counter++;
                break;
            }
            for (int j = pointer; j > i - 1; j--) {
                System.out.println("========");
                System.out.println("i is : " + i);
                System.out.println("j is : " + j);
                System.out.println("pointer is : " + pointer);
                System.out.println("Counter is : " + counter);

                System.out.println("****** " + weights_int[i]);
                System.out.println("****** " + weights_int[j]);
                System.out.println("****** " + (weights_int[i] + weights_int[j]) + "<=" + q_info_int[1]);
                if (weights_int[i] + weights_int[j] <= q_info_int[1]) {
                    System.out.println("First if run.");
                    counter++;
                    pointer = j - 1;
                    System.out.println("========");
                    break;
                } else {
                    System.out.println("Else run");
                    counter += 1;
                    System.out.println("========");
                }
            }
        }
        System.out.println(counter);
    }
}