package Managing;

public class Group {
    protected int groupId;
    protected GradeBook gradeBook;

    public Group(int groupId) {
        this.groupId = groupId;
        this.gradeBook = new GradeBook();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public GradeBook getGradeBook() {
        return gradeBook;
    }

    public void setGradeBook(GradeBook gradeBook) {
        this.gradeBook = gradeBook;
    }
}
