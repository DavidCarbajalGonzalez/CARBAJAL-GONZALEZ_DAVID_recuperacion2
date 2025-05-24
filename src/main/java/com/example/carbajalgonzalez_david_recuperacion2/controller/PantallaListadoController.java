package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.RelacionDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import com.example.carbajalgonzalez_david_recuperacion2.utils.UsuarioReceptor;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador de la pantalla de listado.
 * Muestra una tabla con los alumnos inscritos en cursos.
 * Si el usuario es profesor, muestra todos los alumnos.
 * Si es alumno, muestra solo sus propios cursos.
 */
public class PantallaListadoController implements UsuarioReceptor {

    @FXML
    private TableView<AlumnoCursoDTO> tablaListado;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colAlumno;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colCurso;

    private Usuario usuarioActual;

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        cargarDatos();
    }

    @FXML
    public void initialize() {
        colAlumno.setCellValueFactory(new PropertyValueFactory<>("nombreAlumno"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
    }

    private void cargarDatos() {
        List<AlumnoCursoDTO> datos;

        if (usuarioActual != null && usuarioActual.getTipo() == Usuario.TipoUsuario.ALUMNO) {
            datos = RelacionDAO.obtenerCursosDeAlumno(usuarioActual.getId());
        } else {
            datos = RelacionDAO.obtenerTodosAlumnosConCursos();
        }

        tablaListado.getItems().setAll(datos);
    }

    @FXML
    private void onVolver() {
        PantallaUtils.cambiarPantalla("PantallaInicial.fxml", tablaListado.getScene(), usuarioActual);
    }
}
