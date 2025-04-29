package com.example.carbajalgonzalez_david_recuperacion2;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 * Inicia la aplicación cargando la pantalla inicial.
 */
public class Main extends Application {

    /**
     * Método llamado al iniciar la aplicación JavaFX.
     *
     * @param stage Escenario principal.
     * @throws IOException En caso de error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Constantes.PANTALLA_INICIAL_FXML));
        Scene scene = new Scene(fxmlLoader.load(), Constantes.VENTANA_ANCHO, Constantes.VENTANA_ALTO);
        stage.setTitle(Constantes.TITULO_PANTALLA_INICIAL);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main para lanzar la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
