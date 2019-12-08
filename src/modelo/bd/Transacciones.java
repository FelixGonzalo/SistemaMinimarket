/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fekilo
 */
public class Transacciones {

    //CREATE no se considera, por que cambia acorde a los atributos
    public static ResultSet consulta(String consulta) {
        Connection con = Conexion.getConexion();
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
        } catch (Exception e) {

        }
        return rs;
    }

    public static int comandos_Update_Delete(String consultaDML) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(consultaDML);
            ps.executeUpdate();
            con.commit();
            return 1;
        } catch (Exception e) {
            System.out.println("ERROR TRANSACCIONES comandos_Update_Delte");
            usarRollback(con);
            System.out.println("paso aqui");
            return -1;
        }
    }

    public static void usarRollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            System.out.println("ERROR TRANSACCIONES usarRollBack");
        }
    }
}
