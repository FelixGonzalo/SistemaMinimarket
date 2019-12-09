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
import modelo.dao.DocumentoCompraDao;
import modelo.entidad.DocumentoCompra;
import modelo.entidad.Proveedor;

/**
 *
 * @author Fekilo
 */
public class mDocumentoCompra implements DocumentoCompraDao {

    @Override
    public List<DocumentoCompra> leer() {
        ResultSet rs = Transacciones.consulta("SELECT doc.idDocumentoCompra,doc.serie, "
                + "	doc.numero,doc.fecha, doc.rucProveedor, p.RazonSocial, "
                + "	SUM(dc.cantidad) as cantidadProductos, "
                + "     SUM(dc.cantidad*dc.preciocompra) as importeTotal "
                + "FROM documentoCompra as doc "
                + "join Proveedor as p "
                + "	on p.rucProveedor = doc.rucProveedor "
                + "join detalleCompra as dc "
                + "	on doc.idDocumentoCompra = dc.idDocumentoCompra "
                + "    group by doc.idDocumentoCompra "
                + "    ORDER BY doc.fecha, doc.serie,doc.numero");
        List<DocumentoCompra> lista = new ArrayList<DocumentoCompra>();
        try {
            while (rs.next()) {
                DocumentoCompra documentoCompra = new DocumentoCompra();
                Proveedor proveedor = new Proveedor();
                documentoCompra.setIdDocumentoCompra(Integer.parseInt(rs.getString(1)));
                documentoCompra.setSerie(Integer.parseInt(rs.getString(2)));
                documentoCompra.setNumero(Integer.parseInt(rs.getString(3)));
                documentoCompra.setFecha(rs.getString(4));
                proveedor.setRucProveedor(rs.getString(5));
                proveedor.setRazonSocial(rs.getString(6));
                documentoCompra.setCantidadProductos(rs.getString(7));
                documentoCompra.setTotal(rs.getString(8));
                documentoCompra.setProveedor(proveedor);
                lista.add(documentoCompra);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public DocumentoCompra leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registrar(DocumentoCompra obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO documentoCompra (serie,numero,fecha,rucProveedor) VALUES (?,?,?,?)");
            ps.setInt(1, obj.getSerie());
            ps.setInt(2, obj.getNumero());
            ps.setString(3, obj.getFecha());
            ps.setString(4, obj.getProveedor().getRucProveedor());
            ps.executeUpdate();
            con.commit();
            return 1;
        } catch (Exception e) {
            Transacciones.usarRollback(con);
            return -1;
        }
    }

    @Override
    public int actualizar(DocumentoCompra obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obtenerIDUltimoRegistro() {
        int id = 0;
        try {
            ResultSet rs = Transacciones.consulta("SELECT MAX(idDocumentoCompra) FROM documentoCompra");
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

}
