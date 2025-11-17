package operations;

import grade.Student;
import java.util.ArrayList;

public class Update {

    public boolean updateStudent(ArrayList<Student> students, int id, String newEmail) {
        for (Student s : students) {
            if (s.getIdStudent() == id) {
                s.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }
}
