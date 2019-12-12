/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DetalleCompraDao;
import modelo.dao.DocumentoCompraDao;
import modelo.entidad.DetalleCompra;
import modelo.entidad.DocumentoCompra;
import modelo.entidad.Proveedor;
import modelo.mDetalleCompra;
import modelo.mDocumentoCompra;
import modelo.entidad.Producto;

/**
 *
 * @author Fekilo
 */
public class cCompra {
    public static int registrarCompra(DefaultTableModel tablaCompra, String idProveedor) {
        DocumentoCompraDao documento = new mDocumentoCompra();
        DetalleCompraDao detalles = new mDetalleCompra();

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        String anio = Integer.toString(c.get(Calendar.YEAR));

        Proveedor proveedor = new Proveedor();
        proveedor.setRucProveedor(idProveedor);

        /*Connection con = Conexion.getConexion();123*/
        try {
            DocumentoCompra documentocompra = new DocumentoCompra(1, 1, anio + "-" + mes + "-" + dia, proveedor);
            documento.registrar(documentocompra);
            /*con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO documentoVenta (serie,numero,fecha,igv,idClienteDniRuc) VALUES (?,?,?,?,?)");
            ps.setInt(1, documentoVenta.getSerie());
            ps.setInt(2, documentoVenta.getNumero());
            ps.setString(3, documentoVenta.getFecha());
            ps.setDouble(4, documentoVenta.getIgv());
            ps.setString(5, documentoVenta.getCliente().getIdClienteDniRuc());
            ps.executeUpdate();
            con.commit();// si falla ya no realizar los detalle Venta123*/
            
            int id = documento.leerIdUltimoRegistro();//obteniendo el id del registro
            documentocompra.setIdDocumentoCompra(id);
            List<DetalleCompra> listaDetalle = new ArrayList<DetalleCompra>();
            for (int i = 0; i < tablaCompra.getRowCount(); i++) {
                Producto prod = new Producto();
                prod.setIdProducto(Integer.parseInt(tablaCompra.getValueAt(i, 0).toString()));
                DetalleCompra detalle = new DetalleCompra(documentocompra, prod, Integer.parseInt(tablaCompra.getValueAt(i, 4).toString()), Double.parseDouble(tablaCompra.getValueAt(i, 5).toString()));
                listaDetalle.add(detalle);
            }

            int band = detalles.registrarLista(listaDetalle);
            if (band != -1) {
                return 1;
            } else {
                JOptionPane.showMessageDialog(null, "Error en modelo DetalleCompra -> registrarDetalles!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error controlador Compra -> registrarCompra: \n" + e.getMessage());
            /*Transacciones.usarRollback(con);123*/
        }
        return -1;
    }

//Control de la Selección de productos a vender
    static DefaultTableModel dt;

    public static void controlTablaCompra() {
        dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 4) {
                    return true;
                } else {
                    return false;
                }//No permite la modificacion de ciertos datos dentro  dentro de la tabla productos
            }
        };
        Object[] columnas = new Object[7];
        columnas[0] = "ID";
        columnas[1] = "CodigoBarras";
        columnas[2] = "Descripción";
        columnas[3] = "Unidad";
        columnas[4] = "Cantidad";
        columnas[5] = "precio";
        columnas[6] = "total";
        dt.setColumnIdentifiers(columnas);
    }

    public static DefaultTableModel addProducto(Object[] obj) {
        if (!existe((String) obj[0])) {
             dt.addRow(obj);
        }else{
            JOptionPane.showMessageDialog(null, "El producto ya ha sido seleccionado!!");
        }
        return dt;
    }

    public static boolean existe(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < dt.getRowCount(); i++) {
            if (dt.getValueAt(i, 0).toString().equalsIgnoreCase(nombre)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public static DefaultTableModel deleteProducto(int fila) {
        dt.removeRow(fila);
        return dt;
    }
}
