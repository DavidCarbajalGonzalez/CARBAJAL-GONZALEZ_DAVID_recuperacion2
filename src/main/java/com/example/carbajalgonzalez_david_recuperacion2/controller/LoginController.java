// src/main/java/com/example/carbajalgonzalez_david_recuperacion2/controller/LoginController.java
package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.model.UsuarioDAO;
import com.example.carbajalgonzalez_david_recuperacion2.model.TipoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField tfUsuario;
    @FXML private PasswordField pfPassword;
    @FXML private Label lblError;

    @FXML
    private void onEntrar() {
        String usr = tfUsuario.getText().trim();
        String pwd = pfPassword.getText().trim();

        Usuario u = UsuarioDAO.autenticar(usr, pwd);
        if (u == null) {
            lblError.setText("Credenciales inválidas");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/carbajalgonzalez_david_recuperacion2/PantallaInicial.fxml")
            );
            Scene sc = new Scene(loader.load());
            PantallaInicialController ctrl = loader.getController();
            ctrl.setUsuario(u);

            Stage stage = (Stage) tfUsuario.getScene().getWindow();
            stage.setScene(sc);
            stage.setTitle(u.getTipo() == TipoUsuario.ADMIN ? "Panel Profesor" : "Panel Alumno");
        } catch (Exception e) {
            e.printStackTrace();
            lblError.setText("Error cargando la siguiente pantalla");
        }
    }
}
