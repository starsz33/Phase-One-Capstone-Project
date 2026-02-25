package Service;

import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;
import Models.Student;
import Models.Course;

import java.util.ArrayList;
import java.util.List;

public class UniversityManager {

    private final List<Student> students;
    private final List<Course> courses;

    public UniversityManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // ----------------- Students -----------------
    public void registerStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(String id) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // ----------------- Courses -----------------
    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    // Enrolls a student in a course with proper exception handling
    public void enrollStudentInCourse(String studentId, String courseCode)
            throws CourseFullException, StudentAlreadyEnrolledException {
        // Find the student by ID
        Student student = findStudentById(studentId);
        // Find the course by code
        Course course = courses.stream()
                .filter(c -> c.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);

        // Validate student and course exist
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student or Course not found!");
        }

        // Check if course is full
        if (course.getStudents().size() >= course.getMaxStudents()) {
            throw new CourseFullException("Course " + courseCode + " is full!");
        }

        // Check if student is already enrolled
        if (course.getStudents().contains(student)) {
            throw new StudentAlreadyEnrolledException("Student " + studentId + " is already enrolled in " + courseCode);
        }

        // Enroll the student
        course.getStudents().add(student);
        student.addCourse(course); // This now also updates total credits
    }
    // Calculates average GPA for all students in a specific department
    public double calculateAverageGPAByDepartment(String department) {

        double totalGPA = 0;
        int count = 0;

        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                totalGPA =totalGPA +student.calculateGPA();
                count++;
            }
        }

        if (count == 0) {
            return 0.0;
        }

        return totalGPA / count;
    }
    // Finds the student with the highest GPA
    public Student findTopPerformingStudent() {

        if (students == null || students.isEmpty()) {
            return null;
        }

        Student topStudent = students.get(0);

        for (Student student : students) {
            if (student.calculateGPA() > topStudent.calculateGPA()) {
                topStudent = student;
            }
        }

        return topStudent;
    }
}