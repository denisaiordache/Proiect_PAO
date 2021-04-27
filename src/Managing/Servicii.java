package Managing;


import CSVFiles.Audit;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.*;

public class Servicii {

    Audit audit = Audit.getInstance();


    //1. metoda pentru afisarea informatiilor legate de un student (fara note)
    public void afisare_student(Student s)
    {

        audit.writeInfo("afisare_student");
        System.out.println(s);
        System.out.println("Phone number: " + s.getPhoneNumber() + "\nGroup: " + s.getGroup());
    }

    //2.metoda pentru afisarea notelor unui student
    public void afisare_note(Student s)
    {

        afisare_student(s);
        audit.writeInfo("afisare_note");
        for (String key: s.getGrades().keySet())
        {
            System.out.println(key);
            System.out.println(s.getGrades().get(key));

        }
    }

    //3.  metoda pentru calculul mediei totale a unui student
    public double medie_totala(Student s)
    {
        audit.writeInfo("medie_totala");
        double medie = 0;
        int n = 0;
        for (String key: s.getGrades().keySet())
        {
            n++;
            int sum = 0;
            for(Grade g: s.getGrades().get(key) )
            {
                sum += g.getValue();
            }
            int nr = s.getGrades().get(key).size();
            medie += sum/nr;


        }
        return (double)medie/n;

    }

    //4. este promovat sau nu
    public boolean promoted(Student s)
    {
        audit.writeInfo("promoted");
        double medie = medie_totala(s);
        if (Math.round(medie) >= 5)
        {
            return true;
        }
        else
            return false;
    }

    //5. stergere student din catalog
    public void delete_student(GradeBook gradeBook, Student s)
    {
        audit.writeInfo("delete_student");
        int idx = gradeBook.getStudents().indexOf(s);
        gradeBook.getStudents().remove(idx);
    }

    //METODE CURSURI

    //6. Afisare informatii despre un curs
    public void afis_curs(Course c)
    {
        audit.writeInfo("afis_curs");
        System.out.println("Course name: " + c.getCourseName());
        System.out.println("Course teacher: " +c.getCourseTeacher());
    }

    //7. afisare nr studenti inscrisi la un curs
    public int students_course(Course c, GradeBook gradeBook)
    {
        audit.writeInfo("students_course");
        int nr = 0;
        ArrayList<Student> students = gradeBook.getStudents();
        for (Student s: students)
            for (String key: s.getGrades().keySet())
            {
                if (c.getCourseName().equals(key))
                    nr++;
            }
        return nr;

    }

    //8. adaugare nota la un anumit curs
    public void addGrade(Student s, Course c, Grade g)
    {
        audit.writeInfo("addGrade");
        s.getGrades().get(c.getCourseName()).add(g);
    }

    //9. marire salariu profesor in functie de pozitia sa in ierarhie (doctorand, lector, conferentiar, profesor)
    //10%, 15%, 20%, 25%
    public void increaseSalary(Teacher t)
    {
        audit.writeInfo("increaseSalary");
        String f = t.getFunction();
        if (f.equals("doctorand"))
        {
            double actual_salary = t.getSalary();
            t.setSalary(actual_salary*1.1);
        }

        else
            if (f.equals("lector"))
            {
                double actual_salary = t.getSalary();
                t.setSalary(actual_salary*1.15);
            }
            else
                if(f.equals("conferentiar"))
                {
                    double actual_salary = t.getSalary();
                    t.setSalary(actual_salary*1.2);
                }
                else
                {
                    double actual_salary = t.getSalary();
                    t.setSalary(actual_salary*1.25);
                }
    }

    //10. afisare informatii profesor
    public void afis_profesor(Teacher t)
    {
        audit.writeInfo("afis_profesor");
        System.out.println(t);
        System.out.println("Salary: " + t.getSalary() + "\nFunction: " +t.getFunction());
    }


    //11. medie per grupa
    public double medie_grupa(Group g)
    {
        audit.writeInfo("medie_grupa");
        GradeBook catalog = g.getGradeBook();
        double sum = 0;
        for(Student s: catalog.getStudents())
        {
            sum+= medie_totala(s);
        }
        return sum/catalog.getStudents().size();
    }

    //12. ierarhie a facultatilor in functie de medie
    public String statut(Faculty f) {
        audit.writeInfo("statut");
        ArrayList<Group> groups = f.getGroups();
        double sum = 0;
        for (Group g : groups) {
            sum += medie_grupa(g);

        }

        double medie = sum / groups.size();
        if (medie >= 9.50)
            return f.getName() + " " + f.getCity() +  ": Ivy league";
        else if (medie >= 8.50)
            return f.getName() +  " " + f.getCity() + ": Good rank";
        else if (medie >= 7)
            return f.getName() + " " + f.getCity() + ": Average";
        else
            return f.getName() +  " " + f.getCity() + ": Low";
    }

    //13. functie ce returneaza un vector cu mediile dintr-o facultate

    public ArrayList<Double> medii_facultate(Faculty f)
    {
        audit.writeInfo("medii_facultate");
        ArrayList<Group> groups = f.getGroups();
        ArrayList<Double>  medii =  new ArrayList<Double>();
        for (Group g : groups)
        {
            for (Student s: g.getGradeBook().getStudents())
            {
                medii.add(medie_totala(s));
            }
        }

        Collections.sort(medii);
        return medii;
    }

    //14. metoda ce determina daca un student e la taxa/buget
    public String repartizare(Student s, Faculty f)
    {
        audit.writeInfo("repartizare");
        ArrayList<Double> medii = medii_facultate(f);
        double medie_student = medie_totala(s);
        int idx = medii.indexOf(medie_student);
        if (idx >= medii.size()/3)
            return "Buget";
        else
            return "Taxa";
    }

    Comparator<Student> sortStudentByLastNameAndAfterByFirstName = new Comparator<Student>()
    {
        @Override
        public int compare(Student s1, Student s2) {
            String firstName1, firstName2, lastName1, lastName2;
            firstName1 = s1.getName().split(" ")[0];
            firstName2 = s2.getName().split(" ")[0];
            lastName1 = s1.getName().split(" ")[1];
            lastName2 = s2.getName().split(" ")[1];

            int firstNameCompare = firstName1.compareTo( firstName2 );
            int lastNameCompare = lastName1.compareTo(lastName2);
            return (lastNameCompare == 0) ? firstNameCompare : lastNameCompare;
        }
    };

    //15. afisare catalog
    public void afis_catalog(GradeBook gb)
    {
        audit.writeInfo("afis_catalog");
        gb.getStudents().sort(sortStudentByLastNameAndAfterByFirstName);
        for(Student s: gb.getStudents())
        {
            afisare_note(s);
        }

    }

    //16. inscriere student la un anume curs
    public void inscriere_curs(Student s, Course c)
    {
        audit.writeInfo("inscriere_curs");
        Map<String, ArrayList<Grade>> grades = s.getGrades();
        if(grades.containsKey(c.getCourseName()))
        {
            System.out.println("Student already enrolled at this course.");
        }
        else
        {
            grades.put(c.getCourseName(),new ArrayList<Grade>());
        }

    }

    //17. afisare informatii facultate
    public void afis_fac(Faculty f)
    {
        audit.writeInfo("afis_fac");
        System.out.println("Faculty name: " + f.getName());
        System.out.println("\nCity: " + f.getCity());
        for (Group g: f.getGroups())
        {
            afis_gr(g);
        }

    }

    //18. afisare informatii grupa
    public void afis_gr (Group g)
    {
        audit.writeInfo("afis_gr");
        System.out.println("Group Id: " + g.getGroupId());
        afis_catalog(g.getGradeBook());
    }

    public void inchidere_fisier()
    {
        audit.inchidere();
    }


}
