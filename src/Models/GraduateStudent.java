package Models;

public class GraduateStudent extends Student{
int Credits;
int amountPerCredit;
int ResearchFee=60000;
    public GraduateStudent(String name, String email, String Department, int studentId,int amountPerCredit)
    {
        super(name, email, Department,studentId);
        //this.Credits=Credits;
        this.amountPerCredit=amountPerCredit;
    }
    @Override
    public double calculateTuition() {
        return (totalCredit * amountPerCredit) + ResearchFee; // per credit + research fee
    }

//    @Override
//    public double calculateTuition(){
//        return(Credits*amountPerCredit)+ResearchFee;
//    }


}
