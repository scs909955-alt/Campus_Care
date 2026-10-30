
package utility;

// IMPORT: Allows access to Student class.
import person.Student;

// REPORT GENERATOR CLASS
public class ReportGenerator {

    // STRINGBUFFER:
    // Builds a report dynamically by appending information.
    public String generateReport(Student s) {

        StringBuffer report = new StringBuffer();

        // APPEND: Adds text to the StringBuffer.
        report.append("\n================================\n");
        report.append("       CAMPUSCARE REPORT\n");
        report.append("================================\n");

        report.append("Student ID: ").append(s.getId()).append("\n");
        report.append("Name: ").append(s.getName()).append("\n");
        report.append("Department: ").append(s.getDepartment()).append("\n");

        report.append("--------------------------------\n");

        // ATTENDANCE DETAILS
        report.append("Total Classes: ")
              .append(s.getTotalClasses()).append("\n");

        report.append("Attended Classes: ")
              .append(s.getAttendedClasses()).append("\n");

        report.append("Attendance: ")
              .append(String.format("%.2f", s.calculateAttendance()))
              .append("%\n");

        // MARKS DETAILS
        report.append("--------------------------------\n");

        report.append("Subject 1: ").append(s.getMarks1()).append("\n");
        report.append("Subject 2: ").append(s.getMarks2()).append("\n");
        report.append("Subject 3: ").append(s.getMarks3()).append("\n");

        report.append("Average: ")
              .append(String.format("%.2f", s.calculateAverage()))
              .append("\n");

        // RETURN: Convert StringBuffer into a normal String.
        report.append("================================\n");

        return report.toString();
    }
}
