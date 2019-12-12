package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.DocumentoCompraDao;
import modelo.entidad.DocumentoCompra;
import modelo.entidad.Proveedor;

public class mDocumentoCompra implements DocumentoCompraDao {

    @Override
    public List<DocumentoCompra> leer() {
        ResultSet rs = Transaccion.consulta("SELECT doc.idDocumentoCompra,doc.serie, "
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
            //System.out.println("ERROR mDocumentoCompra -> leer: \n" + e.getMessage());
        }
        return lista;
    }

    @Override
    public int registrar(DocumentoCompra obj) {
        int band = Transaccion.actualizacion("INSERT INTO documentoCompra (serie,numero,fecha,rucProveedor) VALUES ('"
                + obj.getSerie() + "','"
                + obj.getNumero() + "','"
                + obj.getFecha() + "','"
                + obj.getProveedor().getRucProveedor() + "')");
        return band;
    }

    @Override
    public int leerIdUltimoRegistro() {
        int id = 0;
        try {
            ResultSet rs = Transaccion.consulta("SELECT MAX(idDocumentoCompra) FROM documentoCompra");
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }
}
