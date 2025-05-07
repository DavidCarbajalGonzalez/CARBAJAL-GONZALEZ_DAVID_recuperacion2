package com.example.carbajalgonzalez_david_recuperacion2.model;

public class Alumno {
    private String usuario, nombre, apellidos, direccion, telefono;

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

