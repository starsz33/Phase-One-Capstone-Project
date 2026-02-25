package Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
    public class Course {
        private String courseCode;
        private String courseName;
        private int credits;
        private int maxStudents;

        private List<Student> SList;

        public Course(String courseCode, String courseName, int credits, int maxStudents) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.credits = credits;
            this.maxStudents = maxStudents;
            this.SList= new ArrayList<>();
        }

        public boolean isFull() {
            return SList.size() >= maxStudents;
        }

        public String getCourseName() {
            return courseName;
        }

        public void addStudent(Student student) {
            SList.add(student);
        }

        public List<Student> getRoster() {
            return SList;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public int getMaxStudents() {
            return maxStudents;
        }

        public int getCredits() {
            return credits;
        }
        
        public List<Student> getStudents() {
            return SList;
        }
    }

