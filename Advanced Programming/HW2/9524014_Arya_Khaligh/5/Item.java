public class Item {
//    fields 
    private int amount ; 
    private String name ; 
    private String producer ; 

//    constructor
    public Item (String name , String producer) {
        amount = 0 ; 
        this.name = name ; 
        this.producer = producer ; 
    }
    
//    methods 
    public void increment (int addedAmount) {
        if (addedAmount >= 0) 
            amount += addedAmount ; 
    }
    
    public void decrement (int removedAmount) {
        if (removedAmount >= 0) 
            amount -= removedAmount ; 
    }
            
    public void print (){
        System.out.println ("NAME : " + name + "    PRODUCER : " + producer + "    AMOUNT : " + amount + "\n") ; 
    }
    
    
    
}
