/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.bd.Transacciones;
import modelo.dao.DocumentoVentaDao;
import modelo.entidad.Cliente;
import modelo.entidad.DocumentoVenta;

/**
 *
 * @author Fekilo
 */
public class mDocumentoVenta implements DocumentoVentaDao {

    @Override
    public List<DocumentoVenta> leer() {
        ResultSet rs = Transacciones.consulta("SELECT doc.idDocumentoVenta,doc.serie, "
                + "doc.numero,doc.fecha, doc.igv, doc.idClienteDniRuc,c.nombres, c.apellidos, "
                + "SUM(dv.cantidad) as cantidadProductos, "
                + "CAST(SUM(dv.cantidad*dv.precioVenta) /(doc.igv+1) as decimal(6,2)) as subTotal, "
                + "CAST(SUM(dv.cantidad*dv.precioVenta) /(doc.igv+1)*0.18 as decimal(6,2)) as montoIGV, "
                + "SUM(dv.cantidad*dv.precioVenta) as importeTotal "
                + "FROM documentoVenta as doc "
                + "JOIN cliente as c "
                + "     on c.idClienteDniRuc = doc.idClienteDniRuc "
                + "JOIN detalleVenta as dv "
                + "	on doc.idDocumentoVenta = dv.idDocumentoVenta "
                + "GROUP BY doc.idDocumentoVenta "
                + "ORDER BY doc.fecha, doc.serie,doc.numero;");
        List<DocumentoVenta> lista = new ArrayList<DocumentoVenta>();
        try {
            while (rs.next()) {
                DocumentoVenta documentoVenta = new DocumentoVenta();
                Cliente cliente = new Cliente();
                documentoVenta.setIdDocumentoVenta(Integer.parseInt(rs.getString(1)));
                documentoVenta.setSerie(Integer.parseInt(rs.getString(2)));
                documentoVenta.setNumero(Integer.parseInt(rs.getString(3)));
                documentoVenta.setFecha(rs.getString(4));
                documentoVenta.setIgv(Double.parseDouble(rs.getString(5)));
                cliente.setIdClienteDniRuc(rs.getString(6)); //  cliente.setIdClienteDniRuc(Integer.parseInt(rs.getString(6)));  STRING
                cliente.setNombres(rs.getString(7));
                cliente.setApellidos(rs.getString(8));
                documentoVenta.setCantidadProductos(rs.getString(9));
                documentoVenta.setSubtotal(rs.getString(10));
                documentoVenta.setMontoIGV(rs.getString(11));
                documentoVenta.setTotal(rs.getString(12));
                documentoVenta.setCliente(cliente);
                lista.add(documentoVenta);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public DocumentoVenta leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar(DocumentoVenta obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO documentoVenta (serie,numero,fecha,igv,idClienteDniRuc) VALUES (?,?,?,?,?)");
            ps.setInt(1, obj.getSerie());
            ps.setInt(2, obj.getNumero());
            ps.setString(3, obj.getFecha());
            ps.setDouble(4, obj.getIgv());
            ps.setString(5, obj.getCliente().getIdClienteDniRuc());//ps.setInt(5, obj.getCliente().getIdClienteDniRuc());
            ps.executeUpdate();
            con.commit();
            System.out.println("listo DocumentoVenta");
        } catch (Exception e) {
            System.out.println("error modelo: " + e.getMessage());
            Transacciones.usarRollback(con);
        }
    }

    @Override
    public void actualizar(DocumentoVenta obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int obtenerIDUltimoRegistro(){
        int id = 0;
        try {
            ResultSet rs = Transacciones.consulta("SELECT MAX(idDocumentoVenta) FROM documentoVenta");
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (Exception e) {
            System.out.println("enviando comodin de ultimo registro");
            return -1;
        }
    }
}
