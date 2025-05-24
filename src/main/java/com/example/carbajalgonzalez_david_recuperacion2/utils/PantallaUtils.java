package com.example.carbajalgonzalez_david_recuperacion2.utils;

import com.example.carbajalgonzalez_david_recuperacion2.Main;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
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
     * Abre una nueva ventana especificada por el archivo FXML y opcionalmente cierra la ventana actual.
     *
     * @param rutaFXML   Ruta relativa al archivo FXML de la nueva ventana.
     * @param titulo     Título de la nueva ventana.
     * @param nodoActual Nodo de la ventana actual (si quieres cerrarla). Puede ser {@code null} si no se desea cerrar ninguna.
     */
    public static void abrirVentana(String rutaFXML, String titulo, Node nodoActual) {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaUtils.class.getResource(rutaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root, Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);

            // Aplicar el estilo CSS desde la ruta especificada en Constantes
            scene.getStylesheets().add(Main.class.getResource(Constantes.ESTILO_CSS).toExternalForm());

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

            // Si se pasa un nodo actual, cerrar su ventana
            if (nodoActual != null) {
                Stage ventanaActual = (Stage) nodoActual.getScene().getWindow();
                ventanaActual.close();
            }

        } catch (Exception e) {
            System.out.println("Error al abrir la ventana: " + rutaFXML);
            e.printStackTrace();
        }
    }
    public static void cambiarPantalla(String fxml, Scene escenaActual, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaUtils.class.getResource(
                    "/com/example/carbajalgonzalez_david_recuperacion2/PantallaInicial.fxml"
            ));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof com.example.carbajalgonzalez_david_recuperacion2.utils.UsuarioReceptor) {
                ((com.example.carbajalgonzalez_david_recuperacion2.utils.UsuarioReceptor) controller).setUsuario(usuario);
            }

            Stage stage = (Stage) escenaActual.getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cierra la ventana actual asociada a un nodo específico.
     *
     * @param node Nodo perteneciente a la ventana que se desea cerrar (por ejemplo, un {@link javafx.scene.control.Label} o {@link javafx.scene.control.TextField}).
     */
    public static void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}