package CSVFiles;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;

public class Audit {
    private static Audit writing_instance = null;
    private static PrintWriter writer;

   private Audit()
    {

        try
        {

            writer = new PrintWriter(new File("src/CSVFiles/audit.csv"));
            writer.write("Nume actiune  Timestamp" + "\n");
            //writer.close();

        } catch ( FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit");
        }

    }

    public static Audit getInstance() {
        if (writing_instance == null)
            writing_instance = new Audit();

        return writing_instance;
    }



    public void writeInfo(String methodName)
    {


        java.util.Date d = new java.util.Date();
        writer.write(methodName+ " " + d.toString() + "\n");


    }

   public void inchidere() {

        writer.close();

    }


    }

