public class Main {

    public static void main (String [] args) {
        Insurance insurance1 = new Insurance("Iran");
        Insurance insurance2 = new Insurance("Sina");

        Employee emp1 = new Employee("Arya", "Khaligh", 20, "Arya", "Khaligh");
        Employee emp2 = new Employee("Sina", "Jafari", 32, "Zahra", "Khaki");
        Employee emp3 = new Employee("Ali", "Barzegar", 45, "Maryam", "Fotoohi");

        insurance1.register(emp1);
        insurance1.register(emp2);
        insurance1.register(emp3);
        insurance2.register(emp1);
        insurance2.register(emp2);
        insurance2.register(emp3);



    }
}
