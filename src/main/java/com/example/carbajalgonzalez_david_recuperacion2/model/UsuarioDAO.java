package com.example.carbajalgonzalez_david_recuperacion2.model;

public class UsuarioDAO {

    /**
     * Autentica un usuario.
     * - Si usuario="admin" y password="1234" → ADMIN.
     * - Si existe un Alumno con ese usuario (AlumnoDAO.buscarPorUsuario) → ALUMNO.
     * @return Usuario si credenciales válidas, o null si no.
     */
    public static Usuario autenticar(String usuario, String password) {
        // Profe
        if ("admin".equals(usuario) && "1234".equals(password)) {
            return new Usuario(usuario, password, TipoUsuario.ADMIN);
        }
        // Alumno (no requiere password)
        Alumno a = AlumnoDAO.buscarPorUsuario(usuario);
        if (a != null) {
            return new Usuario(usuario, null, TipoUsuario.ALUMNO);
        }
        return null;
    }
}
