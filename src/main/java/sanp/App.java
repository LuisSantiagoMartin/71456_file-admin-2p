package sanp;

import grade.Student;
import grade.Status;
import operations.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static Writing w = new Writing();
    static Reading r = new Reading();
    static Update u = new Update();
    static Delete d = new Delete();

    public static void main(String[] args) {

        // cargar si existe
        students = r.readTxt("studentsDB.txt");

        int option;

        do {
            System.out.println("\n===== MENÚ ALUMNOS =====");
            System.out.println("1) Añadir alumno");
            System.out.println("2) Mostrar alumnos");
            System.out.println("3) Actualizar alumno");
            System.out.println("4) Eliminar alumno");
            System.out.println("5) Guardar");
            System.out.println("6) Salir");
            System.out.print("Opción: ");

            while (!sc.hasNextInt()) {
                System.out.println(" Ingresa un número válido.");
                sc.next();
            }
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> addStudent();
                case 2 -> showStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> saveAll();
                case 6 -> {
                    System.out.println("Saliendo...");
                    saveAll();
                }
                default -> System.out.println(" Opción no válida.");
            }

        } while (option != 6);
    }

    // ==========================
    //     VALIDACIONES
    // ==========================

    private static int getValidId() {
        System.out.print("ID: ");
        while (!sc.hasNextInt()) {
            System.out.println(" ID inválido. Debe ser número.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static String getValidEmail() {
        String email;
        while (true) {
            System.out.print("Email: ");
            email = sc.nextLine();

            if (email.contains("@") &&
                    email.contains(".") &&
                    !email.startsWith("@") &&
                    !email.endsWith("@")) {
                break;
            }
            System.out.println(" Email inválido. Ejemplo: usuario@mail.com");
        }
        return email;
    }

    private static String getNonEmptyString(String msg) {
        String text;
        do {
            System.out.print(msg);
            text = sc.nextLine().trim();
            if (text.isEmpty())
                System.out.println(" No puede estar vacío.");
        } while (text.isEmpty());
        return text;
    }

    private static Status getValidStatus() {
        while (true) {
            System.out.print("Status (ACTIVE / INACTIVE / SUSPENDED): ");
            try {
                return Status.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println(" Estado inválido.");
            }
        }
    }

    private static boolean idExists(int id) {
        for (Student s : students)
            if (s.getIdStudent() == id)
                return true;
        return false;
    }

    // ==========================
    //     CRUD
    // ==========================

    private static void addStudent() {

    System.out.print("ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.print("Nombre: ");
    String name = sc.nextLine();

    System.out.print("Apellido: ");
    String last = sc.nextLine();

    // ===== CARRERA (MODIFICADO) =====
    System.out.println("Elige la carrera:");
    System.out.println("1) SISTEMAS");
    System.out.println("2) CIVIL");
    System.out.println("3) MECATRONICA");
    System.out.println("4) ENERGIA");
    System.out.println("5) SOFTWARE");
    System.out.println("6) MECANICO");
    System.out.print("Opción: ");

    int degree;
    while (true) {
        while (!sc.hasNextInt()) {
            System.out.println(" Ingresa un número válido.");
            sc.next();
        }

        degree = sc.nextInt();
        sc.nextLine();

        if (degree >= 1 && degree <= 6)
            break;

        System.out.println(" Opción no válida. Selecciona 1-6.");
    }

    System.out.println("✔ Carrera seleccionada: " + degree);
    // =================================

    System.out.print("Semestre: ");
    String semester = sc.nextLine();

    System.out.print("Promedio: ");
    double prom = sc.nextDouble();
    sc.nextLine();

    System.out.print("Email: ");
    String email = sc.nextLine();

    System.out.print("Edad: ");
    int age = sc.nextInt();
    sc.nextLine();

    System.out.print("Status (ACTIVE/INACTIVE/SUSPENDED): ");
    String st = sc.nextLine();

    Student s = new Student(id, name, last, degree, semester, prom, email, age, Status.valueOf(st.toUpperCase()));
    students.add(s);

    System.out.println("✔ Alumno añadido.");
}


    private static void showStudents() {
        System.out.println("\n--- LISTA DE ALUMNOS ---");
        if (students.isEmpty()) {
            System.out.println("No hay alumnos almacenados.");
            return;
        }
        for (Student s : students)
            System.out.println(s);
    }

    private static void updateStudent() {
        System.out.print("ID del alumno a actualizar: ");

        while (!sc.hasNextInt()) {
            System.out.println(" Ingresa un número válido.");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        if (!idExists(id)) {
            System.out.println(" No existe ese ID.");
            return;
        }

        String newEmail = getValidEmail();

        if (u.updateStudent(students, id, newEmail)) {
            System.out.println("✔ Alumno actualizado.");
        }
    }

    private static void deleteStudent() {
        System.out.print("ID a eliminar: ");

        while (!sc.hasNextInt()) {
            System.out.println(" Ingresa número válido.");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();

        if (!idExists(id)) {
            System.out.println(" No existe ese ID.");
            return;
        }

        System.out.print("¿Seguro que quieres eliminarlo? (s/n): ");
        String c = sc.nextLine().toLowerCase();

        if (c.equals("s")) {
            if (d.deleteStudent(students, id)) {
                System.out.println("✔ Alumno eliminado.");
            }
        } else {
            System.out.println("Cancelado.");
        }
    }

    private static void saveAll() {
        w.writeTxt("studentsDB.txt", students);
        w.writeJson("studentsDB.json", students);
        w.writeBin("studentsDB.bin", students);
        System.out.println("✔ Datos guardados.");
    }
}
