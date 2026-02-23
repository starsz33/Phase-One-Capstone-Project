package Models;

public class UndergraduateStudent extends Student{
    double flatRate=500000;
    public UndergraduateStudent(String name, String email, String Department,int studentId) {
        super(name, email, Department, studentId);
    }
    @Override
    public double calculateTuition(){
        return flatRate;
    }
}
