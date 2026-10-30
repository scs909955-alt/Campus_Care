
package person;

// INHERITANCE:
// Faculty inherits common attributes and methods from Person.
public class Faculty extends Person {

    // ENCAPSULATION:
    // Faculty-specific information.
    private String department;
    private String subject;
    private String designation;

    // CONSTRUCTOR:
    // super() initializes the common Person attributes.
    public Faculty(int id, String name, String department,
                   String subject, String designation) {

        super(id, name);

        this.department = department;
        this.subject = subject;
        this.designation = designation;
    }

    // GETTER METHODS:
    public String getDepartment() {
        return department;
    }

    public String getSubject() {
        return subject;
    }

    public String getDesignation() {
        return designation;
    }

    // METHOD OVERRIDING:
    // Faculty provides its own implementation of
    // the abstract method declared in Person.
    @Override
    public void displayDetails() {

        System.out.println("Faculty ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Subject: " + subject);
        System.out.println("Designation: " + designation);
    }
}
