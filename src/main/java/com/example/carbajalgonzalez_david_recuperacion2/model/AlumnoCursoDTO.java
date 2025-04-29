package com.example.carbajalgonzalez_david_recuperacion2.model;

/**
 * Clase de transferencia de datos (DTO) para representar un alumno inscrito en un curso.
 * Contiene el nombre del curso, nombre del alumno y sus apellidos.
 */
public class AlumnoCursoDTO {

    private String curso;
    private String nombre;
    private String apellidos;

    /**
     * Constructor que inicializa un objeto AlumnoCursoDTO con todos los datos necesarios.
     *
     * @param curso     Nombre del curso.
     * @param nombre    Nombre del alumno.
     * @param apellidos Apellidos del alumno.
     */
    public AlumnoCursoDTO(String curso, String nombre, String apellidos) {
        this.curso = curso;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el nombre del curso en el que está inscrito el alumno.
     *
     * @return Nombre del curso.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Obtiene el nombre del alumno.
     *
     * @return Nombre del alumno.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del alumno.
     *
     * @return Apellidos del alumno.
     */
    public String getApellidos() {
        return apellidos;
    }
}
