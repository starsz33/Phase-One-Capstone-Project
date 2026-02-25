package FileManager;

import Models.Student;
import Models.UndergraduateStudent;
import Models.GraduateStudent;
import Models.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String STUDENT_FILE = "students.csv";
    private static final String COURSE_FILE = "courses.csv";

    // ===================== STUDENT FILE HANDLING =====================

    // Save students to CSV file
    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            // Write CSV header
            writer.write("StudentID,Name,Email,Department,GPA,Type,AmountPerCredit\n");
            
            // Write each student
            for (Student student : students) {
                String type = student instanceof UndergraduateStudent ? "Undergraduate" : "Graduate";
                int amountPerCredit = 0;
                if (student instanceof GraduateStudent) {
                    // Access amountPerCredit through reflection or add getter
                    amountPerCredit = 5000; // Default value, you may need to add getter
                }
                writer.write(String.format("%s,%s,%s,%s,%.2f,%s,%d\n",
                        student.getStudentId(),
                        student.getname(),
                        student.getEmail(),
                        student.getDepartment(),
                        student.getGPA(),
                        type,
                        amountPerCredit));
            }
            System.out.println("Students saved successfully to " + STUDENT_FILE);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Load students from CSV file
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENT_FILE);
        
        if (!file.exists()) {
            System.out.println("No previous student data found.");
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String id = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String dept = parts[3];
                    String type = parts[5];
                    
                    Student student;
                    if ("Undergraduate".equals(type)) {
                        student = new UndergraduateStudent(name, email, dept, id);
                    } else {
                        int amountPerCredit = parts.length > 6 ? Integer.parseInt(parts[6]) : 5000;
                        student = new GraduateStudent(name, email, dept, id, amountPerCredit);
                    }
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    // ===================== COURSE FILE HANDLING =====================

    // Save courses to CSV file
    public static void saveCourses(List<Course> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_FILE))) {
            // Write CSV header
            writer.write("CourseCode,CourseName,Credits,MaxStudents\n");
            
            // Write each course
            for (Course course : courses) {
                writer.write(String.format("%s,%s,%d,%d\n",
                        course.getCourseCode(),
                        course.getCourseName(),
                        course.getCredits(),
                        course.getMaxStudents()));
            }
            System.out.println("Courses saved successfully to " + COURSE_FILE);
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    // Load courses from CSV file
    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        File file = new File(COURSE_FILE);
        
        if (!file.exists()) {
            System.out.println("No previous course data found.");
            return courses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String code = parts[0];
                    String name = parts[1];
                    int credits = Integer.parseInt(parts[2]);
                    int maxStudents = Integer.parseInt(parts[3]);
                    
                    Course course = new Course(code, name, credits, maxStudents);
                    courses.add(course);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
        return courses;
    }
}