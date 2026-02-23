import Models.*;

public class Main {
    public static void main(String[] args) {

                // Create Students
                UndergraduateStudent undergrad =
                        new UndergraduateStudent("esther","sss","CSE",222);

                GraduateStudent grad =
                        new GraduateStudent("anne","ss","IS",333,20);

                // Create Courses
                Course javaCourse = new Course("CO999","Java ",10,40);
                Course dbCourse = new Course("CS202", "Database Systems", 4, 25);

                // Enroll Students manually (without manager for now)
                javaCourse.addStudent(undergrad);
                undergrad.addCourse(javaCourse);

                dbCourse.addStudent(grad);
                grad.addCourse(dbCourse);
        System.out.println();

                // Print Student Details
                System.out.println(undergrad.getDetails());
                System.out.println("Undergrad Tuition: " + undergrad.calculateTuition());

                System.out.println("---------------------");

                System.out.println(grad.getDetails());
                System.out.println("Graduate Tuition: " + grad.calculateTuition());

                // Check relationships
                System.out.println("---------------------");
                System.out.println("Java Course Roster:");
                for (Student s : javaCourse.getRoster()) {
                    System.out.println(s.getname());
                }

                System.out.println("---------------------");
                System.out.println("Graduate Student Courses:");
                grad.getCourses().forEach((course, grade) ->
                        System.out.println(course.getCourseCode()));

        Instructor instructor=new Instructor("KALISA","EEE","CSE",400);
        instructor.getDetails();

        Student undergrad1 = new UndergraduateStudent("esther", "e@mail.com", "22222", 555);
        Student grad1= new GraduateStudent("anne", "a@mail.com", "33440",4444,6500);
             grad1.setTotalCredits(5);
        System.out.println(grad.calculateTuition());

        undergrad.updateGrade(javaCourse, 3.7);
        grad.updateGrade(dbCourse, 3.9);

        System.out.println("Undergrad GPA: " + undergrad.getGPA());
        System.out.println("Graduate GPA: " + grad.getGPA());


    }
            }




