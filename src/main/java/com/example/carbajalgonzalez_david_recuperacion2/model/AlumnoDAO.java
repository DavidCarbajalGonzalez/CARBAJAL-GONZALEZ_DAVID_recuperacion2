package com.example.carbajalgonzalez_david_recuperacion2.model;

import com.example.carbajalgonzalez_david_recuperacion2.model.AlumnoCursoDTO;
import com.example.carbajalgonzalez_david_recuperacion2.model.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlumnoDAO {
    /**
     * Obtiene una lista de alumnos con todos sus datos y los cursos en los que están inscritos.
     *
     * @return Lista de objetos AlumnoCursoDTO con datos del alumno y sus cursos concatenados.
     */
    public static List<AlumnoCursoDTO> obtenerAlumnosConCursos() {
        List<AlumnoCursoDTO> lista = new ArrayList<>();

        String sql = """
                    SELECT a.nombre, a.apellidos, a.nombre_usuario, a.direccion, a.telefono,
                           GROUP_CONCAT(c.nombre, ', ') AS cursos
                    FROM alumnos a
                    JOIN relaciones r ON a.id = r.id_alumno
                    JOIN cursos c ON c.id = r.id_curso
                    GROUP BY a.id
                    ORDER BY a.apellidos;
                """;

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new AlumnoCursoDTO(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("nombre_usuario"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("cursos")
                ));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener los alumnos con sus cursos.");
            e.printStackTrace();
        }

        return lista;
    }

    public static int obtenerIdAlumno(String nombreUsuario) {
        String sql = "SELECT id FROM alumnos WHERE nombre_usuario = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener ID del alumno:");
            e.printStackTrace();
        }

        return -1; // ID no encontrado
    }


    public static int insertarAlumno(String usuario, String nombre, String apellidos, String direccion, String telefono) {
        try {
            Connection conn = Conexion.getConexion();

            // Primero comprobamos si ya existe un alumno con ese usuario
            String sqlCheck = "SELECT id FROM alumnos WHERE nombre_usuario = ?";
            PreparedStatement checkStmt = conn.prepareStatement(sqlCheck);
            checkStmt.setString(1, usuario);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Ya existe, devolvemos su ID directamente (no insertamos nada)
                return rs.getInt("id");
            }

            // No existe, lo insertamos
            String sqlInsert = "INSERT INTO alumnos (nombre_usuario, nombre, apellidos, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
            insertStmt.setString(1, usuario);
            insertStmt.setString(2, nombre);
            insertStmt.setString(3, apellidos);
            insertStmt.setString(4, direccion);
            insertStmt.setString(5, telefono);
            insertStmt.executeUpdate();

            // Obtenemos y devolvemos el ID recién insertado
            return obtenerIdAlumno(usuario);

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar alumno:");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Busca un alumno por su nombre.
     *
     * @param nombre Nombre del alumno.
     * @return Objeto Alumno si se encuentra, null si no existe.
     */
    public static Alumno buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM alumnos WHERE nombre = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Alumno(
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar alumno por nombre:");
            e.printStackTrace();
        }
        return null;
    }

    public static Alumno buscarPorUsuario(String usuario) {
        String sql = "SELECT * FROM alumnos WHERE nombre_usuario = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Alumno(
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar alumno por usuario:");
            e.printStackTrace();
        }
        return null; // No se encontró el alumno
    }
}