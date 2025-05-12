package com.example.carbajalgonzalez_david_recuperacion2.utils;

/**
 * Clase utilitaria que proporciona métodos para validar datos de entrada de texto.
 */
public class ValidacionUtils {

    /**
     * Comprueba si un texto está vacío o es {@code null}.
     *
     * @param texto Texto a comprobar.
     * @return {@code true} si el texto es {@code null} o está vacío tras eliminar espacios; {@code false} en caso contrario.
     */
    public static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    /**
     * Comprueba si un texto es un número entero válido.
     *
     * @param texto Texto a comprobar.
     * @return {@code true} si el texto representa un número entero; {@code false} en caso contrario.
     */
    public static boolean esNumero(String texto) {
        if (estaVacio(texto)) return false;
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Comprueba si un número de teléfono es válido.
     * Un número válido debe contener exactamente 9 dígitos.
     *
     * @param telefono Número de teléfono a validar.
     * @return {@code true} si el teléfono tiene exactamente 9 dígitos; {@code false} en caso contrario.
     */
    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }
}