import Models.Student;
import FileManager.FileManager;
import Service.UniversityManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();
        List<Student> loaded = FileManager.loadStudents();
        if (loaded != null) {
            for (Student s : loaded) {
                manager.registerStudent(s);
            }
        }

    }
}