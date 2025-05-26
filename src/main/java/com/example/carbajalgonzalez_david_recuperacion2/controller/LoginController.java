package com.example.carbajalgonzalez_david_recuperacion2.controller;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario.TipoUsuario;
import com.example.carbajalgonzalez_david_recuperacion2.model.UsuarioDAO;
import com.example.carbajalgonzalez_david_recuperacion2.utils.AlertaUtils;

public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private ComboBox<TipoUsuario> cmbRol;

    @FXML
    public void initialize() {
        cmbRol.getItems().setAll(TipoUsuario.values());
        cmbRol.getSelectionModel().selectFirst();
    }


    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String clave = pwdPassword.getText();
        TipoUsuario rol = cmbRol.getValue();

        Usuario u = UsuarioDAO.autenticar(usuario, clave, rol);
        if (u != null) {
            try {
                FXMLLoader loader = null;
                if (u.getTipo() == TipoUsuario.ALUMNO) {
                    // *** ALUMNO ***
                    loader = new FXMLLoader(
                            getClass().getResource(Constantes.PANTALLA_INICIAL_FXML)
                    );
                    Parent root = loader.load();

                    PantallaInicialController ctrl =
                            loader.getController();
                    ctrl.setUsuario(u);

                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } else {
                    // *** PROFESOR ***
                    FXMLLoader loader1 = new FXMLLoader(
                            getClass().getResource(Constantes.PANTALLA_INICIAL_FXML)
                    );
                    Parent root = loader1.load();

                    // ** Aquí sí: use PantallaListadoController **
                    PantallaInicialController ctrl =
                            loader1.getController();
                    ctrl.setUsuario(u);

                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
            } catch (Exception e) {
                e.printStackTrace();
                AlertaUtils.mostrarError("Error", "No se pudo cargar la siguiente pantalla");
            }
        } else {
            AlertaUtils.mostrarError("Login fallido", "Usuario, contraseña o rol incorrecto");
        }
    }
}
