public class main {

    public static void main(String[] args) {
        
        Rational n1 = new Rational (4 , 5) ; 
        Rational n2 = new Rational (7 , 14) ; 
        
        // prints n1 and n2 (fraction type)
        n1.printFraction () ; 
        n2.printFraction () ; 
        System.out.println () ;
        
        // float type 
        n1.printFloat () ; 
        n2.printFloat () ; 
        System.out.println () ;
        
        //addition 
        System.out.print ("Addition = ") ;
        n1.add (n2) ;
        n1.printFraction () ;
        
        //subtraction 
        System.out.print ("Subtraction = ") ;
        n1.sub (n2) ;
        n1.printFraction () ;
        
        //multiplication 
        System.out.print ("Multiplication = ") ;
        n1.mult (n2) ;
        n1.printFraction () ;
        
        //division
        System.out.print ("Division = ") ;
        n1.div (n2) ;
        n1.printFraction () ;

    }
    
}

