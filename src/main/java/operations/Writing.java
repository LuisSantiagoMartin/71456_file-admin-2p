package operations;

import grade.Student;
import java.io.*;
import java.util.ArrayList;

public class Writing {

    public void writeTxt(String fileName, ArrayList<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students) pw.println(s.toString());
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void writeJson(String fileName, ArrayList<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("[");
            for (int i = 0; i < students.size(); i++) {
                pw.print("  " + students.get(i).toJson());
                if (i < students.size() - 1) pw.println(",");
            }
            pw.println("\n]");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void writeBin(String fileName, ArrayList<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
        } catch (Exception e) { e.printStackTrace(); }
    }
}



