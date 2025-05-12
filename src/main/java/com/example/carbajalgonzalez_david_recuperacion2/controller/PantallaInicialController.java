package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
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
 * Permite al usuario introducir sus datos personales y seleccionar un curso para la inscripción.
 * También ofrece la opción de visualizar los alumnos ya registrados.
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
     * Inicializa la pantalla inicial.
     * Carga los cursos disponibles en el ComboBox y añade un listener al campo de usuario para autocompletar datos si el usuario ya existe.
     */
    @FXML
    public void initialize() {
        comboCursos.getItems().clear();
        comboCursos.getItems().addAll(CursoDAO.obtenerCursos());

        usuarioField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                Alumno alumno = AlumnoDAO.buscarPorUsuario(newVal);
                if (alumno != null) {
                    nombreField.setText(alumno.getNombre());
                    apellidosField.setText(alumno.getApellidos());
                    direccionField.setText(alumno.getDireccion());
                    telefonoField.setText(alumno.getTelefono());
                }
            }
        });
    }

    /**
     * Acción que se ejecuta al pulsar el botón "Siguiente".
     * Valida los datos introducidos y, si son correctos, abre la pantalla de confirmación para revisar la inscripción.
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
     * Acción que se ejecuta al pulsar el botón "Ver alumnos".
     * Abre la pantalla de listado de alumnos inscritos en los cursos.
     */
    @FXML
    private void onVerAlumnos() {
        PantallaUtils.abrirVentana(Constantes.PANTALLA_LISTADO_FXML, Constantes.TITULO_PANTALLA_LISTADO, btnVerAlumnos);
    }
}