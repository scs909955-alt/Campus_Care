
import java.util.Scanner;

// IMPORT OUR PROJECT CLASSES
import person.Student;
import person.Faculty;
import management.StudentManager;
import utility.ReportGenerator;

// MAIN CLASS: Starting point of CampusCare.
public class Main {

    public static void main(String[] args) {

        // SCANNER: Accepts input from faculty.
        Scanner sc = new Scanner(System.in);

        // OBJECT CREATION: Manages all student records.
        StudentManager manager = new StudentManager();
        manager.loadFromFile();

        // OBJECT CREATION: Generates student reports.
        ReportGenerator generator = new ReportGenerator();

        // DUMMY FACULTY PROFILE.
        Faculty faculty = new Faculty(
            501566,
            "Suneetha Merugula",
            "CSE",
            "Java Programming",
            "Assistant Professor"
        );

        // Display faculty details.
        System.out.println("\n================================");
        System.out.println("       CAMPUSCARE PORTAL");
        System.out.println("================================");
        faculty.displayDetails();

        int choice;

        // DO-WHILE LOOP: Repeats menu until faculty exits.
        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Generate Student Report");
            System.out.println("7. Exit");

            choice = Integer.parseInt(sc.nextLine());

            // SWITCH: Executes the selected operation.
            switch (choice) {

                case 1:

                    // AUTOMATIC ID GENERATION
                    int id = manager.generateStudentId();
                    System.out.println("Generated Student ID: " + id);  

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    // CREATE A STUDENT OBJECT.
                   try {

                        Student s = new Student(id, name, department);

                        // Your existing attendance and marks input goes here.

                        

                        System.out.print("Enter Total Classes: ");
                        int total = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Attended Classes: ");
                        int attended = Integer.parseInt(sc.nextLine());

                        s.setAttendance(total, attended);

                        System.out.print("Enter Subject 1 Marks: ");
                        double m1 = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Subject 2 Marks: ");
                        double m2 = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Subject 3 Marks: ");
                        double m3 = Double.parseDouble(sc.nextLine());

                        s.setMarks(m1, m2, m3);

                        // CALL STUDENTMANAGER TO ADD STUDENT.
                        manager.addStudent(s);

                        

                    } catch (IllegalArgumentException e) {

                        System.out.println("Invalid input: " + e.getMessage());
                    } 
                    break;

                    

                case 2:

                    // DISPLAY ALL REGISTERED STUDENTS.
                    manager.viewStudents();

                    break;

                case 3:

                    System.out.print("Enter Student ID to search: ");
                    int searchId = Integer.parseInt(sc.nextLine());

                    Student found = manager.searchStudent(searchId);

                    if (found != null) {
                        System.out.println("\nStudent Found!");
                        found.displayDetails();
                    } else {
                        System.out.println("Student not found!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = Integer.parseInt(sc.nextLine());

                    manager.deleteStudent(deleteId);

                    break;
                
                
                case 5:

                    try {

                        System.out.print("Enter Student ID to update: ");
                        int updateId = Integer.parseInt(sc.nextLine());

                        // CHECK WHETHER STUDENT EXISTS.
                        Student existing = manager.searchStudent(updateId);

                        if (existing == null) {
                            System.out.println("Student not found!");
                            break;
                        }

                        System.out.println("Current student details:");
                        existing.displayDetails();

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter New Department: ");
                        String newDepartment = sc.nextLine();

                        System.out.print("Enter Total Classes: ");
                        int newTotal = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Attended Classes: ");
                        int newAttended = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Subject 1 Marks: ");
                        double newM1 = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Subject 2 Marks: ");
                        double newM2 = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Subject 3 Marks: ");
                        double newM3 = Double.parseDouble(sc.nextLine());

                        // CALL UPDATE METHOD.
                        manager.updateStudent(
                            updateId, newName, newDepartment,
                            newTotal, newAttended,
                            newM1, newM2, newM3
                        );

                    } catch (NumberFormatException e) {

                        System.out.println("Please enter valid numeric values!");

                    }

                    break;

                case 6:

                    System.out.print("Enter Student ID for report: ");
                    int reportId = Integer.parseInt(sc.nextLine());

                    Student student = manager.searchStudent(reportId);

                    if (student != null) {

                        // STRINGBUFFER REPORT GENERATION.
                        System.out.println(generator.generateReport(student));

                    } else {
                        System.out.println("Student not found!");
                    }

                    break;

                case 7:

                    System.out.println("Thank you for using CampusCare!");

                    break;

                default:

                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);

        // CLOSE SCANNER.
        sc.close();
    }
}
