package com.example.carbajalgonzalez_david_recuperacion2.model;

/**
 * Clase que representa un alumno del sistema.
 * Contiene la información básica del alumno como usuario, nombre, apellidos, dirección y teléfono.
 */
public class Alumno {
    private String usuario;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;

    /**
     * Constructor de la clase Alumno.
     *
     * @param usuario   Nombre de usuario del alumno.
     * @param nombre    Nombre del alumno.
     * @param apellidos Apellidos del alumno.
     * @param direccion Dirección del alumno.
     * @param telefono  Teléfono de contacto del alumno.
     */
    public Alumno(String usuario, String nombre, String apellidos, String direccion, String telefono) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getUsuario() { return usuario; }

    public String getNombre() { return nombre; }

    public String getApellidos() { return apellidos; }

    public String getDireccion() { return direccion; }

    public String getTelefono() { return telefono; }
}