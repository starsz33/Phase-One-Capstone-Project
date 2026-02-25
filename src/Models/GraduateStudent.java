package Models;

public class GraduateStudent extends Student {
    int amountPerCredit; // Cost per credit hour
    int ResearchFee = 60000; // Additional research fee for graduate students

    public GraduateStudent(String name, String email, String Department, String studentId, int amountPerCredit) {
        super(name, email, Department, studentId);
        this.amountPerCredit = amountPerCredit;
    }

    // Override: Graduate students pay per credit + research fee
    @Override
    public double calculateTuition() {
        return (totalCredit * amountPerCredit) + ResearchFee;
    }
}