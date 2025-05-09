package com.example.carbajalgonzalez_david_recuperacion2.model;

import com.example.carbajalgonzalez_david_recuperacion2.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos SQLite.
 * Utiliza el patrón Singleton para garantizar una única conexión activa.
 */
public class Conexion {

    private static final String URL = "jdbc:sqlite:" + Constantes.RUTA_BASEDATOS;
    private static Connection conexion;

    /**
     * Obtiene la conexión activa a la base de datos.
     * Si no existe una conexión previa, se establece una nueva.
     *
     * @return Objeto Connection para interactuar con la base de datos.
     */
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL);
                System.out.println("Conexión a la base de datos establecida.");
            }
        } catch (SQLException e) {
            System.out.println(Constantes.MENSAJE_ERROR_BBDD);
            e.printStackTrace();
        }
        return conexion;
    }
}
