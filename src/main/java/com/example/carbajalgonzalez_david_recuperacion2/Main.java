package com.example.carbajalgonzalez_david_recuperacion2;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 * Se encarga de iniciar la interfaz gráfica cargando la pantalla inicial.
 */
public class Main extends Application {

    /**
     * Método llamado automáticamente al iniciar la aplicación JavaFX.
     * Carga la pantalla inicial definida en el archivo FXML correspondiente.
     *
     * @param stage Escenario principal donde se muestra la ventana.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Constantes.PANTALLA_INICIAL_FXML));
        Scene scene = new Scene(fxmlLoader.load(), Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
        scene.getStylesheets().add(Main.class.getResource(Constantes.ESTILO_CSS).toExternalForm());
        stage.setTitle(Constantes.TITULO_PANTALLA_INICIAL);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal que lanza la aplicación JavaFX.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch();
    }
}
