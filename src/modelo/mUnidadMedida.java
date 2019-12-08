/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.bd.Transacciones;
import modelo.dao.UnidadMedidaDao;
import modelo.entidad.UnidadMedida;

/**
 *
 * @author Fekilo
 */
public class mUnidadMedida implements UnidadMedidaDao {

    @Override
    public List<UnidadMedida> leer() {
        ResultSet rs = Transacciones.consulta("SELECT idUnidadMedida, nombre, UPPER(abreviatura) FROM unidadMedida ORDER BY nombre");
        List<UnidadMedida> lista = new ArrayList<UnidadMedida>();
        try {
            while (rs.next()) {
                UnidadMedida unidadMedida = new UnidadMedida();
                unidadMedida.setIdUnidadMedidad(Integer.parseInt(rs.getString(1)));
                unidadMedida.setNombre(rs.getString(2));
                unidadMedida.setAbreviatura(rs.getString(3));
                lista.add(unidadMedida);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public UnidadMedida leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registrar(UnidadMedida obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO unidadMedida (nombre, abreviatura) VALUES (?,?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getAbreviatura());
            ps.executeUpdate();
            con.commit();
            return 1;
        } catch (Exception e) {
            Transacciones.usarRollback(con);
            return -1;
        }
    }

    @Override
    public int actualizar(UnidadMedida obj) {
        try {
            Transacciones.comandos_Update_Delete("UPDATE unidadMedida SET "
                    + "nombre='" + obj.getNombre()
                    + "' , abreviatura='" + obj.getAbreviatura()
                    + "' WHERE idUnidadMedida=" + obj.getIdUnidadMedidad());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int eliminar(int id) {
        try {
            Transacciones.comandos_Update_Delete("DELETE FROM unidadMedida WHERE idUnidadMedida=" + id);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public UnidadMedida leerNombre(String nombre) {
        ResultSet rs = Transacciones.consulta("SELECT idUnidadMedida, nombre FROM unidadMedida WHERE nombre = '" + nombre + "'");
        UnidadMedida unidadMedida = new UnidadMedida();
        try {
            while (rs.next()) {
                unidadMedida.setIdUnidadMedidad(Integer.parseInt(rs.getString(1)));
                unidadMedida.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return unidadMedida;
    }

}
