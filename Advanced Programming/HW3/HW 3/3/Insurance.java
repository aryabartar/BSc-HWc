public class Insurance {

    private String name ;

    public Insurance (String name) {
        this.name = name ;
    }

    public void register (Employee emp){
        if (emp.getFirstName().equals(emp.getFirstNameOfSpouse()) &&
                emp.getLastName().equals(emp.getLastNameOfSpouse())){
            System.out.println("Insurance record for employee " +
                                emp.getFirstName() + " " + emp.getLastName() +
                                " cannot be registered!");
        }

        else {
            System.out.println("An insurance record for employee " +
                    emp.getFirstName() + " " + emp.getLastName() +
                    " successfully registered by company " + name + ".");
        }
    }
}
