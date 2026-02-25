package Models;

public abstract class Person {
    private String name;
    private String email;
    Person(String name,String email){
        this.name=name;
        this.email=email;

    }
    public String getname() {
        return name;
    }

    // Fixed: Added 'this' keyword to properly set the name field
    public void setPname(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public abstract String getDetails();
    }

