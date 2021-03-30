package Managing;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Ionescu Maria",22,"mionescu@gmail.com","0760214564",231);
        Teacher t1 = new Teacher("Ionescu Andreea",34,"aionescu@gmail.com",2400.2, "lector");
        Course c1 = new Course("Statistics",t1);
        c1.setCourseName("Statistica");
        Date date1 = new Date(12,03,2020);
        Grade g1 = new Grade(10, date1);

        GradeBook catalog = new GradeBook();
        catalog.students.add(s1);

        Student s2 = new Student("Burcea Andrei",24,"email@yahoo.com","0754852145",231);

        Teacher t2 = new Teacher("Lupu Mihaela",42,"mlupu@gmail.com",10000,"profesor");
        Teacher t3 = new Teacher("Oprisan Emil",27,"emopr@yahoo.com",2500,"doctorand");

        Course c2 = new Course("Baze de date",t2);
        Course c3 = new Course("Programare orientata pe obiecte",t3);

        ArrayList<Grade> n1 = new ArrayList<Grade>();
        n1.add(new Grade(10,new Date(10,2,2020)));
        n1.add(new Grade(8,new Date(1,2,2020)));
        n1.add(new Grade(8,new Date(15,3,2020)));



        s1.getGrades().put(c1.getCourseName(), n1);
        s1.getGrades().put(c2.getCourseName(),n1);


        GradeBook catalog1 = new GradeBook();
        catalog1.getStudents().add(s1);
        catalog1.getStudents().add(s2);
        //System.out.println(catalog1.getStudents().size());

        Group grupa1 = new Group(231);
        grupa1.setGradeBook(catalog1);



        Servicii servicii_student = new Servicii();
        servicii_student.afisare_note(s2);
        servicii_student.afisare_student(s1);
        System.out.println(servicii_student.medie_totala(s1));
        if (servicii_student.promoted(s1))
        {
            System.out.println("promovat");
        }
        else
        {
            System.out.println("nepromovat");
        }

        servicii_student.afisare_note(s2);
        System.out.println(servicii_student.students_course(c1,catalog1));

        servicii_student.afis_profesor(t1);

        Faculty fac1 = new Faculty("Facultatea de Matematica si Informatica","Bucuresti");
        fac1.getGroups().add(grupa1); //adaug o grupa in lista de grupe a facultatii

        System.out.println(servicii_student.medie_grupa(grupa1));

        System.out.println(servicii_student.statut(fac1));

        System.out.println(servicii_student.medii_facultate(fac1));  //mediile dintr-o facultate
        System.out.println(servicii_student.repartizare(s1,fac1));
        servicii_student.afis_catalog(catalog1);
        servicii_student.inscriere_curs(s1,c1);

        servicii_student.afis_fac(fac1);

        Teacher t4 = new Teacher("Raileanu Mircea", 40, "rmircea@gmail.com", 5500, "conferentiar");
        Course c4 = new Course("Algebra liniara",t4);

        servicii_student.inscriere_curs(s1,c4);







    }


}
