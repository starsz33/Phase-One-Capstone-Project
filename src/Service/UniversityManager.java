package Service;

import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;
import Models.Student;
import Models.Course;
import Models.Instructor;

import java.util.ArrayList;
import java.util.List;

public class UniversityManager {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;

    public UniversityManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.instructors = new ArrayList<>();
    }
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
    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }
    public void enrollStudentInCourse(String studentId, String courseCode)
            throws CourseFullException, StudentAlreadyEnrolledException {
        Student student = findStudentById(studentId);
        Course course = courses.stream()
                .filter(c -> c.getCourseCode().equals(courseCode))
                .findFirst()
                .orElse(null);
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student or Course not found!");
        }
        if (course.getStudents().size() >= course.getMaxStudents()) {
            throw new CourseFullException("Course " + courseCode + " is full!");
        }

        if (course.getStudents().contains(student)) {
            throw new StudentAlreadyEnrolledException("Student " + studentId + " is already enrolled in " + courseCode);
        }
        course.getStudents().add(student);
        student.addCourse(course);
    }
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

    // ----------------- Instructors -----------------
    public void registerInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructors;
    }
}