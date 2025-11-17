package grade;

import java.io.Serializable;

public class Student implements Serializable {

    private int idStudent;
    private String name;
    private String lastName;
    private int degree;
    private String semester;
    private double promedio;
    private String email;
    private int age;
    private Status status;

    public Student() {}

    public Student(int idStudent, String name, String lastName, int degree, String semester,
                   double promedio, String email, int age, Status status) {
        this.idStudent = idStudent;
        this.name = name;
        this.lastName = lastName;
        this.degree = degree;
        this.semester = semester;
        this.promedio = promedio;
        this.email = email;
        this.age = age;
        this.status = status;
    }

    // SETTERS
    public void setIdStudent(int idStudent) { this.idStudent = idStudent; }
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDegree(int degree) { this.degree = degree; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setStatus(Status status) { this.status = status; }

    // GETTERS
    public int getIdStudent() { return idStudent; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public int getDegree() { return degree; }
    public String getSemester() { return semester; }
    public double getPromedio() { return promedio; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public Status getStatus() { return status; }

    @Override
    public String toString() {
        return idStudent + "|" + name + "|" + lastName + "|" + degree + "|" +
               semester + "|" + promedio + "|" + email + "|" + age + "|" +
               status;
    }

    public String toJson() {
        return "{"
            + "\"idStudent\":" + idStudent + ","
            + "\"name\":\"" + name + "\","
            + "\"lastName\":\"" + lastName + "\","
            + "\"degree\":" + degree + ","
            + "\"semester\":\"" + semester + "\","
            + "\"promedio\":" + promedio + ","
            + "\"email\":\"" + email + "\","
            + "\"age\":" + age + ","
            + "\"status\":\"" + status + "\""
            + "}";
    }
}
