package Managing;

import java.util.ArrayList;

public class Faculty {
    private String name;
    private String city;
    private ArrayList<Group> groups;

    public Faculty(String name, String city) {
        this.name = name;
        this.city = city;
        this.groups = new ArrayList<Group>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
