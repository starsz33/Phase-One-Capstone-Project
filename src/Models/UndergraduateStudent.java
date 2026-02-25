package Models;

public class UndergraduateStudent extends Student{
    // Flat rate tuition for undergraduate students
    double flatRate=500000;
    
    public UndergraduateStudent(String name, String email, String Department,String studentId) {
        super(name, email, Department, studentId);
    }
    
    // Override: Undergraduate students pay a flat rate regardless of credits
    @Override
    public double calculateTuition(){
        return flatRate;
    }
}
