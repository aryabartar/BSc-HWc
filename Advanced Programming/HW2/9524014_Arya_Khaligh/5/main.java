public class main {

    public static void main(String[] args) {
        Item chocolate = new Item ("chocolate" , "Farmand") ; 
        Item pofak = new Item ("pofak" , "Chakels") ; 
        
        chocolate.increment (5) ; 
        pofak.increment (3) ; 
        
        chocolate.print () ; 
        pofak.print () ; 
    }
    
}

