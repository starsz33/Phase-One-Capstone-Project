package Models;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private String Department;
    private String studentId;
    private double GPA = 0.0;
    protected int totalCredit;
    Map<Course, Double> courseMap;

    public Student(String name, String email, String Department, String studentId) {
        super(name, email);
        this.Department = Department;
        this.studentId = studentId;
        courseMap = new HashMap<>();
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGPA() {
        return GPA;
    }

    public void setTotalCredits(int totalCredit) {
        this.totalCredit = totalCredit;
    }
    
    public int getTotalCredits() {
        return totalCredit;
    }
    // Adds a course to the student's course map and updates total credits
    public void addCourse(Course course) {
        courseMap.put(course, 0.0);
        totalCredit += course.getCredits(); // Update total credits for tuition calculation
    }

    public Map<Course, Double> getCourses() {
        return courseMap;
    }

    public void updateGrade(Course course, double grade) {
        if (courseMap.containsKey(course)) {
            courseMap.put(course, grade);
            calculateGPA();
        }
    }

    // Calculates weighted GPA based on course credits
    public double calculateGPA() {
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Map.Entry<Course, Double> entry : courseMap.entrySet()) {
            Course course = entry.getKey();
            double grade = entry.getValue();
            int credits = course.getCredits();
            
            totalGradePoints += (grade * credits);
            totalCredits += credits;
        }

        if (totalCredits > 0) {
            this.GPA = totalGradePoints / totalCredits;
        }
        return GPA;
    }

    public double calculateTuition() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Student: " + getname() + ", ID: " + studentId;
    }
}



