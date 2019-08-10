import java.util.Scanner ; 

public class firstPro {

    public static void main (String[] args) {
        
        String userString ; 
        String firstTwoStr ; 
        int firstTwoInt ;
        int total = 0 ; 
        
        System.out.print ("Enter your string : ") ; 
        Scanner input = new Scanner (System.in) ; 
        
        userString = input.next() ; 
        firstTwoStr = userString.substring(0,2) ; //split and saves first 2 characters
        
        firstTwoInt = Integer.parseInt(firstTwoStr) ; //casts String to int 

        int triangularNum[] = new int[firstTwoInt] ;  //array definition 
        
        for (int i1 = 0 ; i1 < firstTwoInt ; i1++ ) {
            
            //makes triangular numbers 
            for (int i2 = 0 ; i2 <= i1 ; i2++ )
                total += i2 ;
            
            triangularNum[i1] = total ; //save triangular numbers in array 
            total = 0 ; 
            
            if ( i1 == (firstTwoInt-1) ) 
                continue ; 
            //prints 0 to firstTwoNum-1 
            System.out.printf ("%d, " , triangularNum[i1]) ; 
                 
        }
       
        System.out.println (triangularNum[firstTwoInt-1]) ; //prints last number
    }
    
}

