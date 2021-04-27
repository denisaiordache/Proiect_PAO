package Managing;

import java.util.*;

public class Student extends Person{
    private String phoneNumber;
    private int group;
    private  Map<String, ArrayList<Grade>> grades;

    public Student(String name, int age, String email, String phoneNumber, int group) {
        super(name, age, email);
        this.phoneNumber = phoneNumber;
        this.group = group;

        //TODO : initialization for map - done
        this.grades = new HashMap<String,ArrayList<Grade>>();
        ArrayList<Grade> purtare = new ArrayList<Grade>();
        purtare.add(new Grade(10, new Date(1,10,2020)));
        grades.put("purtare",purtare);

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Map<String, ArrayList<Grade>> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, ArrayList<Grade>> grades) {
        this.grades = grades;
    }
}

