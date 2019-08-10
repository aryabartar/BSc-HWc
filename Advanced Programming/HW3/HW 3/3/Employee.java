public class Employee {

    private String firstName ;
    private String lastName ;
    private int age ;
    private int numOfchildren ;
    private String firstNameOfSpouse ;
    private String lastNameOfSpouse ;

    public Employee (String firstName , String lastName , int age){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.age = age ;
    }

    public Employee (String firstName , String lastName , int age ,
                     String firstNameOfSpouse , String lastNameOfSpouse) {
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.age = age ;
        this.firstNameOfSpouse = firstNameOfSpouse ;
        this.lastNameOfSpouse = lastNameOfSpouse ;
    }

    public void increaseChildren (int increment){
        if (increment > 0) {
            numOfchildren += increment ;
        }
    }

    public int getAge() {
        return age;
    }

    public int getNumOfchildren() {
        return numOfchildren;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFirstNameOfSpouse() {
        return firstNameOfSpouse;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLastNameOfSpouse() {
        return lastNameOfSpouse;
    }
}
