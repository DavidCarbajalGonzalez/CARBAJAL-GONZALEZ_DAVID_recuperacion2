package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.RelacionDAO;
import com.example.carbajalgonzalez_david_recuperacion2.utils.AlertaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla de confirmación.
 * Permite revisar los datos ingresados por el usuario antes de confirmar la inscripción.
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

    // Datos del alumno a confirmar
    private String curso;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String direccion;
    private String telefono;

    /**
     * Inicializa los datos que se mostrarán en la pantalla de confirmación.
     *
     * @param curso         Nombre del curso seleccionado.
     * @param nombre        Nombre del alumno.
     * @param apellidos     Apellidos del alumno.
     * @param usuario       Nombre de usuario del alumno.
     * @param direccion     Dirección del alumno.
     * @param telefono      Teléfono del alumno.
     * @param stageAnterior Ventana anterior para poder volver si se edita.
     */
    public void inicializarDatos(String curso, String nombre, String apellidos, String usuario, String direccion, String telefono, Stage stageAnterior) {
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
     * Acción del botón "Editar".
     * Permite volver a la pantalla anterior para modificar los datos.
     */
    @FXML
    private void onEditar() {
        if (stageAnterior != null) {
            stageAnterior.show();
            PantallaUtils.cerrarVentana(cursoLabel);
        }
    }

    /**
     * Acción del botón "Confirmar".
     * Inserta el alumno en la base de datos y muestra la pantalla de listado de alumnos.
     */
    @FXML
    private void onConfirmar() {
        // Comprobar si el alumno ya existe
        Alumno alumnoExistente = AlumnoDAO.buscarPorNombre(usuario);
        int idAlumno;

        if (alumnoExistente != null) {
            // Si ya existe, recuperamos su ID
            idAlumno = AlumnoDAO.obtenerIdAlumno(usuario);

            // Obtenemos el ID del curso
            int idCurso = RelacionDAO.obtenerIdCurso(curso);

            // Comprobamos si ya está inscrito en ese curso
            if (RelacionDAO.relacionExiste(idAlumno, idCurso)) {
                AlertaUtils.mostrarError("Duplicado", "Este alumno ya está inscrito en el curso seleccionado.");
                return;
            }

            // Si no está inscrito, registramos la nueva relación
            RelacionDAO.registrarRelacion(idAlumno, curso);

        } else {
            // Si no existe, lo insertamos
            idAlumno = AlumnoDAO.insertarAlumno(usuario, nombre, apellidos, direccion, telefono);

            if (idAlumno == -1) {
                AlertaUtils.mostrarError("Error", "No se pudo registrar el alumno.");
                return;
            }

            // Y luego registramos la relación
            RelacionDAO.registrarRelacion(idAlumno, curso);
        }

        AlertaUtils.mostrarInfo("Éxito", "Alumno inscrito correctamente en el curso.");
        PantallaUtils.abrirVentana(Constantes.PANTALLA_LISTADO_FXML, Constantes.TITULO_PANTALLA_LISTADO, cursoLabel);
    }


}