package com.example.carbajalgonzalez_david_recuperacion2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.CursoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.AlertaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.ValidacionUtils;

public class PantallaInicialController {

    // 1) Usuario que llega desde el login
    private Usuario usuarioLogueado;

    @FXML private ComboBox<String> comboCursos;
    @FXML private TextField nombreField;
    @FXML private TextField apellidosField;
    @FXML private TextField usuarioField;
    @FXML private TextField direccionField;
    @FXML private TextField telefonoField;

    @FXML private Button btnVerAlumnos;
    @FXML private Button btnSiguiente;  // Asegúrate de que en tu FXML exista fx:id="btnSiguiente"

    @FXML
    public void initialize() {
        // Carga lista de cursos
        comboCursos.getItems().clear();
        comboCursos.getItems().addAll(CursoDAO.obtenerCursos());

        // Autocompletar si escribe un profe
        usuarioField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (usuarioLogueado == null && !newVal.isEmpty()) {
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
     * 2) Llamar desde LoginController justo después de loader.load():
     *    PantallaInicialController ctrl = loader.getController();
     *    ctrl.setUsuario(usuarioLogueado);
     */
    public void setUsuario(Usuario u) {
        this.usuarioLogueado = u;

        if (u.getTipo() == Usuario.TipoUsuario.ALUMNO) {
            // Autocompletar campos del alumno
            usuarioField.setText(u.getUsername());
            Alumno alumno = AlumnoDAO.buscarPorUsuario(u.getUsername());
            if (alumno != null) {
                nombreField.setText(alumno.getNombre());
                apellidosField.setText(alumno.getApellidos());
                direccionField.setText(alumno.getDireccion());
                telefonoField.setText(alumno.getTelefono());
            }
            // Restringir edición
            nombreField.setEditable(false);
            apellidosField.setEditable(false);
            direccionField.setEditable(false);
            telefonoField.setEditable(false);
            usuarioField.setEditable(false);
            btnVerAlumnos.setDisable(true);
        }
        // Si es PROFESOR, no hacemos nada y deja todo habilitado
    }

    @FXML
    private void onSiguiente() {
        // Validaciones estándar
        if (comboCursos.getValue() == null) {
            AlertaUtils.mostrarError("Validación", "Debes seleccionar un curso.");
            return;
        }
        if (ValidacionUtils.estaVacio(nombreField.getText())
                || ValidacionUtils.estaVacio(apellidosField.getText())
                || ValidacionUtils.estaVacio(usuarioField.getText())) {
            AlertaUtils.mostrarError("Validación", "Todos los campos obligatorios deben estar rellenos.");
            return;
        }
        if (!ValidacionUtils.esNumero(telefonoField.getText())
                || !ValidacionUtils.esTelefonoValido(telefonoField.getText())) {
            AlertaUtils.mostrarError("Validación", "El teléfono debe ser un número de 9 dígitos.");
            return;
        }

        try {
            // Cargamos la pantalla de confirmación
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(Constantes.PANTALLA_CONFIRMACION_FXML)
            );
            Parent root = loader.load();

            // Inyectamos datos + usuario al controlador de confirmación
            PantallaConfirmacionController ctrl = loader.getController();
            ctrl.inicializarDatos(
                    comboCursos.getValue(),
                    nombreField.getText(),
                    apellidosField.getText(),
                    usuarioField.getText(),
                    direccionField.getText(),
                    telefonoField.getText(),
                    (Stage) btnSiguiente.getScene().getWindow(),  // stage actual
                    usuarioLogueado                              // usuario logueado
            );

            // Cambiamos de escena
            Stage stage = (Stage) btnSiguiente.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
            AlertaUtils.mostrarError("Error", "No se pudo abrir la pantalla de confirmación.");
        }
    }

    @FXML
    private void onVerAlumnos() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(Constantes.PANTALLA_LISTADO_FXML)
            );
            Parent root = loader.load();

            // Inyectamos el usuario para filtrar listado
            PantallaListadoController ctrl = loader.getController();
            ctrl.setUsuario(usuarioLogueado);

            Stage stage = (Stage) btnVerAlumnos.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
            AlertaUtils.mostrarError("Error", "No se pudo abrir la pantalla de listado.");
        }
    }
}
