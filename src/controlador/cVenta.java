/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.bd.Conexion;
import modelo.bd.Transacciones;
import modelo.dao.DetalleVentaDao;
import modelo.dao.DocumentoVentaDao;
import modelo.entidad.Cliente;
import modelo.entidad.DetalleVenta;
import modelo.entidad.DocumentoVenta;
import modelo.entidad.Producto;
import modelo.mDetalleVenta;
import modelo.mDocumentoVenta;

/**
 *
 * @author Fekilo
 */
public class cVenta {
    //FALTA TRABAJAR SERIE y NUMERO

    public static int registrarVenta(DefaultTableModel tablaVenta, String idCliente) {
        DocumentoVentaDao documento = new mDocumentoVenta();
        DetalleVentaDao detalles = new mDetalleVenta();

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        String anio = Integer.toString(c.get(Calendar.YEAR));

        Cliente cliente = new Cliente();
        cliente.setIdClienteDniRuc(idCliente);

        Connection con = Conexion.getConexion();
        try {

            DocumentoVenta documentoVenta = new DocumentoVenta(11, 11, anio + "-" + mes + "-" + dia, 0.18, cliente);
            //documento.registrar(documentoVenta);
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO documentoVenta (serie,numero,fecha,igv,idClienteDniRuc) VALUES (?,?,?,?,?)");
            ps.setInt(1, documentoVenta.getSerie());
            ps.setInt(2, documentoVenta.getNumero());
            ps.setString(3, documentoVenta.getFecha());
            ps.setDouble(4, documentoVenta.getIgv());
            ps.setString(5, documentoVenta.getCliente().getIdClienteDniRuc());
            ps.executeUpdate();
            con.commit();// si falla ya no realizar los detalle Venta

            int id = documento.obtenerIDUltimoRegistro();//obteniendo el id del registro
            documentoVenta.setIdDocumentoVenta(id);
            List<DetalleVenta> listaDetalle = new ArrayList<DetalleVenta>();
            for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                Producto prod = new Producto();
                prod.setIdProducto(Integer.parseInt(tablaVenta.getValueAt(i, 0).toString()));
                DetalleVenta detalle = new DetalleVenta(documentoVenta, prod, Integer.parseInt(tablaVenta.getValueAt(i, 4).toString()), Double.parseDouble(tablaVenta.getValueAt(i, 5).toString()));
                listaDetalle.add(detalle);
            }

            int band = detalles.registrarDetalles(listaDetalle);
            if (band != -1) {
                return 1;
            } else {
                JOptionPane.showMessageDialog(null, "Error en modelo DetalleVenta -> registrarDetalles!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error controlador venta -> registrarVenta: \n" + e.getMessage());
            Transacciones.usarRollback(con);
        }
        return -1;
    }

//Control de la Selección de productos a vender
    static DefaultTableModel dt;

    public static void controlTablaVenta() {
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
