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
import modelo.dao.DetalleCompraDao;
import modelo.entidad.DetalleCompra;

/**
 *
 * @author Fekilo
 */
public class mDetalleCompra implements DetalleCompraDao{

    @Override
    public List<DetalleCompra> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetalleCompra leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registrar(DetalleCompra obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(DetalleCompra obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registrarDetalles(List<DetalleCompra> detalleCompra) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            if (detalleCompra.size() > 1) {
                for (DetalleCompra objDetalle : detalleCompra) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO detalleCompra (idDocumentoCompra,idProducto,cantidad,precioCompra) VALUES (?,?,?,?)");
                    ps.setInt(1, objDetalle.getDocumentoCompra().getIdDocumentoCompra());
                    ps.setInt(2, objDetalle.getProducto().getIdProducto());
                    ps.setInt(3, objDetalle.getCantidad());
                    ps.setDouble(4, objDetalle.getPrecioVenta());
                    ps.executeUpdate();
                }
            } else {
                PreparedStatement ps = con.prepareStatement("INSERT INTO detalleCompra (idDocumentoCompra,idProducto,cantidad,precioCompra) VALUES (?,?,?,?)");
                ps.setInt(1, detalleCompra.get(0).getDocumentoCompra().getIdDocumentoCompra());
                ps.setInt(2, detalleCompra.get(0).getProducto().getIdProducto());
                ps.setInt(3, detalleCompra.get(0).getCantidad());
                ps.setDouble(4, detalleCompra.get(0).getPrecioVenta());
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
