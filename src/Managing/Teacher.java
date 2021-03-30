package Managing;

public class Teacher extends Person{
    private double salary;
    private String function;

    public Teacher(String name, int age, String email, double salary, String function)
    {
        super(name, age, email);
        this.salary = salary;
        this.function = function;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}
