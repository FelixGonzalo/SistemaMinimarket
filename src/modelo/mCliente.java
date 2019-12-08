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
import modelo.dao.ClienteDao;
import modelo.entidad.Cliente;

/**
 *
 * @author Fekilo
 */
public class mCliente implements ClienteDao {

    @Override
    public List<Cliente> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente leerId(int id) {
        ResultSet rs = Transacciones.consulta("SELECT idClienteDniRuc, nombres, apellidos, sexo FROM cliente WHERE idClienteDniRuc = '" + id + "'");
        Cliente cliente = new Cliente();
        try {
            while (rs.next()) {
                cliente.setIdClienteDniRuc(rs.getString(1));
                cliente.setNombres(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setSexo(Integer.parseInt(rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public int registrar(Cliente obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (idClienteDniRuc,nombres,apellidos,sexo) VALUES (?,?,?,?)");
            ps.setString(1, obj.getIdClienteDniRuc());
            ps.setString(2, obj.getNombres());
            ps.setString(3, obj.getApellidos());
            ps.setInt(4, obj.getSexo());
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
    public int actualizar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
