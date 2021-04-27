package CSVFiles;

import Managing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Citire {
    private static Citire reading_instance = null;

    public static Citire getInstance() {
        if (reading_instance == null)
            reading_instance = new Citire();

        return reading_instance;
    }

    public ArrayList<Date> read_dates() {
        ArrayList<Date> lista_date = new ArrayList<>();
        try {
            RandomAccessFile f = new RandomAccessFile("src/CSVFiles/date.csv", "r");
            String row;

            while ((row = f.readLine()) != null) {
                String[] info = row.split(",");
                Date d = new Date(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                //System.out.println(d);
                lista_date.add(d);
            }

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("S-a produs o eroare!");
            e.printStackTrace();
        }

        return lista_date;

    }

    public ArrayList<Grade> read_grades() {
        ArrayList<Grade> lista_note = new ArrayList<>();
        try {
            RandomAccessFile f = new RandomAccessFile("src/CSVFiles/grade.csv", "r");
            String row;

            int index = 0;
            ArrayList<Date> lista_date = this.read_dates();

            //nr egale de obiecte citite din fisiere, nu e nevoie de verificare momentan
            while ((row = f.readLine()) != null) {
                Date d = lista_date.get(index);
                Grade g = new Grade(Integer.parseInt(row), d);
                //System.out.println(g);
                lista_note.add(g);
                index++;
            }

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("S-a produs o eroare!");
            e.printStackTrace();
        }

        return lista_note;
    }

    public ArrayList<Teacher> read_teachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        //Servicii s = new Servicii();
        try {
            RandomAccessFile f = new RandomAccessFile("src/CSVFiles/teachers.csv", "r");
            String row;

            while ((row = f.readLine()) != null) {

                String[] info = row.split(",");
                Teacher t = new Teacher(info[0], Integer.parseInt(info[1]), info[2], Double.parseDouble(info[3]), info[4]);
                //s.afis_profesor(t);
                teachers.add(t);
            }

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("S-a produs o eroare!");
            e.printStackTrace();
        }

        return teachers;
    }

    public ArrayList<Course> read_courses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            RandomAccessFile f = new RandomAccessFile("src/CSVFiles/courses.csv", "r");
            String row;

            int index = 0;
            ArrayList<Teacher> teachers = this.read_teachers();
            //System.out.println(teachers.size());

            //nr egale de obiecte citite din fisiere, nu e nevoie de verificare momentan
            while ((row = f.readLine()) != null) {
                Teacher t = teachers.get(index++);
                Course c = new Course(row, t);
                //System.out.println(c);
                courses.add(c);

            }

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("S-a produs o eroare!");
            e.printStackTrace();
        }

        return courses;
    }

    public ArrayList<Student> read_students() {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Grade> grades = this.read_grades();
        ArrayList<Course> courses = this.read_courses();
        Servicii serv = new Servicii();

        try {
            RandomAccessFile f = new RandomAccessFile("src/CSVFiles/students.csv", "r");
            String row;

            int index = 0;

            while ((row = f.readLine()) != null) {

                String[] info = row.split(",");
                Student s = new Student(info[0], Integer.parseInt(info[1]), info[2], info[3], Integer.parseInt(info[4]));
                for (int i = 0; i < courses.size(); i++) {
                    s.getGrades().put(courses.get(i).getCourseName(), grades); //TODO: deep copy (?)
                }
                serv.afisare_note(s);
                students.add(s);
                index++;
            }

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("S-a produs o eroare!");
            e.printStackTrace();
        }

        return students;
    }

}
