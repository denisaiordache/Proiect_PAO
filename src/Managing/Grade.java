package Managing;

public class Grade {
    protected int value;
    protected Date date;

    public Grade(int value, Date date) {
        this.value = value;
        //this.date = date;
        this.date = new Date(date.getDay(),date.getMonth(),date.getYear());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Grade value:" + this.value + " evaluation date:" + this.date.toString() + "\n";
    }
}
