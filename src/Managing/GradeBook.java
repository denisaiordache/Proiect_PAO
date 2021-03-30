package Managing;

import java.util.ArrayList;

public class GradeBook {
    protected ArrayList<Student> students;

    public GradeBook() {
        this.students = new ArrayList<Student>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
