package modelo.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Transaccion {

    public static ResultSet consulta(String consulta) {
        Connection con = Conexion.getConexion();
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR Transaccion -> consulta: \n" + e.getMessage());
        }
        return rs;
    }

    public static int actualizacion(String consulta) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.executeUpdate();
            con.commit();
            con.close();
            return 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR Transaccion -> actualización: \n" + e.getMessage());
            usarRollback(con);
            return -1;
        }
    }

    public static void usarRollback(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR Transaccion -> usarRollBack: \n" + e.getMessage());
        }
    }
}
