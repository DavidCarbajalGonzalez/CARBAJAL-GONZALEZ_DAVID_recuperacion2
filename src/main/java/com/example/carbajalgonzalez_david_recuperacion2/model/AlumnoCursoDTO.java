package com.example.carbajalgonzalez_david_recuperacion2.model;

/**
 * DTO (Data Transfer Object) que representa un alumno con todos sus datos
 * personales y los cursos asociados.
 */
public class AlumnoCursoDTO {

    private String nombreAlumno;
    private String apellidos;
    private String nombreUsuario;
    private String direccion;
    private String telefono;
    private String cursos; // Cursos concatenados en una sola cadena
    private String nombreCurso; // Para listados simples

    /**
     * Constructor para crear un objeto AlumnoCursoDTO completo.
     *
     * @param nombre        Nombre del alumno.
     * @param apellidos     Apellidos del alumno.
     * @param nombreUsuario Nombre de usuario del alumno.
     * @param direccion     Dirección del alumno.
     * @param telefono      Teléfono de contacto del alumno.
     * @param cursos        Cursos asociados al alumno (concatenados en una cadena).
     */
    public AlumnoCursoDTO(String nombre, String apellidos, String nombreUsuario, String direccion, String telefono, String cursos) {
        this.nombreAlumno = nombre;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cursos = cursos;
    }

    /**
     * Constructor alternativo para mostrar solo nombre del alumno y curso.
     *
     * @param nombreAlumno Nombre completo del alumno.
     * @param nombreCurso  Nombre del curso.
     */
    public AlumnoCursoDTO(String nombreAlumno, String nombreCurso) {
        this.nombreAlumno = nombreAlumno;
        this.nombreCurso = nombreCurso;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCursos() {
        return cursos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }
}
