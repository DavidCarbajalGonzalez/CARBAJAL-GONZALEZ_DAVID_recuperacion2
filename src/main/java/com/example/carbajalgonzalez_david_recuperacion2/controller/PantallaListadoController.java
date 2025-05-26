package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.RelacionDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador de la pantalla que muestra el listado de alumnos y los cursos en los que están inscritos.
 */
public class PantallaListadoController {

    @FXML private TableView<AlumnoCursoDTO> tablaAlumnos;
    @FXML private TableColumn<AlumnoCursoDTO, String> colNombre;
    @FXML private TableColumn<AlumnoCursoDTO, String> colApellidos;
    @FXML private TableColumn<AlumnoCursoDTO, String> colUsuario;
    @FXML private TableColumn<AlumnoCursoDTO, String> colDireccion;
    @FXML private TableColumn<AlumnoCursoDTO, String> colTelefono;
    @FXML private TableColumn<AlumnoCursoDTO, String> colCursos;
    @FXML private Button btnVolver;

    private Usuario usuarioLogueado;

    /**
     * Inicializa la tabla de alumnos cargando todos los datos desde la base de datos.
     * Configura cada columna con su respectiva propiedad del objeto AlumnoCursoDTO.
     */
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direción"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("teléfono"));
        colCursos.setCellValueFactory(new PropertyValueFactory<>("cursos"));

        List<AlumnoCursoDTO> lista = AlumnoDAO.obtenerAlumnosConCursos();
        tablaAlumnos.setItems(FXCollections.observableArrayList(lista));
    }
    public void setUsuario(Usuario u) {
        this.usuarioLogueado = u;
        cargarDatos();
    }

    /** Carga los registros: todos para el profe, sólo los del alumno si es rol ALUMNO */
    private void cargarDatos() {
        if (usuarioLogueado.getTipo() == Usuario.TipoUsuario.PROFESOR) {
            tablaAlumnos.getItems().setAll(RelacionDAO.obtenerTodosLosRegistros());
        } else {
            tablaAlumnos.getItems().setAll(
                    RelacionDAO.obtenerRegistrosPorUsuario(usuarioLogueado.getUsername())
            );
        }
    }

    /** Activa o desactiva botones según rol */

    /*
    private void configurarPermisos() {
        boolean esAlumno = usuarioLogueado.getTipo() == Usuario.TipoUsuario.ALUMNO;
        btnEditar.setDisable(esAlumno);
        btnEliminar.setDisable(esAlumno);
        btnNuevo.setDisable(esAlumno);
    }
    */

    /**
     * Acción que se ejecuta al pulsar el botón "Volver".
     * Permite regresar a la pantalla inicial de inscripción.
     */
    @FXML
    private void onVolver() {
        PantallaUtils.abrirVentana(Constantes.PANTALLA_INICIAL_FXML, Constantes.TITULO_PANTALLA_INICIAL, tablaAlumnos);
    }
}