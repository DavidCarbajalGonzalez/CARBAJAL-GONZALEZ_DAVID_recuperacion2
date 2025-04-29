package com.example.carbajalgonzalez_david_recuperacion2.utils;

public class Constantes {

    // Rutas de FXML
    public static final String PANTALLA_INICIAL_FXML = "/com/example/carbajalgonzalez_david_recuperacion2/PantallaInicial.fxml";
    public static final String PANTALLA_CONFIRMACION_FXML = "/com/example/carbajalgonzalez_david_recuperacion2/PantallaConfirmacion.fxml";
    public static final String PANTALLA_LISTADO_FXML = "/com/example/carbajalgonzalez_david_recuperacion2/PantallaListado.fxml";

    // Base de datos
    public static final String RUTA_BASEDATOS = "data/universidad.db";

    // Títulos de ventanas
    public static final String TITULO_PANTALLA_INICIAL = "Inscripción a cursos";
    public static final String TITULO_PANTALLA_CONFIRMACION = "Confirmar inscripción";
    public static final String TITULO_PANTALLA_LISTADO = "Listado de alumnos por curso";

    // Nombres de tablas
    public static final String TABLA_ALUMNOS = "alumnos";
    public static final String TABLA_CURSOS = "cursos";
    public static final String TABLA_RELACIONES = "relaciones";

    // Tamaño de las ventanas
    public static final int VENTANA_ANCHO = 320;
    public static final int VENTANA_ALTO = 400;

    // Mensajes comunes
    public static final String MENSAJE_ERROR_BBDD = "Error al conectar con la base de datos.";
    public static final String MENSAJE_ERROR_INSERCION = "Error al insertar en la base de datos.";
}
