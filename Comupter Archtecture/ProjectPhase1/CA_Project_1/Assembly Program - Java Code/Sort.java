import java.util.ArrayList;

// Java program for implementation of Bubble Sort
class Sort
{
    ArrayList<Order> Sort(ArrayList<Order> orders)
    {
        ArrayList<Order> ordersl = new ArrayList<>();
        int i = orders.size();
        int max;
        int index;
        for(int j =0;j<i;j++){
            max=orders.get(0).f ;
            index=0;
            for(int k = 0; k < orders.size();k++){
               if(max<orders.get(k).f){
                   max=orders.get(k).f;
                   index=k;
               }
            }

            ordersl.add(orders.get(index));
            orders.remove(index);
        }
        return ordersl;
    }

    /* Prints the array */
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver method to test above 

} 