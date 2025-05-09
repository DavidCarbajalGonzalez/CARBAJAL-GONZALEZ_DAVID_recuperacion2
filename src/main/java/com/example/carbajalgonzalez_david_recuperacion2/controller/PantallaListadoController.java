package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
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
 * Controlador de la pantalla que lista todos los alumnos y sus cursos.
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

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCursos.setCellValueFactory(new PropertyValueFactory<>("cursos"));

        List<AlumnoCursoDTO> lista = AlumnoDAO.obtenerAlumnosConCursos();
        tablaAlumnos.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void onVolver() {
        PantallaUtils.abrirVentana(Constantes.PANTALLA_INICIAL_FXML, Constantes.TITULO_PANTALLA_INICIAL, tablaAlumnos);
    }
}


