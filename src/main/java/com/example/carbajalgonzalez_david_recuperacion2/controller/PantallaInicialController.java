package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.CursoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.utils.AlertaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.ValidacionUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla inicial.
 * Permite al usuario introducir sus datos y seleccionar un curso para inscribirse.
 */
public class PantallaInicialController {

    @FXML private ComboBox<String> comboCursos;
    @FXML private TextField nombreField;
    @FXML private TextField apellidosField;
    @FXML private TextField usuarioField;
    @FXML private TextField direccionField;
    @FXML private TextField telefonoField;

    @FXML private Button btnVerAlumnos;

    /**
     * Inicializa la pantalla inicial cargando los cursos disponibles en el ComboBox.
     */
    @FXML
    public void initialize() {
        comboCursos.getItems().clear();
        comboCursos.getItems().addAll(CursoDAO.obtenerCursos());
    }

    /**
     * Acción del botón "Siguiente".
     * Valida los datos introducidos y pasa a la pantalla de confirmación si son correctos.
     */
    @FXML
    private void onSiguiente() {
        if (comboCursos.getValue() == null) {
            AlertaUtils.mostrarError("Validación", "Debes seleccionar un curso.");
            return;
        }
        if (ValidacionUtils.estaVacio(nombreField.getText()) ||
                ValidacionUtils.estaVacio(apellidosField.getText()) ||
                ValidacionUtils.estaVacio(usuarioField.getText())) {
            AlertaUtils.mostrarError("Validación", "Todos los campos obligatorios deben estar rellenos.");
            return;
        }
        if (!ValidacionUtils.esNumero(telefonoField.getText()) || !ValidacionUtils.esTelefonoValido(telefonoField.getText())) {
            AlertaUtils.mostrarError("Validación", "El teléfono debe ser un número de 9 dígitos.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.PANTALLA_CONFIRMACION_FXML));
            Scene scene = new Scene(loader.load());

            PantallaConfirmacionController controller = loader.getController();
            controller.inicializarDatos(
                    comboCursos.getValue(),
                    nombreField.getText(),
                    apellidosField.getText(),
                    usuarioField.getText(),
                    direccionField.getText(),
                    telefonoField.getText(),
                    (Stage) nombreField.getScene().getWindow()
            );

            Stage stage = new Stage();
            stage.setTitle(Constantes.TITULO_PANTALLA_CONFIRMACION);
            stage.setScene(scene);
            stage.show();

            nombreField.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Acción del botón "Ver alumnos".
     * Abre directamente la pantalla de listado de alumnos inscritos en los cursos.
     */
    @FXML
    private void onVerAlumnos() {
        PantallaUtils.abrirVentana(Constantes.PANTALLA_LISTADO_FXML, Constantes.TITULO_PANTALLA_LISTADO, btnVerAlumnos);
    }
}

