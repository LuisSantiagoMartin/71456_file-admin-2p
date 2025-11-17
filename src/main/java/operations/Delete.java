package operations;

import grade.Student;
import java.util.ArrayList;

public class Delete {

    public boolean deleteStudent(ArrayList<Student> students, int id) {
        return students.removeIf(s -> s.getIdStudent() == id);
    }
}
