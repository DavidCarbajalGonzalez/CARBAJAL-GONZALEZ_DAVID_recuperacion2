package com.example.carbajalgonzalez_david_recuperacion2.utils;

/**
 * Clase que contiene todas las constantes reutilizadas a lo largo del proyecto.
 * Incluye rutas de archivos, nombres de tablas, tamaños de ventanas y mensajes comunes.
 */
public class Constantes {

    // Rutas de FXML

    /** Ruta del archivo FXML de la pantalla inicial. */
    public static final String PANTALLA_INICIAL_FXML = "/com/example/carbajalgonzalez_david_recuperacion2/PantallaInicial.fxml";

    /** Ruta del archivo FXML de la pantalla de confirmación. */
    public static final String PANTALLA_CONFIRMACION_FXML = "/com/example/carbajalgonzalez_david_recuperacion2/PantallaConfirmacion.fxml";

    /** Ruta del archivo FXML de la pantalla de listado de alumnos. */
    public static final String PANTALLA_LISTADO_FXML = "/com/example/carbajalgonzalez_david_recuperacion2/PantallaListado.fxml";

    /** Ruta del archivo CSS de estilos. */
    public static final String ESTILO_CSS = "/com/example/carbajalgonzalez_david_recuperacion2/style/css.css";

    // Base de datos

    /** Ruta del archivo de la base de datos SQLite. */
    public static final String RUTA_BASEDATOS = "data/universidad.db";

    // Títulos de ventanas

    /** Título de la ventana de inscripción. */
    public static final String TITULO_PANTALLA_INICIAL = "Inscripción a cursos";

    /** Título de la ventana de confirmación de inscripción. */
    public static final String TITULO_PANTALLA_CONFIRMACION = "Confirmar inscripción";

    /** Título de la ventana de listado de alumnos. */
    public static final String TITULO_PANTALLA_LISTADO = "Listado de alumnos";

    // Nombres de tablas

    /** Nombre de la tabla de alumnos en la base de datos. */
    public static final String TABLA_ALUMNOS = "alumnos";

    /** Nombre de la tabla de cursos en la base de datos. */
    public static final String TABLA_CURSOS = "cursos";

    /** Nombre de la tabla de relaciones alumno-curso en la base de datos. */
    public static final String TABLA_RELACIONES = "relaciones";

    // Tamaño de las ventanas

    /** Ancho estándar de las ventanas. */
    public static final int VENTANA_ANCHO = 325;

    /** Alto estándar de las ventanas. */
    public static final int VENTANA_ALTO = 500;

    // Mensajes comunes

    /** Mensaje de error genérico al conectar con la base de datos. */
    public static final String MENSAJE_ERROR_BBDD = "Error al conectar con la base de datos.";

    /** Mensaje de error genérico al insertar datos en la base de datos. */
    public static final String MENSAJE_ERROR_INSERCION = "Error al insertar en la base de datos.";
}