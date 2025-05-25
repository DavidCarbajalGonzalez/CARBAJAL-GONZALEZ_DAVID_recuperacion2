package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.model.TipoUsuario;
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
 * Permite el registro de alumnos y creación de cursos según el rol.
 */
public class PantallaInicialController {

    @FXML private ComboBox<String> comboCursos;
    @FXML private TextField nombreField;
    @FXML private TextField apellidosField;
    @FXML private TextField usuarioField;
    @FXML private TextField direccionField;
    @FXML private TextField telefonoField;

    @FXML private Button btnVerAlumnos;
    @FXML private Button btnCrearAlumno;
    @FXML private Button btnCrearCurso;
    @FXML private Button btnMatricular;

    private Usuario usuario;

    /**
     * Inicializa la pantalla inicial.
     * Carga los cursos disponibles y añade un listener para autocompletar datos de alumno.
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
     * Configura la vista según el usuario autenticado.
     */
    public void setUsuario(Usuario u) {
        this.usuario = u;

        if (u.getTipo() == TipoUsuario.ALUMNO) {
            // Poblar datos del alumno y deshabilitar creación
            Alumno a = AlumnoDAO.buscarPorUsuario(u.getUsuario());
            if (a != null) {
                nombreField.setText(a.getNombre());
                apellidosField.setText(a.getApellidos());
                direccionField.setText(a.getDireccion());
                telefonoField.setText(a.getTelefono());
                usuarioField.setText(a.getUsuario());
                if (a.getCursos() != null && !a.getCursos().isEmpty()) {
                    comboCursos.getSelectionModel().select(a.getCursos().get(0));
                }
            }
            btnCrearAlumno.setDisable(true);
            btnCrearCurso.setDisable(true);
        } else if (u.getTipo() == TipoUsuario.ADMIN) {
            // Limpia campos y habilita creación
            nombreField.clear();
            apellidosField.clear();
            direccionField.clear();
            telefonoField.clear();
            usuarioField.clear();
            comboCursos.getSelectionModel().clearSelection();

            btnCrearAlumno.setDisable(false);
            btnCrearCurso.setDisable(false);
        }
    }

    /**
     * Acción para matricular un alumno.
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

            Stage nuevaStage = new Stage();
            nuevaStage.setTitle(Constantes.TITULO_PANTALLA_CONFIRMACION);
            nuevaStage.setScene(scene);
            nuevaStage.show();

            PantallaUtils.cerrarVentana(nombreField);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Acción para visualizar la lista de alumnos.
     */
    @FXML
    private void onVerAlumnos() {
        PantallaUtils.abrirVentana(Constantes.PANTALLA_LISTADO_FXML,
                Constantes.TITULO_PANTALLA_LISTADO,
                btnVerAlumnos);
    }

    // Stubs para creación (implementar según lógica DAO)
    @FXML private void onCrearAlumno() {
        // Lógica para crear un nuevo alumno
    }

    @FXML private void onCrearCurso() {
        // Lógica para crear un nuevo curso
    }
}