package com.example.carbajalgonzalez_david_recuperacion2.model;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Devuelve todos los registros (para el profesor)
     */
    public static List<AlumnoCursoDTO> obtenerTodosLosRegistros() {
        String sql = """
            SELECT a.id    AS id_alumno,
                   a.nombre,
                   a.apellidos,
                   a.nombre_usuario,
                   c.id    AS id_curso,
                   c.nombre AS curso_nombre
              FROM relaciones r
              JOIN alumnos a ON r.id_alumno = a.id
              JOIN cursos   c ON r.id_curso  = c.id
            """;

        List<AlumnoCursoDTO> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new AlumnoCursoDTO(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("nombre_usuario"),
                        rs.getInt("id_curso"),
                        rs.getString("curso_nombre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Devuelve solo los registros del alumno dado (para el rol ALUMNO)
     */
    public static List<AlumnoCursoDTO> obtenerRegistrosPorUsuario(String username) {
        String sql = """
            SELECT a.id    AS id_alumno,
                   a.nombre,
                   a.apellidos,
                   a.nombre_usuario,
                   c.id    AS id_curso,
                   c.nombre AS curso_nombre
              FROM relaciones r
              JOIN alumnos a ON r.id_alumno = a.id
              JOIN cursos   c ON r.id_curso  = c.id
             WHERE a.nombre_usuario = ?
            """;

        List<AlumnoCursoDTO> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new AlumnoCursoDTO(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("nombre_usuario"),
                        rs.getInt("id_curso"),
                        rs.getString("curso_nombre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}