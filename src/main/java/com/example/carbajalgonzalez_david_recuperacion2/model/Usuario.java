package com.example.carbajalgonzalez_david_recuperacion2.model;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private TipoUsuario tipo;

    public enum TipoUsuario {
        ALUMNO, PROFESOR
    }

    public Usuario(int id, String username, String password, TipoUsuario tipo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    // getters y setters...
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public TipoUsuario getTipo() { return tipo; }
}
