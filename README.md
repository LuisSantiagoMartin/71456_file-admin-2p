# 📘 Sistema de Administración de Estudiantes  
Proyecto de persistencia en archivos TXT, JSON y BIN – Java + Maven

Este proyecto implementa un sistema CRUD de estudiantes utilizando Java.  
Permite **agregar, ver, actualizar, eliminar y guardar alumnos** en tres formatos:

- `studentsDB.txt` → Texto plano  
- `studentsDB.json` → Formato JSON  
- `studentsDB.bin` → Archivo binario (serializado)

El sistema incluye validaciones, manejo de excepciones con `try-with-resources` y un esquema JSON (`studentSchema.json`) para el formato esperado.

---

# 📂 Estructura del Proyecto

practica02/
├── src/
│ └── main/
│ └── java/
│ ├── grade/
│ │ ├── Student.java
│ │ ├── DegreeFI.java
│ │ └── Status.java
│ │
│ ├── operations/
│ │ ├── Writing.java
│ │ ├── Reading.java
│ │ ├── Update.java
│ │ ├── Delete.java
│ │ └── Create.java
│ │
│ └── sanp/
│ └── App.java
├── studentSchema.json
├── pom.xml (opcional, si usas Maven)
├── .gitignore
└── README.md

markdown
Copiar código

---

# 🧠 Documentación del Código

A continuación se describe cada clase y los métodos implementados, con una breve explicación de su funcionamiento.

---

## 🔵 1. `grade.Student`

**Propósito:** Modelo que representa un estudiante. Es `Serializable` para poder guardarse/recuperarse en formato BIN.

**Atributos:**
- `int idStudent` — Matrícula/ID único.
- `String name` — Nombre.
- `String lastName` — Apellido.
- `int degree` — Carrera (número 1–6).
- `String semester` — Semestre.
- `double promedio` — Promedio.
- `String email` — Correo electrónico.
- `int age` — Edad.
- `Status status` — Estado (`ACTIVE`, `INACTIVE`, `SUSPENDED`).

**Métodos importantes:**
- `toString()` — Devuelve todos los campos en una línea usando `|` como separador. Se usa para guardar en TXT.
  - Ejemplo: `1|Luis|Lopez|3|5|9.1|luis@mail.com|21|ACTIVE`
- `toJson()` — Construye un JSON representando el estudiante (string manual). Se usa para producir la salida en `studentsDB.json`.
- Getters y setters estándar para cada atributo.

---

## 🔵 2. `grade.DegreeFI`

**Propósito:** Representa una carrera con `idDegree` y `degreeName`.  
`toString()` devuelve `idDegree|degreeName`. (Útil si quisieras guardar lista de carreras.)

---

## 🔵 3. `grade.Status` (enum)

Estados permitidos:
```java
public enum Status {
    ACTIVE,
    INACTIVE,
    SUSPENDED
}
Se usa en Student.status para limitar valores válidos.

🟣 4. operations.Writing
Propósito: Persistencia — guarda una lista de estudiantes en los tres formatos.

Métodos:

writeTxt(String fileName, ArrayList<Student> students)

Reescribe fileName con una línea por estudiante usando toString() y | como separador. Usa try-with-resources (PrintWriter).

writeJson(String fileName, ArrayList<Student> students)

Escribe un [] con objetos JSON (generados por Student.toJson()), con formato legible.

writeBin(String fileName, ArrayList<Student> students)

Serializa el ArrayList<Student> completo con ObjectOutputStream.

Todos los métodos imprimen mensajes y capturan excepciones (logging con e.printStackTrace() en esta versión).

🟣 5. operations.Reading
Propósito: Recuperación — lee estudiantes desde los archivos.

Métodos:

ArrayList<Student> readTxt(String fileName)

Lee línea por línea, divide por |, convierte tipos y construye objetos Student. Devuelve la lista. Si el archivo no existe o está vacío, retorna lista vacía.

ArrayList<Student> readBin(String fileName)

Deserializa un ArrayList<Student> desde archivo BIN usando ObjectInputStream. Retorna lista (vacía si falla).

(La lectura JSON no usa librería externa en la versión simple; se usa Student.toJson() para escribir y lectura principal se hace por TXT o BIN.)

🟣 6. operations.Update
Propósito: Modificar registros.

Método:

boolean updateStudent(ArrayList<Student> students, int id, String newEmail)

Recorre la lista, busca por idStudent y cambia el email. Retorna true si actualiza, false si no existe.

🟣 7. operations.Delete
Propósito: Eliminar registros.

Método:

boolean deleteStudent(ArrayList<Student> students, int id)

Usa removeIf para eliminar el estudiante con el ID dado. Retorna true si se eliminó algún elemento.

🔵 8. sanp.App (Interfaz por consola, menú)
Propósito: Interfaz de usuario por consola que coordina las operaciones CRUD y la persistencia.

Flujo principal (resumido):

Carga lista inicial desde studentsDB.txt (si existe) usando Reading.readTxt.

Muestra menú con opciones:

Añadir alumno

Mostrar alumnos

Actualizar alumno (email por ID)

Eliminar alumno (por ID, con confirmación)

Guardar (escribe TXT, JSON y BIN)

Salir (guarda antes de salir)

Validaciones:

ID numérico y no duplicado.

Email con formato básico (contiene @ y . y no empieza/termina con @).

Campos obligatorios no vacíos.

Status válido (enum).

Carrera: el menú muestra y acepta solo números 1–6 (SISTEMAS, CIVIL, MECATRONICA, ENERGIA, SOFTWARE, MECANICO).

Al añadir, se construye Student y se agrega a la lista en memoria.

Al guardar, Writing reescribe los archivos studentsDB.txt, studentsDB.json, studentsDB.bin.

📄 Esquema JSON (studentSchema.json)
El proyecto incluye studentSchema.json que define el formato esperado:

json
Copiar código
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "title": "Student",
  "type": "object",
  "properties": {
    "idStudent": { "type": "integer" },
    "name": { "type": "string" },
    "lastName": { "type": "string" },
    "degree": { "type": "integer" },
    "semester": { "type": "string" },
    "promedio": { "type": "number" },
    "email": { "type": "string", "format": "email" },
    "age": { "type": "integer", "minimum": 0 },
    "status": {
      "type": "string",
      "enum": ["ACTIVE", "INACTIVE", "SUSPENDED"]
    }
  },
  "required": [
    "idStudent", "name", "lastName", "degree",
    "semester", "promedio", "email", "age", "status"
  ]
}


📘 Manual de Usuario (breve)
Añadir alumno

Selecciona opción 1.

Introduce ID (entero; no duplicado), nombre, apellido.

Selecciona la carrera por número (1–6).

Introduce semestre, promedio, email, edad y status (ACTIVE/INACTIVE/SUSPENDED).

El alumno queda en memoria hasta que uses 5) Guardar o 6) Salir.

Mostrar alumnos

Selecciona opción 2 → lista los estudiantes en pantalla (líneas |).

Actualizar alumno

Selecciona opción 3.

Introduce ID del alumno a modificar.

Introduce el nuevo email (se valida formato básico).

Eliminar alumno

Selecciona opción 4.

Introduce ID y confirma con s para eliminar.

Guardar

Selecciona opción 5 (o 6 al salir): se generan/actualizan:

studentsDB.txt — texto plano (una línea por alumno)

studentsDB.json — array JSON (salida manual)

studentsDB.bin — serialización Java
