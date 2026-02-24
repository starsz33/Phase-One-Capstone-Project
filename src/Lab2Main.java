import Models.Course;
import Models.GraduateStudent;
import Models.Student;
import Models.UndergraduateStudent;
import Service.UniversityManager;
import Models.*;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;


    public class Lab2Main {

        public static void main(String[] args) {

            UniversityManager manager = new UniversityManager();

            Course java = new Course("CS44","Computer Ethics",10,3);
            Course ds = new Course("ET44","Java Programming ",50,70);
            Course ai = new Course("CS201", "Artificial Intelligence", 8,50);

            manager.createCourse(java);
            manager.createCourse(ds);
            manager.createCourse(ai);

            // create Student
            Student s1 = new UndergraduateStudent("Kaline", "Esthe@gmail.com", "Computer Science",33333);
            Student s2 = new UndergraduateStudent("Uwera", "Anne@gmail.com", "Computer Science",22201);
            Student s3 = new GraduateStudent("Esther","est@gmail.com","Information Technology",5555,6000);
            Student s4 = new GraduateStudent("Aline", "Sarah@gmail.com", "Information Systems",444477,6000);

            manager.registerStudent(s1);
            manager.registerStudent(s2);
            manager.registerStudent(s3);
            manager.registerStudent(s4);

            try {

                manager.enrollStudentInCourse(s1, java);
                manager.enrollStudentInCourse(s1, ds);

                manager.enrollStudentInCourse(s2, java);
                manager.enrollStudentInCourse(s2, ds);

                manager.enrollStudentInCourse(s3, java);
                manager.enrollStudentInCourse(s3, ai);

                manager.enrollStudentInCourse(s4, ai);
                manager.enrollStudentInCourse(s1, java);

            } catch (CourseFullException e) {
                System.out.println("FULL ERROR: " + e.getMessage());
            } catch (StudentAlreadyEnrolledException e) {
                System.out.println("DUPLICATE ERROR: " + e.getMessage());
            }

            s1.updateGrade(java, 3.7);
            s1.updateGrade(ds, 3.5);

            s2.updateGrade(java, 3.9);
            s2.updateGrade(ds, 3.8);

            s3.updateGrade(java, 4.0);
            s3.updateGrade(ai, 3.9);

            s4.updateGrade(ai, 3.6);

            System.out.println("\n=== STREAM TESTING ===");

            double avgCS = manager.calculateAverageGPAByDepartment("Computer Science");
            System.out.println("Average GPA (Computer Science): " + avgCS);

            Student top = manager.findTopPerformingStudent();
            if (top != null) {
                System.out.println("Top Student: " + top.getname() +
                        " | GPA: " + top.getGPA());
            }
            System.out.println("\n=== ALL STUDENTS ===");
            for (Student s : manager.getStudents()) {
                System.out.println(s.getname() +
                        " | Dept: " + s.getDepartment() +
                        " | GPA: " + s.getGPA());
            }
        }
    }

