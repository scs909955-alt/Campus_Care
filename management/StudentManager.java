
package management;

// IMPORT: ArrayList is part of Java's Collections Framework.
import java.util.ArrayList;

// IMPORT: Allows us to use the Student class.
import person.Student;

// FILE HANDLING IMPORTS
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

// STUDENT MANAGEMENT CLASS
public class StudentManager {

    // ARRAYLIST:
    // Stores Student objects dynamically.
    private ArrayList<Student> students = new ArrayList<>();

    // ADD STUDENT:
    // Adds a new Student object to the ArrayList.
    public void addStudent(Student s) {

        // Check for duplicate student ID.
        if (searchStudent(s.getId()) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        students.add(s);

        saveToFile();
        
        System.out.println("Student added successfully!");
    }

    // VIEW STUDENTS:
    // Displays all registered students.
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        for (Student s : students) {
            System.out.println("--------------------");
            s.displayDetails();
        }
    }

    // SEARCH STUDENT:
    // Searches for a student using their ID.
    public Student searchStudent(int id) {

        for (Student s : students) {

            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    // DELETE STUDENT:
    // Removes a student using their ID.
    
// DELETE STUDENT:
// Removes a student from ArrayList and updates the file.
public void deleteStudent(int id) {

    Student s = searchStudent(id);

    if (s != null) {

        // Remove student from ArrayList.
        students.remove(s);

        System.out.println("Student deleted successfully!");

        // Save the updated ArrayList to the text file.
        saveToFile();

    } else {

        System.out.println("Student not found!");
    }
}


// UPDATE STUDENT DETAILS
public void updateStudent(int id, String name, String department,
                          int total, int attended,
                          double m1, double m2, double m3) {

    Student s = searchStudent(id);

    if (s == null) {
        System.out.println("Student not found!");
        return;
    }

    try {

        // TEMPORARY OBJECT VALIDATES ALL NEW DETAILS FIRST.
        Student temp = new Student(id, name, department);
        temp.setAttendance(total, attended);
        temp.setMarks(m1, m2, m3);

        // UPDATE ORIGINAL OBJECT ONLY AFTER VALIDATION.
        s.setName(name);
        s.setDepartment(department);
        s.setAttendance(total, attended);
        s.setMarks(m1, m2, m3);

        // SAVE UPDATED INFORMATION.
        saveToFile();

        System.out.println("Student updated successfully!");

    } catch (IllegalArgumentException e) {

        System.out.println("Update failed: " + e.getMessage());
    }
}





// FILE HANDLING: SAVE STUDENTS

public void saveToFile() {

    // FILE WRITING: Opens campuscare.txt.
    try (BufferedWriter writer =
             new BufferedWriter(new FileWriter("campuscare.txt"))) {

        // WRITE EVERY STUDENT IN THE ARRAYLIST.
        for (Student s : students) {

            String record = s.getId() + "," +
                    s.getName() + "," +
                    s.getDepartment() + "," +
                    s.getTotalClasses() + "," +
                    s.getAttendedClasses() + "," +
                    s.getMarks1() + "," +
                    s.getMarks2() + "," +
                    s.getMarks3();

            writer.write(record);
            writer.newLine();
        }

        System.out.println("Records saved: " + students.size());

    } catch (IOException e) {

        System.out.println("File saving error: " + e.getMessage());
    }



    // FileWriter in overwrite mode updates the complete file.
    try (BufferedWriter writer =
             new BufferedWriter(new FileWriter("campuscare.txt"))) {

        // ENHANCED FOR LOOP: Visit every Student object.
        for (Student s : students) {

            // Convert student details into comma-separated text.
            String record = s.getId() + "," +
                            s.getName() + "," +
                            s.getDepartment() + "," +
                            s.getTotalClasses() + "," +
                            s.getAttendedClasses() + "," +
                            s.getMarks1() + "," +
                            s.getMarks2() + "," +
                            s.getMarks3();

            // Write one student per line.
            writer.write(record);
            writer.newLine();
        }

        System.out.println("Student records saved!");

    } catch (IOException e) {

        // EXCEPTION HANDLING: Handles file-writing errors.
        System.out.println("Error saving records: " + e.getMessage());
    }
}


// FILE HANDLING: LOAD STUDENTS
public void loadFromFile() {

    File file = new File("campuscare.txt");

// CREATE FILE IF IT DOES NOT EXIST.
try {
    if (file.createNewFile()) {
        System.out.println("CampusCare data file created!");
    }
} catch (IOException e) {
    System.out.println("Unable to create file: " + e.getMessage());
    return;
}

    students.clear();

    try (BufferedReader reader =
             new BufferedReader(new FileReader(file))) {

        String line;

        // Read the file one line at a time.
        while ((line = reader.readLine()) != null) {

            // SPLIT: Separate comma-separated values.
            String[] data = line.split(",", -1);

            // Check whether the record has all 8 fields.
            if (data.length != 8) {
                continue;
            }

            // Convert text into the required data types.
            int id = Integer.parseInt(data[0]);
            String name = data[1];
            String department = data[2];

            int total = Integer.parseInt(data[3]);
            int attended = Integer.parseInt(data[4]);

            double m1 = Double.parseDouble(data[5]);
            double m2 = Double.parseDouble(data[6]);
            double m3 = Double.parseDouble(data[7]);

            // Recreate the Student object.
            Student s = new Student(id, name, department);

            s.setAttendance(total, attended);
            s.setMarks(m1, m2, m3);

            // Add the reconstructed object to ArrayList.
            students.add(s);
        }

        System.out.println("Student records loaded: " + students.size());

    } catch (IOException | NumberFormatException e) {

        // EXCEPTION HANDLING: Handles file and conversion errors.
        System.out.println("Error loading records: " + e.getMessage());
    }
}


// AUTOMATIC STUDENT ID GENERATION
public int generateStudentId() {

    // Starting student ID.
    int nextId = 101;

    // Find the highest existing student ID.
    for (Student s : students) {

        if (s.getId() >= nextId) {
            nextId = s.getId() + 1;
        }
    }

    return nextId;
}


}
