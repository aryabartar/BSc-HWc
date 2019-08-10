import java.util.Scanner;

/**
 * This class is the core of the program. Core class makes and uses objects of Matrix class.
 * Then calculates statement .
 */
public class Core {
    /**
     * Operate method initializes Matrix objects then receives statement from user then
     * uses StatementAnalyser object to split the statement and calculate it .
     */
    public void operate() {
        Scanner input = new Scanner(System.in);
        String statement;
        MatrixOperation operate;

        //initializes first matrix
        System.out.println("Define the first matrix (X): ");
        Matrix X = new Matrix();

        //initializes second matrix
        System.out.println("Define the second matrix (Y):");
        Matrix Y = new Matrix();

        StatementAnalyzer analyzer;

        //This loop condition is true unless the break in the loop executes
        while (true) {
            System.out.println("Enter your polynomial expression: (If you want to terminate input -1)");
            statement = input.nextLine();

            // sentinel value
            if (statement.equals("-1")) {
                System.out.println("Terminated !");
                break;
            }

            analyzer = new StatementAnalyzer(statement);
            analyzer.operate();

            Matrix tempX;
            Matrix tempY;
            Matrix result = null;

            tempX = MatrixOperation.singleProduct(analyzer.getFirstPart(), X);
            tempY = MatrixOperation.singleProduct(analyzer.getSecondPart(), Y);

            if (analyzer.getNeedReverse() == false)
                operate = new MatrixOperation(tempX, tempY);
            else
                operate = new MatrixOperation(tempY, tempX);

            //invokes appropriate method from object of MatrixOperation class
            if (analyzer.getOperationType() == '+')
                result = operate.summation();
            else if (analyzer.getOperationType() == '-')
                result = operate.subtraction();
            else if (analyzer.getOperationType() == '*')
                result = operate.production();
            else if (analyzer.getOperationType() == 'T')
                result = MatrixOperation.singleProduct(analyzer.getFirstPart(), X);
            else if (analyzer.getOperationType() == 'F')
                result = MatrixOperation.singleProduct(analyzer.getSecondPart(), Y);
            else
                throw new IllegalArgumentException("Input + or - or *") ;

            System.out.println("Result : ");
            result.print();
            System.out.println("\n");
        }
    }
}
