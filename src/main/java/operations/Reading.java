package operations;

import grade.Student;
import grade.Status;

import java.io.*;
import java.util.ArrayList;

public class Reading {

    public ArrayList<Student> readTxt(String fileName) {
        ArrayList<Student> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] p = line.split("\\|");
                Student s = new Student(
                        Integer.parseInt(p[0]),
                        p[1],
                        p[2],
                        Integer.parseInt(p[3]),
                        p[4],
                        Double.parseDouble(p[5]),
                        p[6],
                        Integer.parseInt(p[7]),
                        Status.valueOf(p[8])
                );
                list.add(s);
            }

        } catch (Exception e) {}

        return list;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Student> readBin(String fileName) {
        ArrayList<Student> list = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            list = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {}

        return list;
    }
}
