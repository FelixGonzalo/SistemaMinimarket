package controlador;

public class Controlador {

    public static boolean isNumeric(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
