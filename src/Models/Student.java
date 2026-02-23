package Models;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private String Department;
    private int studentId;
    private double GPA=0.0;
    protected int totalCredit;
    Map<Course, Double> courseMap;

    public Student(String name, String email, String Department,int studentId) {
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getGPA() {
        return GPA;
    }

    public void setTotalCredits(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    //    public void setGPA(int GPA) {
//        this.GPA = GPA;
//    }
    public void addCourse(Course course) {
        courseMap.put(course, 0.0);
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

    private void calculateGPA() {
        double total = 0;

        for (double grade : courseMap.values()) {
            total += grade;
        }

        if (!courseMap.isEmpty()) {
            this.GPA = total / courseMap.size();
        }
    }
    public double calculateTuition() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Student: " + getname() + ", ID: " + studentId;
    }
}


