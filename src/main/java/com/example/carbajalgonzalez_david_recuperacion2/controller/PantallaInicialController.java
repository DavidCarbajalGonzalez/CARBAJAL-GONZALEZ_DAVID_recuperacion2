package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.CursoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.AlertaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.UsuarioReceptor;
import com.example.carbajalgonzalez_david_recuperacion2.utils.ValidacionUtils;
import javafx.application.Platform;
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
 * Comportamiento adaptado según el tipo de usuario (profesor o alumno).
 */
public class PantallaInicialController implements UsuarioReceptor {

    @FXML private ComboBox<String> comboCursos;
    @FXML private TextField nombreField;
    @FXML private TextField apellidosField;
    @FXML private TextField usuarioField;
    @FXML private TextField direccionField;
    @FXML private TextField telefonoField;

    @FXML private Button btnVerAlumnos;

    private Usuario usuarioActual;

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
    }

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

        // Ajustar la pantalla según el tipo de usuario
        Platform.runLater(() -> {
            if (usuarioActual != null) {
                if (usuarioActual.getTipo() == Usuario.TipoUsuario.PROFESOR) {
                    btnVerAlumnos.setVisible(true);
                } else {
                    btnVerAlumnos.setVisible(false);
                    usuarioField.setText(usuarioActual.getNombreUsuario());
                    usuarioField.setEditable(false);

                    Alumno alumno = AlumnoDAO.obtenerAlumnoPorNombreUsuario(usuarioActual.getNombreUsuario());
                    if (alumno != null) {
                        nombreField.setText(alumno.getNombre());
                        apellidosField.setText(alumno.getApellidos());
                        direccionField.setText(alumno.getDireccion());
                        telefonoField.setText(alumno.getTelefono());

                        nombreField.setEditable(false);
                        apellidosField.setEditable(false);
                        direccionField.setEditable(false);
                        telefonoField.setEditable(false);
                    }
                }
            }
        });
    }

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

            Stage nuevaStage = new Stage();
            nuevaStage.setTitle(Constantes.TITULO_PANTALLA_CONFIRMACION);
            nuevaStage.setScene(scene);
            nuevaStage.show();

            PantallaUtils.cerrarVentana(nombreField);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onVerAlumnos() {
        // Abre PantallaListado en ventana nueva, pasando el Usuario
        PantallaUtils.abrirVentana(
                Constantes.PANTALLA_INICIAL_FXML,
                Constantes.TITULO_PANTALLA_INICIAL,
                usuarioActual   // PASAMOS EL USUARIO, NO EL BOTÓN
        );
    }

}