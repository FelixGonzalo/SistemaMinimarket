/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.bd.Conexion;
import modelo.bd.Transacciones;
import modelo.dao.ProveedorDao;
import modelo.entidad.Proveedor;

/**
 *
 * @author Fekilo
 */
public class mProveedor implements ProveedorDao{

    @Override
    public List<Proveedor> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proveedor leerId(int id) {
        ResultSet rs = Transacciones.consulta("SELECT rucProveedor, razonSocial, telefono, celular, correo FROM proveedor WHERE rucProveedor = '" + id + "'");
        Proveedor cliente = new Proveedor();
        try {
            while (rs.next()) {
                cliente.setRucProveedor(rs.getString(1));
                cliente.setRazonSocial(rs.getString(2));
                cliente.setTelefono(rs.getString(3));
                cliente.setCelular(rs.getString(4));
                cliente.setCorreo(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public int registrar(Proveedor obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO proveedor (rucProveedor,razonSocial,telefono,celular,correo) VALUES (?,?,?,?,?)");
            ps.setString(1, obj.getRucProveedor());
            ps.setString(2, obj.getRazonSocial());
            ps.setString(3, obj.getTelefono());
            ps.setString(4, obj.getCelular());
            ps.setString(5, obj.getCorreo());
            ps.executeUpdate();
            con.commit();
            return 1;
        } catch (Exception e) {
            System.out.println("error modelo: " + e.getMessage());
            Transacciones.usarRollback(con);
            return -1;
        }
    }

    @Override
    public int actualizar(Proveedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
