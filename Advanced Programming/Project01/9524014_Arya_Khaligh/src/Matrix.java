import java.util.HashMap;
import java.util.Scanner;

/**
 * This class implements a matrix .
 *
 * @author Arya Khaligh
 * @version 1.0
 */
public class Matrix {

    private HashMap<Integer, Integer> elements;
    private int rowNumber = 0;
    private int columnNumber;

    /**
     * TODO
     * This constructor receives and splits matrix elements and outs them
     * in elements HashMap . The element keys starts from 1 . If user inputs
     * wrong row size constructor will {@code throw new IllegalStateException("Wrong row sizes!"); .
     */
    public Matrix() {
        Scanner scanner = new Scanner(System.in);
        elements = new HashMap<>();
        String temp = null;
        String row;
        boolean firstWhile = true; //for counting loops

        while (scanner.hasNextLine()) {
            row = scanner.nextLine();

            //To terminate while loop.
            if (row.isEmpty())
                break;

            //This if/else if checks if user gives wrong row size.
            if (firstWhile == true) {
                columnNumber = numberOfIntInStr(row);
                firstWhile = false;
            } else if (numberOfIntInStr(row) != columnNumber)
                throw new IllegalStateException("Wrong row sizes!");

            temp += "," + row;
            rowNumber++;
        }

        //For putting string elements to elements HashMap.
        String tempStr[] = temp.split(",");
        for (int i1 = 1; i1 < tempStr.length; i1++) {
            int element = Integer.parseInt(tempStr[i1]);
            elements.put(i1, element);
        }
    }


    public Matrix (int rowNumber , int columnNumber) {
        this.rowNumber = rowNumber ;
        this.columnNumber = columnNumber ;
        elements = new HashMap<>() ;
        for (int i = 1 ; i <= columnNumber*rowNumber ; i++)
            elements.put(i,0) ;
    }

    /**
     * TODO
     * This method returns element of specific row and column .
     *
     * @param i for row
     * @param j for column
     * @return element that fits row and column
     */
    public int getElement(int i, int j) {
        int place = (i - 1) * columnNumber + j;
        return elements.get(place);
    }

    /**
     * TODO
     * This method is used in constructor to calculate number of
     * integers in given string .
     *
     * @param str given String
     * @return number if integers
     */
    public static int numberOfIntInStr(String str) {
        String tempStr[] = ("," + str).split(",");
        int size = tempStr.length - 1;
        return size;
    }

    /**
     * This method returns number of rows.
     *
     * @return rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * This method returns number of rows.
     *
     * @return
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * TODO
     * This class sets value of a specific element .
     * @param i for row
     * @param j for column
     * @param element value of specific element to set.
     */
    public void put(int i, int j, int element) {
        if (i > rowNumber || j > columnNumber || i < 1 || j < 1)
            throw new IllegalArgumentException("Wrong column or row!");
        elements.put((i - 1) * columnNumber + j, element);
    }

    /**
     * TODO
     * This public method prints all elements of matrix .
     */
    public void print () {
        for (int i = 1 ; i <= rowNumber * columnNumber ; i++) {
            System.out.print(elements.get(i));

            if (i%columnNumber ==0)
                System.out.println("");
            else
                System.out.print(",");
        }
    }

}
