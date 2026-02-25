package Models;

public class GraduateStudent extends Student {
    int amountPerCredit;
    int ResearchFee = 60000;

    public GraduateStudent(String name, String email, String Department, String studentId, int amountPerCredit) {
        super(name, email, Department, studentId);
        this.amountPerCredit = amountPerCredit;
    }
    @Override
    public double calculateTuition() {
        return (totalCredit * amountPerCredit) + ResearchFee;
    }
}