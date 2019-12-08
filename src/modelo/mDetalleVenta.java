/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.bd.Conexion;
import modelo.bd.Transacciones;
import modelo.dao.DetalleVentaDao;
import modelo.entidad.DetalleVenta;

/**
 *
 * @author Fekilo
 */
public class mDetalleVenta implements DetalleVentaDao {

    @Override
    public List<DetalleVenta> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetalleVenta leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar(DetalleVenta obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO detalleVenta (idDocumentoVenta,idProducto,cantidad,precioVenta) VALUES (?,?,?,?)");
            ps.setInt(1, obj.getDocumentoVenta().getIdDocumentoVenta());
            ps.setInt(2, obj.getProducto().getIdProducto());
            ps.setInt(3, obj.getCantidad());
            ps.setDouble(4, obj.getPrecioVenta());
            ps.executeUpdate();
            con.commit();
            System.out.println("listo DetalleVenta");
        } catch (Exception e) {
            System.out.println("error modelo: " + e.getMessage());
            Transacciones.usarRollback(con);
        }
    }

    @Override
    public void actualizar(DetalleVenta obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarDetalles(List<DetalleVenta> detalleVenta) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            if (detalleVenta.size() > 1) {
                for (DetalleVenta objDetalle : detalleVenta) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO detalleVenta (idDocumentoVenta,idProducto,cantidad,precioVenta) VALUES (?,?,?,?)");
                    ps.setInt(1, objDetalle.getDocumentoVenta().getIdDocumentoVenta());
                    ps.setInt(2, objDetalle.getProducto().getIdProducto());
                    ps.setInt(3, objDetalle.getCantidad());
                    ps.setDouble(4, objDetalle.getPrecioVenta());
                    ps.executeUpdate();
                }
            } else {
                PreparedStatement ps = con.prepareStatement("INSERT INTO detalleVenta (idDocumentoVenta,idProducto,cantidad,precioVenta) VALUES (?,?,?,?)");
                ps.setInt(1, detalleVenta.get(0).getDocumentoVenta().getIdDocumentoVenta());
                ps.setInt(2, detalleVenta.get(0).getProducto().getIdProducto());
                ps.setInt(3, detalleVenta.get(0).getCantidad());
                ps.setDouble(4, detalleVenta.get(0).getPrecioVenta());
                ps.executeUpdate();
            }//controla el fallo cuando es un solo registro
            con.commit();
        } catch (Exception e) {
            System.out.println("error modelo DetalleVENTA: " + e.getMessage());
            Transacciones.usarRollback(con);
        }

    }

}
