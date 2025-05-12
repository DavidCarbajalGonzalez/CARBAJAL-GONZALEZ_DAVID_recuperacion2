package com.example.carbajalgonzalez_david_recuperacion2.model;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase DAO (Data Access Object) para gestionar las relaciones entre alumnos y cursos en la base de datos.
 */
public class RelacionDAO {

    /**
     * Registra una nueva relación entre un alumno y un curso en la base de datos.
     *
     * @param idAlumno    ID del alumno.
     * @param nombreCurso Nombre del curso.
     */
    public static void registrarRelacion(int idAlumno, String nombreCurso) {
        try {
            int idCurso = obtenerIdCurso(nombreCurso);
            if (idCurso == -1) {
                System.out.println("Curso no encontrado");
                return;
            }

            String sql = "INSERT INTO " + Constantes.TABLA_RELACIONES + " (id_alumno, id_curso) VALUES (?, ?)";
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAlumno);
            stmt.setInt(2, idCurso);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el ID de un curso a partir de su nombre.
     *
     * @param nombreCurso Nombre del curso.
     * @return ID del curso si existe, -1 en caso contrario.
     */
    public static int obtenerIdCurso(String nombreCurso) {
        String sql = "SELECT id FROM " + Constantes.TABLA_CURSOS + " WHERE nombre = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreCurso);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Comprueba si ya existe una relación entre un alumno y un curso.
     *
     * @param idAlumno ID del alumno.
     * @param idCurso  ID del curso.
     * @return true si la relación ya existe, false en caso contrario.
     */
    public static boolean relacionExiste(int idAlumno, int idCurso) {
        String sql = "SELECT 1 FROM relaciones WHERE id_alumno = ? AND id_curso = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAlumno);
            stmt.setInt(2, idCurso);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}