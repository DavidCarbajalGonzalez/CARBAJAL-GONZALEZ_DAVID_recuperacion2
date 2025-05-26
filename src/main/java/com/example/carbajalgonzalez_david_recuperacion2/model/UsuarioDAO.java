package com.example.carbajalgonzalez_david_recuperacion2.model;

import java.sql.*;
import com.example.carbajalgonzalez_david_recuperacion2.model.Usuario.TipoUsuario;

public class UsuarioDAO {

    public static Usuario autenticar(String username, String password, TipoUsuario tipo) {
        String sql = "SELECT * FROM Usuario WHERE username=? AND password=? AND tipo=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, tipo.name());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        TipoUsuario.valueOf(rs.getString("tipo"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // podrías usar AlertaUtils para avisar de errores de BD
        }
        return null;
    }
}
