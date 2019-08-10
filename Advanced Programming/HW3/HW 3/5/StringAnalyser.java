import java.util.Scanner;

public class StringAnalyser {

    private String input;


    public StringAnalyser() {
        this.input = getString();
    }

    private String getString() {
        String input = "";
        Scanner keyboard = new Scanner(System.in);
        String line;

        while (keyboard.hasNextLine()) {
            line = keyboard.nextLine();
            if (line.isEmpty()) {
                break;
            }
            input += line + "\n";
        }


        return input;
    }

    public int getLines() {

        int lineCounter = 0;
        Scanner scan = new Scanner(input);

        while (scan.hasNext()) {
            scan.nextLine();
            lineCounter++;
        }
        return lineCounter;
    }

    public int getWords () {

        int wordCounter = 0 ;
        Scanner scan = new Scanner(input) ;

        while (scan.hasNext()) {
            scan.next() ;
            wordCounter++ ;
        }

        return wordCounter ;
    }

    public int getChars () {

        int charCounter = 0;
        String temp ;
        Scanner scan = new Scanner(input) ;

        while (scan.hasNext()) {
            temp = scan.next() ;
            charCounter += temp.length() ;
        }

        return charCounter ;
    }

}


