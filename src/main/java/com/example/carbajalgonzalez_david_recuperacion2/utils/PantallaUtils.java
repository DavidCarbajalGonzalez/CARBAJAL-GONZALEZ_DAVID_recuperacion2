package com.example.carbajalgonzalez_david_recuperacion2.utils;

import com.example.carbajalgonzalez_david_recuperacion2.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase utilitaria para gestionar la navegación entre ventanas (escenarios) en la aplicación JavaFX.
 */
public class PantallaUtils {

    /**
     * Abre una nueva ventana y opcionalmente cierra la ventana actual.
     *
     * @param rutaFXML Ruta del archivo FXML de la nueva ventana.
     * @param titulo   Título de la nueva ventana.
     * @param nodoActual Nodo de la ventana actual (si quieres cerrarla). Puede ser null si no quieres cerrar nada.
     */
    public static void abrirVentana(String rutaFXML, String titulo, Node nodoActual) {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaUtils.class.getResource(rutaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);

            // Aplicar el estilo CSS desde Constantes
            scene.getStylesheets().add(Main.class.getResource(Constantes.ESTILO_CSS).toExternalForm());

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

            // Si se pasa un nodo, cerrar la ventana actual
            if (nodoActual != null) {
                Stage ventanaActual = (Stage) nodoActual.getScene().getWindow();
                ventanaActual.close();
            }

        } catch (Exception e) {
            System.out.println("Error al abrir la ventana: " + rutaFXML);
            e.printStackTrace();
        }
    }

    /**
     * Cierra la ventana actual a partir de un nodo.
     *
     * @param node Un nodo cualquiera de la ventana (Label, TextField, etc.).
     */
    public static void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
