package Controlador;

public class Validar {

    public boolean correoValido(String correo) {
        if (correo == null || correo.isEmpty()) {
            return false;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            return false;
        }

        if (!(correo.endsWith("@gmail.com") || correo.endsWith("@outlook.com") || correo.endsWith("@yahoo.com"))) {
            return false;
        }

        return true;
    }

    public boolean contraValida(String password) {
        if (password == null || password.length() < 4) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        return true;
    }
}
