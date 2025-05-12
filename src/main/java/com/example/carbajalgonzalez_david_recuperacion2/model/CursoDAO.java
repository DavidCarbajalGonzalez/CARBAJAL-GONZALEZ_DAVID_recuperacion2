package com.example.carbajalgonzalez_david_recuperacion2.model;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar operaciones de cursos en la base de datos.
 */
public class CursoDAO {

    /**
     * Obtiene la lista de nombres de todos los cursos disponibles en la base de datos.
     *
     * @return Lista de nombres de cursos.
     */
    public static List<String> obtenerCursos() {
        List<String> cursos = new ArrayList<>();
        String sql = "SELECT nombre FROM " + Constantes.TABLA_CURSOS;

        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cursos.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener los cursos");
            e.printStackTrace();
        }

        return cursos;
    }
}