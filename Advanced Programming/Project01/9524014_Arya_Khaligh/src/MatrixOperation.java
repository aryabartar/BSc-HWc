public class MatrixOperation {
    private Matrix x; //firs matrix
    private Matrix y; //second matrix
    private Matrix z; //result

    /**
     * TODO
     * This constructor initializes x and y matrices .
     *
     * @param x first matrix.
     * @param y second matrix
     */
    public  MatrixOperation(Matrix x, Matrix y) {
        this.x = x;
        this.y = y;
    }

    /**
     * TODO
     * This method performs summation for two given matrices .
     *
     * @return z as result
     */
    public Matrix summation() {
        if ((x.getRowNumber() != y.getRowNumber()) || (x.getColumnNumber() != y.getColumnNumber()))
            throw new IllegalArgumentException("Wrong column or row sizes for summation!");
        int elementSum;
        z = new Matrix(x.getRowNumber(), x.getColumnNumber());

        //to sum element by element
        for (int i = 1; i <= x.getRowNumber(); i++) {
            for (int j = 1; j <= x.getColumnNumber(); j++) {
                elementSum = x.getElement(i, j) + y.getElement(i, j);
                z.put(i, j, elementSum);
            }
        }
        return z;
    }

    /**
     * TODO
     * This method performs subtraction for two given matrices .
     *
     * @return z as result
     */
    public Matrix subtraction() {
        if ((x.getRowNumber() != y.getRowNumber()) || (x.getColumnNumber() != y.getColumnNumber()))
            throw new IllegalArgumentException("Wrong column or row sizes for subtraction!");

        int elementSum;
        z = new Matrix(x.getRowNumber(), x.getColumnNumber());

        //to subtract element by element
        for (int i = 1; i <= x.getRowNumber(); i++) {
            for (int j = 1; j <= x.getColumnNumber(); j++) {
                elementSum = x.getElement(i, j) - y.getElement(i, j);
                z.put(i, j, elementSum);
            }
        }
        return z;
    }

    /**
     * TODO
     * This method performs production for two given matrices .
     *
     * @return z as result
     */
    public Matrix production() {
        if (x.getColumnNumber() != y.getRowNumber())
            throw new IllegalArgumentException("Wrong column and row sizes for production!");

        z = new Matrix(x.getRowNumber(), y.getColumnNumber());
        int total = 0 ;

        for (int i = 1; i <= x.getRowNumber(); i++) {
            for (int j = 1; j <= y.getColumnNumber(); j++) {
                for (int i1 = 1; i1 <= x.getColumnNumber(); i1++) {
                    total += x.getElement(i, i1) * y.getElement(i1, j);
                }
                z.put(i, j, total);
                total = 0;
            }
        }
        return z;
    }

    /**
     * TODO
     * This method returns result of operation .
     *
     * @return z as a result
     */
    public Matrix getResult() {
        return z;
    }

    /**
     * TODO
     * This method is used to return a Matrix .
     * @param number
     * @param matrix
     * @return
     */
    public static Matrix singleProduct(int number, Matrix matrix) {
        Matrix z ;
        z = new Matrix(matrix.getRowNumber(), matrix.getColumnNumber());
        int product;

        for (int i = 1; i <= matrix.getRowNumber(); i++) {
            for (int j = 1; j <= matrix.getColumnNumber(); j++) {
                product = matrix.getElement(i, j) * number;
                z.put(i, j, product);
            }
        }
        return z;
    }

}