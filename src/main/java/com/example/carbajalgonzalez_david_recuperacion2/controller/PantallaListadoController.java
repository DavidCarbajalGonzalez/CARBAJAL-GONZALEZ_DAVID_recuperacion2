package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controlador para la pantalla de listado de alumnos por curso.
 */
public class PantallaListadoController {

    @FXML private TableView<AlumnoCursoDTO> tablaAlumnos;
    @FXML private TableColumn<AlumnoCursoDTO, String> colCurso;
    @FXML private TableColumn<AlumnoCursoDTO, String> colNombre;
    @FXML private TableColumn<AlumnoCursoDTO, String> colApellidos;

    /**
     * Inicializa la tabla cargando los alumnos con su curso desde la base de datos
     */
    @FXML
    public void initialize() {
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        List<AlumnoCursoDTO> lista = AlumnoDAO.obtenerAlumnosPorCurso();
        tablaAlumnos.setItems(FXCollections.observableArrayList(lista));
    }

    /**
     * Vuelve a la pantalla inicial cerrando la actual.
     * Usa PantallaUtils para gestionar la navegación.
     *
     * @param event
     */
    @FXML
    private void onVolver(javafx.event.ActionEvent event) {
        PantallaUtils.abrirVentana(Constantes.PANTALLA_INICIAL_FXML, Constantes.TITULO_PANTALLA_INICIAL, (Node) event.getSource());
    }
}

