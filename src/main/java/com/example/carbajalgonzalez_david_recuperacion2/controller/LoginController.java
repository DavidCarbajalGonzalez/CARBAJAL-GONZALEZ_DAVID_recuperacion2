package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Alumno;
import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import com.example.carbajalgonzalez_david_recuperacion2.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes.PANTALLA_INICIAL_FXML;

public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private Label labelError;

    @FXML
    private void entrarComoProfesor() {
        String usuario = campoUsuario.getText().trim();
        String contrasena = campoContrasena.getText().trim();

        if (usuario.equals("admin") && contrasena.equals("1234")) {
            Usuario profesor = new Usuario("admin", Usuario.TipoUsuario.PROFESOR);
            PantallaUtils.cambiarPantalla(PANTALLA_INICIAL_FXML, campoUsuario.getScene(), profesor);
        } else {
            labelError.setText("Credenciales incorrectas para el profesor.");
        }
    }

    @FXML
    private void entrarComoAlumno() {
        String usuario = campoUsuario.getText().trim();

        if (usuario.isEmpty()) {
            labelError.setText("Introduce tu nombre de usuario.");
            return;
        }

        Alumno alumno = AlumnoDAO.obtenerAlumnoPorNombreUsuario(usuario);
        if (alumno != null) {
            Usuario usuarioAlumno = new Usuario(alumno.getUsuario(), Usuario.TipoUsuario.ALUMNO);
            PantallaUtils.cambiarPantalla(PANTALLA_INICIAL_FXML, campoUsuario.getScene(), usuarioAlumno);
        } else {
            labelError.setText("Alumno no encontrado.");
        }
    }
}
