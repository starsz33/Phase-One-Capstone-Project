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

            manager.addCourse(java);
            manager.addCourse(ds);
            manager.addCourse(ai);

            // create Student
            Student s1 = new UndergraduateStudent("Kaline", "Esthe@gmail.com", "Computer Science","5555");
            Student s2 = new UndergraduateStudent("Uwera", "Anne@gmail.com", "Computer Science","22201");
            Student s3 = new GraduateStudent("Esther","est@gmail.com","Information Technology","5555",6000);
            Student s4 = new GraduateStudent("Aline", "Sarah@gmail.com", "Information Systems","77777",6000);

            manager.registerStudent(s1);
            manager.registerStudent(s2);
            manager.registerStudent(s3);
            manager.registerStudent(s4);

            try {

                manager.enrollStudentInCourse("2201365","coe");
                manager.enrollStudentInCourse("5444","ggg");

                manager.enrollStudentInCourse("88888","ppp");
                manager.enrollStudentInCourse("77777","uuu009");

                manager.enrollStudentInCourse("6666","nnnn");
                manager.enrollStudentInCourse("888","bbbbn");

                manager.enrollStudentInCourse("6666","bbbu09");
                manager.enrollStudentInCourse("7778","vgg88");

            } catch (CourseFullException e) {
                System.out.println("FULL ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("DUPLICATE ERROR: " + e.getMessage());
            }
            try {
                manager.enrollStudentInCourse(s1.getStudentId(), ai.getCourseCode());
                System.out.println("Enrollment successful!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
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
            for (Student s : manager.getAllStudents()) {
                System.out.println(s.getname() +
                        " | Dept: " + s.getDepartment() +
                        " | GPA: " + s.getGPA());
            }
        }
    }

