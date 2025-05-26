package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.RelacionDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.AlertaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla de confirmación de inscripción.
 * Permite al usuario confirmar o editar los datos antes de registrar un alumno en la base de datos.
 */
public class PantallaConfirmacionController {

    @FXML
    private Label cursoLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label usuarioLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label telefonoLabel;

    @FXML
    private Button btnConfirmar;

    private Stage stageAnterior;

    // Datos temporales del alumno
    private String curso;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String direccion;
    private String telefono;

    /**
     * Inicializa los datos del alumno que se mostrarán en la pantalla de confirmación.
     *
     * @param curso           Nombre del curso seleccionado.
     * @param nombre          Nombre del alumno.
     * @param apellidos       Apellidos del alumno.
     * @param usuario         Usuario (login) del alumno.
     * @param direccion       Dirección de residencia del alumno.
     * @param telefono        Teléfono de contacto del alumno.
     * @param stageAnterior   Ventana anterior desde donde se llegó a la confirmación.
     * @param usuarioLogueado
     */
    public void inicializarDatos(String curso, String nombre, String apellidos, String usuario, String direccion, String telefono, Stage stageAnterior, Usuario usuarioLogueado) {
        this.curso = curso;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.direccion = direccion;
        this.telefono = telefono;
        this.stageAnterior = stageAnterior;

        cursoLabel.setText("Curso: " + curso);
        nombreLabel.setText("Nombre: " + nombre);
        apellidosLabel.setText("Apellidos: " + apellidos);
        usuarioLabel.setText("Usuario: " + usuario);
        direccionLabel.setText("Dirección: " + direccion);
        telefonoLabel.setText("Teléfono: " + telefono);
    }

    /**
     * Método ejecutado al pulsar el botón "Editar".
     * Reabre la ventana anterior para modificar los datos ingresados.
     */
    @FXML
    private void onEditar() {
        if (stageAnterior != null) {
            stageAnterior.show();
            PantallaUtils.cerrarVentana(cursoLabel);
        }
    }

    /**
     * Método ejecutado al pulsar el botón "Confirmar".
     * Inserta el alumno y su inscripción al curso en la base de datos, o si ya existe, asocia el nuevo curso.
     * Muestra alertas en caso de errores o duplicados.
     */
    @FXML
    private void onConfirmar() {
        Alumno alumnoExistente = AlumnoDAO.buscarPorNombre(usuario);
        int idAlumno;

        if (alumnoExistente != null) {
            idAlumno = AlumnoDAO.obtenerIdAlumno(usuario);

            int idCurso = RelacionDAO.obtenerIdCurso(curso);

            if (RelacionDAO.relacionExiste(idAlumno, idCurso)) {
                AlertaUtils.mostrarError("Duplicado", "Este alumno ya está inscrito en el curso seleccionado.");
                return;
            }

            RelacionDAO.registrarRelacion(idAlumno, curso);

        } else {
            idAlumno = AlumnoDAO.insertarAlumno(usuario, nombre, apellidos, direccion, telefono);

            if (idAlumno == -1) {
                AlertaUtils.mostrarError("Error", "No se pudo registrar el alumno.");
                return;
            }

            RelacionDAO.registrarRelacion(idAlumno, curso);
        }

        AlertaUtils.mostrarInfo("Éxito", "Alumno inscrito correctamente en el curso.");
        PantallaUtils.abrirVentana(Constantes.PANTALLA_LISTADO_FXML, Constantes.TITULO_PANTALLA_LISTADO, cursoLabel);
    }
}