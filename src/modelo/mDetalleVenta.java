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
    public int registrar(DetalleVenta obj) {
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
            return 1;
        } catch (Exception e) {
            Transacciones.usarRollback(con);
            return -1;
        }
    }

    @Override
    public int actualizar(DetalleVenta obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registrarDetalles(List<DetalleVenta> detalleVenta) {
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
            return 1;
        } catch (Exception e) {
            Transacciones.usarRollback(con);
            return -1;
        }
    }

}
