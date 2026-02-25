import Models.Student;
import Models.UndergraduateStudent;
import Models.GraduateStudent;
import Models.Course;
import Models.Instructor;
import Service.UniversityManager;
import FileManager.FileManager;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();
        List<Student> loadedStudents = FileManager.loadStudents();
        if (loadedStudents != null) {
            for (Student s : loadedStudents) {
                manager.registerStudent(s);
            }
        }

        List<Course> loadedCourses = FileManager.loadCourses();
        if (loadedCourses != null) {
            for (Course c : loadedCourses) {
                manager.addCourse(c);
            }
        }

        // Load enrollments after students and courses are loaded
        FileManager.loadEnrollments(manager.getAllStudents(), manager.getAllCourses());

        // Load instructors
        List<Instructor> loadedInstructors = FileManager.loadInstructors();
        if (loadedInstructors != null) {
            for (Instructor i : loadedInstructors) {
                manager.registerInstructor(i);
            }
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=====UNIVERSITY SYSTEM======");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Update Student Grade");
            System.out.println("5. View Student Record");
            System.out.println("6. Generate Dean's List (GPA > 3.5)");
            System.out.println("7. Instructor Management");
            System.out.println("8. Save & Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Type (1 = Undergraduate, 2 = Graduate): ");
                    String type = scanner.nextLine();

                    Student student;
                    if ("1".equals(type)) {
                        student = new UndergraduateStudent(name, email, dept, id);
                    } else {
                        int defaultAmountPerCredit = 5000;
                        student = new GraduateStudent(name, email, dept, id, defaultAmountPerCredit);
                    }

                    manager.registerStudent(student);
                    System.out.println("Student registered: " + student.getname());
                }

                case "2" -> {
                    System.out.print("Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Course Name: ");
                    String cname = scanner.nextLine();
                    System.out.print("Credits: ");
                    int credits = Integer.parseInt(scanner.nextLine());
                    System.out.print("Max Students: ");
                    int max = Integer.parseInt(scanner.nextLine());

                    Course course = new Course(code, cname, credits, max);
                    manager.addCourse(course);
                    System.out.println("Course created: " + course.getCourseName());
                }

                case "3" -> {
                    System.out.print("Student ID: ");
                    String sid = scanner.nextLine();
                    System.out.print("Course Code: ");
                    String ccode = scanner.nextLine();

                    try {
                        manager.enrollStudentInCourse(sid, ccode);
                        System.out.println("Enrollment successful!");
                    } catch (CourseFullException | StudentAlreadyEnrolledException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }

                case "4" -> {
                    System.out.print("Student ID: ");
                    String sid = scanner.nextLine();
                    Student student = manager.findStudentById(sid);
                    
                    if (student == null) {
                        System.out.println("Student not found!");
                    } else if (student.getCourses().isEmpty()) {
                        System.out.println("Student is not enrolled in any courses!");
                    } else {
                        System.out.println("Enrolled Courses:");
                        int index = 1;
                        List<Course> courseList = new ArrayList<>(student.getCourses().keySet());
                        for (Course c : courseList) {
                            System.out.println(index++ + ". " + c.getCourseCode() + " - " + c.getCourseName());
                        }
                        System.out.print("Select course number: ");
                        int courseNum = Integer.parseInt(scanner.nextLine());
                        
                        if (courseNum > 0 && courseNum <= courseList.size()) {
                            Course selectedCourse = courseList.get(courseNum - 1);
                            System.out.print("Enter grade (0.0 - 4.0): ");
                            double grade = Double.parseDouble(scanner.nextLine());
                            student.updateGrade(selectedCourse, grade);
                            System.out.printf("Grade updated! New GPA: %.2f%n", student.getGPA());
                        } else {
                            System.out.println("Invalid course selection!");
                        }
                    }
                }

                case "5" -> {  // View Student Record
                    System.out.print("Student ID: ");
                    String sid = scanner.nextLine();
                    Student student = manager.findStudentById(sid);
                    if (student != null) {
                        System.out.println("Name: " + student.getname());
                        System.out.println("Department: " + student.getDepartment());
                        System.out.printf("GPA: %.2f%n", student.getGPA());
                        System.out.printf("Tuition Fee: $%.2f%n", student.calculateTuition());
                        System.out.println("Courses:");
                        student.getCourses().forEach((course, grade) ->
                                System.out.println("  " + course.getCourseCode() + " - " + course.getCourseName() + " | Grade: " + grade));
                    } else {
                        System.out.println("Student not found!");
                    }
                }

                case "6" -> {
                    System.out.println("\n=== DEAN'S LIST ===");
                    
                    // Display top performing student (only if GPA > 0)
                    Student topStudent = manager.findTopPerformingStudent();
                    if (topStudent != null && topStudent.getGPA() > 0) {
                        System.out.printf("Top Performing Student: %s | Dept: %s | GPA: %.2f%n%n",
                                topStudent.getname(), topStudent.getDepartment(), topStudent.getGPA());
                    }
                    
                    // Display all students with GPA > 3.5
                    System.out.println("Students with GPA > 3.5:");
                    long count = manager.getAllStudents().stream()
                            .filter(s -> s.getGPA() > 3.5)
                            .peek(s -> System.out.printf("%s | Dept: %s | GPA: %.2f%n",
                                    s.getname(), s.getDepartment(), s.getGPA()))
                            .count();
                    
                    if (count == 0) {
                        System.out.println("No students qualify for Dean's List.");
                    }
                }

                case "7" -> {
                    instructorMenu(scanner, manager);
                }

                case "8" -> {
                    FileManager.saveStudents(manager.getAllStudents());
                    FileManager.saveCourses(manager.getAllCourses());
                    FileManager.saveEnrollments(manager.getAllStudents());
                    FileManager.saveInstructors(manager.getAllInstructors());
                    System.out.println("Data saved. Exiting...");
                    running = false;
                }

                default -> System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }

    // Instructor Management Submenu
    private static void instructorMenu(Scanner scanner, UniversityManager manager) {
        boolean inInstructorMenu = true;
        
        while (inInstructorMenu) {
            System.out.println("\n===== INSTRUCTOR MANAGEMENT =====");
            System.out.println("1. Register Instructor");
            System.out.println("2. View All Instructors");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1" -> {
                    System.out.print("Instructor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Salary: ");
                    String salaryInput = scanner.nextLine();
                    
                    try {
                        int salary = Integer.parseInt(salaryInput);
                        Instructor instructor = new Instructor(name, email, dept, salary);
                        manager.registerInstructor(instructor);
                        System.out.println("Instructor registered: " + instructor.getname());
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Invalid salary. Please enter a valid number.");
                    }
                }
                
                case "2" -> {
                    System.out.println("\n=== ALL INSTRUCTORS ===");
                    if (manager.getAllInstructors().isEmpty()) {
                        System.out.println("No instructors registered.");
                    } else {
                        for (Instructor i : manager.getAllInstructors()) {
                            System.out.printf("%s | Dept: %s | Email: %s | Salary: $%d%n",
                                    i.getname(), i.getDepartment(), i.getEmail(), i.getSalary());
                        }
                    }
                }
                
                case "3" -> {
                    inInstructorMenu = false;
                }
                
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}