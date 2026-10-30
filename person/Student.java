
package person;
import java.util.Locale;
// INHERITANCE:
// Student inherits id, name and common methods from Person.
public class Student extends Person {

    // ENCAPSULATION:
    // Private fields store student-specific information.
    private String department;

    private int totalClasses;
    private int attendedClasses;

    private double marks1;
    private double marks2;
    private double marks3;

    // CONSTRUCTOR:
    // super() calls the parent class constructor.
    public Student(int id, String name, String department) {

    super(id, name);

    this.name = formatName(name);
    this.department = formatName(department);
}
    // SETTER METHODS:
    // Used to update student information.

    public void setName(String name) {
    this.name = formatName(name);
}
    // ATTENDANCE VALIDATION
public void setAttendance(int total, int attended) {

    if (total <= 0 || attended < 0 || attended > total) {
        throw new IllegalArgumentException(
            "Invalid attendance! Attended classes must be between 0 and total classes."
        );
    }

    this.totalClasses = total;
    this.attendedClasses = attended;
}

    
 // MARKS VALIDATION
public void setMarks(double m1, double m2, double m3) {

    if (m1 < 0 || m1 > 100 ||
        m2 < 0 || m2 > 100 ||
        m3 < 0 || m3 > 100) {

        throw new IllegalArgumentException(
            "Marks must be between 0 and 100!"
        );
    }

    this.marks1 = m1;
    this.marks2 = m2;
    this.marks3 = m3;
}
public void setDepartment(String department){
    this.department = department;
}

    // GETTER METHOD:
    public String getDepartment() {
        return department;
    }

    // CALCULATE ATTENDANCE:
    public double calculateAttendance() {

        if (totalClasses == 0) {
            return 0;
        }

        return (attendedClasses * 100.0) / totalClasses;
    }

    // CALCULATE AVERAGE MARKS:
    public double calculateAverage() {
        return (marks1 + marks2 + marks3) / 3;
    }

    // METHOD OVERRIDING:
    // Provides Student's own implementation of the
    // abstract displayDetails() method from Person.
    @Override
    public void displayDetails() {

        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);

        System.out.printf("Attendance: %.2f%%%n",calculateAttendance());

        System.out.printf("Average Marks: %.2f%n",calculateAverage());
    }


    // GETTER METHODS: Return individual subject marks.
public double getMarks1() {
    return marks1;
}

public double getMarks2() {
    return marks2;
}

public double getMarks3() {
    return marks3;
}

// GETTERS: Return attendance information.
public int getTotalClasses() {
    return totalClasses;
}

public int getAttendedClasses() {
    return attendedClasses;
}
// NAME VALIDATION AND FORMATTING
private String formatName(String name) {

    // Remove unnecessary spaces.
    name = name.trim();

    // Reject empty names and numbers/symbols.
    if (name.isEmpty() || !name.matches("[A-Za-z ]+")) {
        throw new IllegalArgumentException(
            "Name must contain only alphabets and spaces!"
        );
    }

    // Convert name into uppercase.
    return name.replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);
}

}
