import java.util.ArrayList;
import java.util.HashMap;

public class Employee {

    // FIELDS: the data each Employee object holds
    private String  name;
    private double  salary;
    private boolean active;

    // CONSTRUCTOR: runs when you write new Employee(...)
    public Employee(String name, double salary) {
        this.name   = name;     // 'this.name' is the field; 'name' is the parameter
        this.salary = salary;
        this.active = true;     // all new employees start active
    }

    // GETTERS: controlled read access to private fields
    public String  getName()   { return name; }
    public double  getSalary() { return salary; }
    public boolean isActive()  { return active; }

    // METHODS: actions this object can perform
    public void promote(double raise) {
        if (raise > 0) this.salary += raise;
    }

    public void deactivate() {
        this.active = false;
    }

    // TOSTRING: what prints when you System.out.println(employee)
    @Override
    public String toString() {
        return name + " | $" + salary + " | active: " + active;
    }

    public static void main(String[] args) {
        // An ordered list of employees
        ArrayList<Employee> team = new ArrayList<>();
        team.add(new Employee("Ada Lovelace",  95000.0));
        team.add(new Employee("Alan Turing",  102000.0));
        team.add(new Employee("Grace Hopper", 110000.0));

        for (Employee emp : team) {
            System.out.println(emp);   // calls toString automatically
        }

        // A lookup table: name -> Employee
        HashMap<String, Employee> directory = new HashMap<>();
        for (Employee emp : team) {
            directory.put(emp.getName(), emp);
        }

        Employee found = directory.get("Ada Lovelace");
        System.out.println(found.getSalary());   // 95000.0
    }
}
