package com.example.carbajalgonzalez_david_recuperacion2.model;

public class Usuario {
    private final String usuario;
    private final String password;
    private final TipoUsuario tipo;

    public Usuario(String usuario, String password, TipoUsuario tipo) {
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }
    public TipoUsuario getTipo() { return tipo; }
}
