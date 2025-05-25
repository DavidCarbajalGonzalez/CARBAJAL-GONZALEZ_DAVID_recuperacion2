package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.RelacionDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
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
    private TableColumn<AlumnoCursoDTO, String> colCursos;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colNombre;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colApellidos;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colUsuario;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colDireccion;

    @FXML
    private TableColumn<AlumnoCursoDTO, String> colTelefono;

    private Usuario usuarioActual;

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        cargarDatos();
    }

    @FXML
    public void initialize() {
        colCursos.setCellValueFactory(new PropertyValueFactory<>("cursos"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
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
        PantallaUtils.cambiarPantalla(Constantes.PANTALLA_INICIAL_FXML, tablaListado.getScene(), usuarioActual);
    }
}
