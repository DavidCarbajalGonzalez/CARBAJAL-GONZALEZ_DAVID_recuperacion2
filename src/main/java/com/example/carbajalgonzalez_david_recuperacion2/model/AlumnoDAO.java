package com.example.carbajalgonzalez_david_recuperacion2.model;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar operaciones de alumnos en la base de datos.
 */
public class AlumnoDAO {

    /**
     * Inserta un nuevo alumno en la base de datos.
     *
     * @param usuario   Nombre de usuario.
     * @param nombre    Nombre del alumno.
     * @param apellidos Apellidos del alumno.
     * @param direccion Dirección del alumno.
     * @param telefono  Teléfono del alumno.
     * @return ID del alumno insertado, o -1 si ocurrió un error.
     */
    public static int insertarAlumno(String usuario, String nombre, String apellidos, String direccion, String telefono) {
        // Comprobamos si ya existe un alumno con ese usuario
        Alumno existente = buscarPorNombre(usuario);
        if (existente != null) {
            // Ya existe → devolvemos su ID directamente
            return obtenerIdAlumno(usuario);
        }

        // Si no existe, lo insertamos
        String sql = "INSERT INTO " + Constantes.TABLA_ALUMNOS + " (nombre_usuario, nombre, apellidos, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, nombre);
            stmt.setString(3, apellidos);
            stmt.setString(4, direccion);
            stmt.setString(5, telefono);
            stmt.executeUpdate();

            return obtenerIdAlumno(usuario);

        } catch (SQLException e) {
            System.out.println(Constantes.MENSAJE_ERROR_INSERCION);
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * Obtiene el ID de un alumno a partir de su nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario del alumno.
     * @return ID del alumno, o -1 si no se encontró.
     */
    public static int obtenerIdAlumno(String nombreUsuario) {
        String sql = "SELECT id FROM " + Constantes.TABLA_ALUMNOS + " WHERE nombre_usuario = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Obtiene una lista de alumnos junto con los cursos en los que están inscritos.
     *
     * @return Lista de objetos AlumnoCursoDTO.
     */
    public static List<AlumnoCursoDTO> obtenerAlumnosPorCurso() {
        List<AlumnoCursoDTO> lista = new ArrayList<>();

        String sql = """
        SELECT a.nombre, a.apellidos, GROUP_CONCAT(c.nombre, ', ') AS cursos
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
                        rs.getString("cursos"),
                        rs.getString("nombre"),
                        rs.getString("apellidos")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Clase que busca el alumno por el nombre
     *
     * @param nombre
     * @return
     */
    public static Alumno buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM alumnos WHERE nombre = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Actualiza los datos de un alumno existente.
     */
    public static boolean actualizarDatosAlumno(String usuario, String nombre, String apellidos, String direccion, String telefono) {
        String sql = "UPDATE " + Constantes.TABLA_ALUMNOS +
                " SET nombre = ?, apellidos = ?, direccion = ?, telefono = ? WHERE nombre_usuario = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setString(5, usuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos del alumno:");
            e.printStackTrace();
            return false;
        }
    }
}