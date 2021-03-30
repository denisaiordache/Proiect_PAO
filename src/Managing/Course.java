package Managing;

public class Course {
    protected String courseName;
    protected Teacher courseTeacher;

    public Course(String courseName, Teacher courseTeacher) {
        this.courseName = courseName;
        this.courseTeacher = courseTeacher; //e ok, deoarece relatia Course -> Teacher e one to one
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(Teacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    @Override
    public String toString() {
        return "Course name: " + this.courseName + "; course teacher: " + this.courseTeacher.toString();
    }
}
