package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.DocumentoVentaDao;
import modelo.entidad.Cliente;
import modelo.entidad.DocumentoVenta;

public class mDocumentoVenta implements DocumentoVentaDao {

    @Override
    public List<DocumentoVenta> leer() {
        ResultSet rs = Transaccion.consulta("SELECT doc.idDocumentoVenta,doc.serie, "
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
            //System.out.println("ERROR mDocumentoVenta -> leer: \n" + e.getMessage());
        }
        return lista;
    }

    @Override
    public int registrar(DocumentoVenta obj) {
        int band = Transaccion.actualizacion("INSERT INTO documentoVenta (serie,numero,fecha,igv,idClienteDniRuc) VALUES ('"
                + obj.getSerie() + "','"
                + obj.getNumero() + "','"
                + obj.getFecha() + "','"
                + obj.getIgv() + "','"
                + obj.getCliente().getIdClienteDniRuc() + "')");
        return band;
    }

    @Override
    public int leerIdUltimoRegistro() {
        int id = 0;
        try {
            ResultSet rs = Transaccion.consulta("SELECT MAX(idDocumentoVenta) FROM documentoVenta");
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int leerSerieDoc() {
        int id = 0;
        try {
            ResultSet rs = Transaccion.consulta("SELECT MAX(serie) FROM documentoVenta");
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int leerNumeroDoc() {
        int id = 0;
        try {
            ResultSet rs = Transaccion.consulta("SELECT MAX(numero) FROM documentoVenta");
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }
}
