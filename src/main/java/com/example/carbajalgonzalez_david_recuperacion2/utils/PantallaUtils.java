package com.example.carbajalgonzalez_david_recuperacion2.utils;

import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Clase utilitaria para gestionar la navegación entre ventanas (escenarios) en la aplicación JavaFX.
 */
public class PantallaUtils {

    /**
     * Abre una nueva ventana especificada por el archivo FXML y pasa un Usuario al controlador destino.
     *
     * @param rutaFXML Ruta del FXML (solo el nombre, p. ej. "PantallaListado.fxml").
     * @param titulo   Título de la ventana.
     * @param usuario  Usuario a inyectar en el nuevo controlador si implementa UsuarioReceptor.
     */
    public static void abrirVentana(String rutaFXML, String titulo, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaUtils.class.getResource(
                    "/com/example/carbajalgonzalez_david_recuperacion2/" + rutaFXML
            ));
            Parent root = loader.load();

            Object ctrl = loader.getController();
            if (ctrl instanceof UsuarioReceptor receptor) {
                receptor.setUsuario(usuario);
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al abrir la ventana: " + rutaFXML);
            e.printStackTrace();
        }
    }

    /**
     * Cambia la ventana actual por una nueva, pasando el Usuario al controlador destino.
     *
     * @param rutaFXML      Nombre del FXML a cargar (p. ej. "PantallaListado.fxml").
     * @param escenaActual  Scene de la ventana que se va a reemplazar.
     * @param usuario       Usuario a inyectar en el nuevo controlador si implementa UsuarioReceptor.
     */
    public static void cambiarPantalla(String rutaFXML, Scene escenaActual, Usuario usuario) {
        try {
            String path = "/com/example/carbajalgonzalez_david_recuperacion2/" + rutaFXML;
            URL res = PantallaUtils.class.getResource(path);
            System.out.println("Intento cargar FXML desde: " + path + " → " + res);
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof UsuarioReceptor receptor) {
                receptor.setUsuario(usuario);
            }

            Stage stage = (Stage) escenaActual.getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al cambiar a la pantalla: " + rutaFXML);
            e.printStackTrace();
        }
    }



    /**
     * Cierra la ventana actual asociada a un nodo de la escena.
     *
     * @param node Nodo (Label, Button, TextField, etc.) perteneciente a la ventana que se desea cerrar.
     */
    public static void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
