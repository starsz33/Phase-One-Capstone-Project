package Service;

import Models.Course;
import Models.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Exception.CourseFullException;
import Exception.StudentAlreadyEnrolledException;
public class UniversityManager {
    private final List<Student> students;
    private final List<Course> courses;

    public UniversityManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void createCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudentInCourse(Student student, Course course)
            throws CourseFullException, StudentAlreadyEnrolledException {

        if (course.isFull()) {
            throw new CourseFullException("Course is full!");
        }

        if (student.getCourses().containsKey(course)) {
            throw new StudentAlreadyEnrolledException("Student already enrolled!");
        }

        course.addStudent(student);
        student.addCourse(course);
    }

    public double calculateAverageGPAByDepartment(String department) {
        return students.stream()
                .filter(student -> student.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(Student::getGPA).average().orElse(0.0);
    }

    public Student findTopPerformingStudent() {
        return students.stream().max(Comparator.comparingDouble(Student::getGPA)).orElse(null);
    }
    public List<Student> getStudents() {
        return students;
    }
}

