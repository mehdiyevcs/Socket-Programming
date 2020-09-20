package TCP;

import java.util.ArrayList;
import java.util.Arrays;

public class Employee {

    private String firstName;
    private String secondName;
    private String jobTitle;

    private static ArrayList<Employee> employees = new ArrayList<Employee>(
        Arrays.asList(
            new Employee("Jalal","Mekhtiyev","Software Engineer"),
            new Employee("Ben","Affleck","Batman"),
            new Employee("Robert","Downey Jr","Iron Man")
        )
    );


    public Employee(){};

    public Employee(String firstName, String secondName, String jobTitle) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static void addEmployee(Employee emp){
        employees.add(emp);
    }

    public void deleteEmployee(Employee emp){
        employees.remove(emp);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
