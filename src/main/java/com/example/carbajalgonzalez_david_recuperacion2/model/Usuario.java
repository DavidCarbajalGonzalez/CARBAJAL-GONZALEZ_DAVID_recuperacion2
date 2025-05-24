package com.example.carbajalgonzalez_david_recuperacion2.model;

public class Usuario {
    public enum TipoUsuario {
        PROFESOR,
        ALUMNO
    }

    private int id; // solo aplica para alumnos
    private String nombreUsuario;
    private TipoUsuario tipo;

    public Usuario(String nombreUsuario, TipoUsuario tipo) {
        this.nombreUsuario = nombreUsuario;
        this.tipo = tipo;
    }

    public Usuario(int id, String nombreUsuario, TipoUsuario tipo) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }
}

