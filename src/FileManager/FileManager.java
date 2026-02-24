package FileManager;
import Models.Student;
import Models.Course;

import java.io.*;
import java.util.List;

    public class FileManager {

        private static final String STUDENT_FILE = "students.txt";
        private static final String COURSE_FILE = "courses.txt";

        // Save students to file
        public static void saveStudents(List<Student> students) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
                oos.writeObject(students);
                System.out.println("Students saved successfully!");
            } catch (IOException e) {
                System.out.println("Error saving students: " + e.getMessage());
            }
        }

        // Load students from file
        @SuppressWarnings("unchecked")
        public static List<Student> loadStudents() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
                return (List<Student>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("No previous student data found.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading students: " + e.getMessage());
            }
            return null;
        }

    }
