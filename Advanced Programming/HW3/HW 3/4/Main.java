public class Main {

    public static void main(String[] args) {
        NumberPlate myNumberPlate ;
        myNumberPlate = new NumberPlate("9524014" , "Arya" ,"Khaligh") ;

        myNumberPlate.addTrafficTicket("Moody Driving !");
        myNumberPlate.addTrafficTicket("Fast Driving !");
        myNumberPlate.addTrafficTicket("Sit belt!");
        myNumberPlate.addTrafficTicket("Over speed limit !");
        myNumberPlate.addTrafficTicket("Bad parking !");
        System.out.println(myNumberPlate.getValidity()) ;

        System.out.println("After removing one traffic ticket : ");
        myNumberPlate.removeTrafficTicket("Bad parking !");
        System.out.println(myNumberPlate.getValidity()) ;




    }
}
