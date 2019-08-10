import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String input;
    static String t;
    static Scanner s;
    static String[] temp;
    static Sort bs = new Sort();
    static ArrayList<Order> orders = new ArrayList<>();
    static ArrayList<Order> orders2 = new ArrayList<>();
    public static void main(String[] args) {
       s = new Scanner(System.in);
       while(true) {
           input = s.nextLine();
           if(input.equals("exit from calculating"))
               break;
           if(input.contains("\t")) {
               temp = input.split("\t");
               t = temp[1];
//               System.out.println(t);
               for (int i = 0; i <= orders.size(); i++) {
                   if (i == orders.size()) {
                       orders.add(new Order(t));
                       break;
                   }
                   if (t.equals(orders.get(i).name)) {
                       orders.get(i).incr();
                       break;
                   }
               }
           }
       }
       orders2=bs.Sort(orders);
       for(Order o : orders2){
        System.out.print(o.name+"     "+o.f+"        ");
       }

    }
}
