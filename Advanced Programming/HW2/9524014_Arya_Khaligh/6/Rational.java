/**
 * Rational class four basic mathematical operations
 * 
 * @Arya Khaligh 
 * @2018-02-18
 */
public class Rational {

//    field name 
    private int numerator ; 
    private int denominator ; 

    //   constructors 
    
//    if we have arguments
    public Rational (int numerator , int denominator) {
        this.numerator = numerator ; 
        this.denominator = denominator ; 
        simplifier () ; 
    }
//    if we don't have arguments 
    public Rational () {
        numerator = 3 ; 
        denominator = 4 ;
    }
     
    //    methods 
    
//    simplifies numerator and denominator every time we call this method
    public void simplifier() {
                
        int finder = 1 ; 
        
        for (int i = 1 ; i <= numerator || i <= denominator ; i++ )
            if (numerator % i == 0 && denominator % i == 0)
                finder = i ;
        
        numerator = numerator/finder ; 
        denominator = denominator/finder ; 
    }
    
//    methods for setting and getting vlues of denominator or numerator
    public void setterNumerator (int numerator) {
        this.numerator = numerator ; 
    }
    
    public void setterDenominator (int denominator) {
        this.denominator = denominator ; 
    }
    
    public int getterNumerator () { 
        return numerator ; 
    }
    
    public int getterDenominator () {
        return denominator ; 
    }
    
    //    four basic mathematical operations methods
    
//    method for multiplication 
    public void mult (Rational secondNum) {
        numerator *= secondNum.getterNumerator() ; 
        denominator *= secondNum.getterDenominator() ;
        simplifier () ; 
        
    }

//    method for division 
    public void div (Rational secondNum) {
        numerator *= secondNum.getterDenominator() ; 
        denominator *= secondNum.getterNumerator() ;
        simplifier () ; 
    }

//    method for addition
    public void add (Rational secondNum) {
        numerator = (secondNum.getterDenominator() * numerator ) + 
                (secondNum.getterNumerator () * denominator ) ; 
        denominator = secondNum.getterDenominator() * 
                denominator ; 
        simplifier () ; 
    }

//      metod for subtraction 
    public void sub (Rational secondNum) {
        numerator = (secondNum.getterDenominator() * numerator ) - 
                (secondNum.getterNumerator () * denominator ) ; 
        denominator = secondNum.getterDenominator() * 
                denominator ; 
        simplifier () ; 
    }
    
//    Prints sth like this : "3/4"
    public void printFraction () {
        System.out.println (numerator + "/" + denominator) ; 
    }
    
//    Prints sth like this : "3.4"
    public void printFloat () {
        System.out.println (numerator + "." + denominator) ; 
    }
}
