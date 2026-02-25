package Models;

public class Instructor extends Person{
    private String department;
    private int salary;

    public Instructor(String name, String email, String department,int salary) {
        super(name, email);
        this.department = department;
        this.salary=salary;
    }

    public int getSalary() {
        return salary;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setSalary(int salary){
        this.salary=salary;
    }
    @Override
    public String getDetails() {
        return "Instructor:"+getname()+"Depatment"+department+"Salary:"+salary;
    }

}
