package com.example.carbajalgonzalez_david_recuperacion2.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase utilitaria para mostrar alertas gráficas en la aplicación.
 */
public class AlertaUtils {

    /**
     * Muestra una alerta de tipo error.
     *
     * @param titulo   Título de la ventana de alerta.
     * @param mensaje  Mensaje de error que se desea mostrar.
     */
    public static void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra una alerta de tipo información.
     *
     * @param titulo   Título de la ventana de alerta.
     * @param mensaje  Mensaje informativo que se desea mostrar.
     */
    public static void mostrarInfo(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}