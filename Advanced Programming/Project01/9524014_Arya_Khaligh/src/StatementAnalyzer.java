public class StatementAnalyzer {

    private String firstPart; //coefficient of X
    private String secondPart; //coefficient of Y
    private char operationType; // can be '+' , '-' , '*' , 'T' and 'F'
    private String statement;
    private boolean needReverse ;

    /**
     * This constructor initializes statement , firstPart and secondPart fields.
     */
    public StatementAnalyzer(String statement) {
        firstPart = "";
        secondPart = "";
        this.statement = statement;
        needReverse = false ;
    }

    /**
     * This method checks if the statement has X and Y and if it does not throws
     * an exception .
     */
    public void operate() {
        int[] flag = new int[2];

        for (int i = 0; i < statement.length(); i++) {
            if (statement.charAt(i) == 'X') {
                flag[0]++;
            } else if (statement.charAt(i) == 'Y') {
                flag[1]++;
            }
        }


        if ((flag[0] == 1) && (flag[1] == 1)) {
            parse();
        } else if ((flag[0] == 1) && (flag[1] == 0)) {
            operationType = 'T';
            parse();
        } else if ((flag[0] == 0) && (flag[1] == 1)) {
            operationType = 'F';
            parse();
        } else
            throw new IllegalArgumentException("You should input at least one X or Y");

    }

    /**
     * This method is used to parse the statement and determines coefficients.
     */
    private void parse() {

        reverseStatement();

        statement = statement.replaceAll(" ", "");
        statement = statement.replaceAll("\\(", "");
        statement = statement.replaceAll("\\)", "");

        //If the statement only has X
        if (operationType == 'T') {
            for (int i = 0; 'X' != statement.charAt(i); i++)
                firstPart += statement.charAt(i);
        }

        //If the statement only has Y
        else if (operationType == 'F') {
            for (int i = 0; 'Y' != statement.charAt(i); i++)
                secondPart += statement.charAt(i);
        }

        //If the statement has both X and Y
        else {
            int i;
            //splits first part
            for (i = 0; statement.charAt(i) != 'X'; i++)
                firstPart += statement.charAt(i);

            i++;
            operationType = statement.charAt(i);
            i++;

            //splits second part
            for (; statement.charAt(i) != 'Y'; i++)
                secondPart += statement.charAt(i);
        }
    }

    /**
     * @return can be '+' , '-' , '*' , 'T' and 'F'
     */
    public char getOperationType() {
        return operationType;
    }

    /**
     * This method returns first coefficient .
     *
     * @return first coefficient
     */
    public int getFirstPart() {
        if (firstPart.equals(""))
            return 1;
        else {
            int first;
            first = Integer.parseInt(firstPart);
            return first;
        }
    }

    /**
     * This method returns second coefficient .
     *
     * @return
     */
    public int getSecondPart() {
        if (!secondPart.equals("")) {
            int second;
            second = Integer.parseInt(secondPart);
            return second;
        } else
            return 1;
    }

    /**
     * This method changes X and Y place if Y is before X .
     */
    private void reverseStatement() {

        boolean isReverseNeeded = false;
        boolean hasX = false ;
        boolean hasY = false ;
        int i ;

        for (i = 0; i < statement.length(); i++) {
            if ((statement.charAt(i) == 'X') || (statement.charAt(i) == 'Y'))
                break;
        }

        for (int j = 0 ; j < statement.length() ; j++) {
            if (statement.charAt(j) == 'X')
                hasX = true ;
            if(statement.charAt(j) == 'Y')
                hasY = true ;
        }

        if (statement.charAt(i) == 'Y') {
            isReverseNeeded = true;
            needReverse = true ;
        }

        if ((isReverseNeeded == true) && (hasX == true) && (hasY == true)) {
            String tempFirst = "";
            String tempLast = "";
            String between = "";

            statement = statement.replaceAll(" ", "");
            statement = statement.replaceAll("\\(", "");
            statement = statement.replaceAll("\\)", "");
            int i1;

            for (i1 = 0 ; 'Y' != statement.charAt(i1) ; i1++)
                tempFirst += statement.charAt(i1) ;
            tempFirst += 'Y' ;

            i1++ ;
            between += statement.charAt(i1) ;
            i1++ ;

            for (; 'X' != statement.charAt(i1) ;i1++)
                tempLast += statement.charAt(i1) ;
            tempLast += 'X' ;

            this.statement = tempLast + between + tempFirst ;

        }

    }

    /**
     * This method returns true if Y is before X
     */
    public boolean getNeedReverse () {
        return needReverse ;
    }
}
