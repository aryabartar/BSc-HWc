public class Printer {

    public Printer (String[] args) {

        StringAnalyser str = new StringAnalyser() ;

        try {
            if (args[0].equals("-l") || args[0].equals("--lines")) {
                System.out.println(str.getLines());
            }
            else if (args[0].equals("-w") || args[0].equals("--words")) {
                System.out.println(str.getWords());
            }
            else if (args[0].equals("-c") || args[0].equals("--chars")) {
                System.out.println(str.getChars());
            }
            else
                System.out.println("Wrong cmd input !!! \nUse valid inputs like -l , --lines , ... ");
        }

        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(str.getLines() + " " + str.getWords() + " " + str.getChars());
        }
    }


}
