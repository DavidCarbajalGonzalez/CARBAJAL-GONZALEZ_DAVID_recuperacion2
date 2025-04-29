package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador de la pantalla de listado de alumnos por curso.
 * Se encarga de mostrar en una tabla los alumnos inscritos junto a sus cursos.
 */
public class PantallaListadoController {

    @FXML private TableView<AlumnoCursoDTO> tablaAlumnos;
    @FXML private TableColumn<AlumnoCursoDTO, String> colCurso;
    @FXML private TableColumn<AlumnoCursoDTO, String> colNombre;
    @FXML private TableColumn<AlumnoCursoDTO, String> colApellidos;

    /**
     * Inicializa la tabla con los datos obtenidos desde la base de datos.
     * Asocia las propiedades de AlumnoCursoDTO a las columnas correspondientes.
     */
    @FXML
    public void initialize() {
        // Configurar las columnas para que se enlacen a las propiedades del DTO
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        // Obtener lista de alumnos por curso y cargarla en la tabla
        List<AlumnoCursoDTO> lista = AlumnoDAO.obtenerAlumnosPorCurso();
        tablaAlumnos.setItems(FXCollections.observableArrayList(lista));
    }
}
