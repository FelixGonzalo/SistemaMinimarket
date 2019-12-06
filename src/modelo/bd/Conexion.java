/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bd;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


/**
 *
 * @author Fekilo
 */
public class Conexion {
    private static String user = "root";
    private static String password = "fekilo";
    private static String url = "jdbc:mysql://localhost:3306/sistema_minimarket?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("conexion lista");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexión fallida: " + e);
        }
        return con;
    }
    
    public static void main(String[] args) {
        Conexion.getConexion();
    }
}
