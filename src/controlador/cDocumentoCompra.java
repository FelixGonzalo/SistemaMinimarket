package controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DocumentoCompraDao;
import modelo.entidad.DocumentoCompra;
import modelo.entidad.Proveedor;
import modelo.mDocumentoCompra;

public class cDocumentoCompra {

    public static DefaultTableModel leer() {
        DocumentoCompraDao dao = new mDocumentoCompra();
        List<DocumentoCompra> lista = dao.leer();

        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        try {
            dt.addColumn("ID");
            dt.addColumn("Serie");
            dt.addColumn("Numero");
            dt.addColumn("Fecha");
            dt.addColumn("RUC");
            dt.addColumn("Proveedor");
            dt.addColumn("# Productos");
            dt.addColumn("Total");

            for (DocumentoCompra obj : lista) {
                Object[] fila = new Object[8];
                fila[0] = obj.getIdDocumentoCompra();
                fila[1] = obj.getSerie();
                fila[2] = obj.getNumero();
                fila[3] = obj.getFecha();
                fila[4] = obj.getProveedor().getRucProveedor();
                fila[5] = obj.getProveedor().getRazonSocial();
                fila[6] = obj.getCantidadProductos();
                fila[7] = obj.getTotal();
                dt.addRow(fila);
            }

        } catch (Exception e) {
            System.out.println("errorrrrrrr: " + e.getMessage());
        }

        return dt;
    }//compras realizas

    public static int registrarDocumentoCompra(Proveedor proveedor, String fecha, String serie, String numero) {
        int band = -1;
        try {
            DocumentoCompraDao documento = new mDocumentoCompra();
            DocumentoCompra documentoCompra = new DocumentoCompra(Integer.parseInt(serie), Integer.parseInt(numero), fecha, proveedor);
            band = documento.registrar(documentoCompra);
        } catch (Exception e) {
        }
        return band;
    }

    public static int leerIdUltimoRegistro() {
        DocumentoCompraDao documento = new mDocumentoCompra();
        int band = documento.leerIdUltimoRegistro();
        return band;
    }
}
