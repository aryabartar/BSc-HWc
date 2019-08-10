import java.util.ArrayList ;

public class NumberPlate {

    private String numberPlate ;
    private String firstName ;
    private String lastName ;
    private boolean validity ;
    private ArrayList<String> trafficTicket ;

    public NumberPlate (String numberPlate , String firstName , String lastName) {
        this.numberPlate = numberPlate ;
        this.firstName = firstName ;
        this.lastName = lastName ;
        validity = true ;
        trafficTicket = new ArrayList<String>() ;
    }

    public void printAllTrafficTickets(){
        for (String temp : trafficTicket){
            System.out.println(temp);
        }
    }

    public void addTrafficTicket (String ticket){

        trafficTicket.add(ticket) ;
        if (trafficTicket.size() >= 5) {
            validity = false ;
        }

    }

    public void removeTrafficTicket (String ticket) {
        trafficTicket.remove(ticket) ;
        if (trafficTicket.size() < 5){
            validity = true ;
        }
    }

    public boolean getValidity () {
        System.out.print("Driver license validity is : ");
        return validity ;
    }
}
