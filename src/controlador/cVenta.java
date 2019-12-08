/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
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
    
    public static void registrarVenta(DefaultTableModel tablaVenta, String idCliente) {
        DocumentoVentaDao documento = new mDocumentoVenta();
        DetalleVentaDao detalles = new mDetalleVenta();
        
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String anio = Integer.toString(c.get(Calendar.YEAR));

        Cliente cliente = new Cliente();
        cliente.setIdClienteDniRuc(idCliente);
        try {
            int id = documento.obtenerIDUltimoRegistro()+1;//obteniendo el id del registro
            DocumentoVenta documentoVenta = new DocumentoVenta(11, 11, anio +"-"+ mes +"-"+ dia, 0.18, cliente);
            documento.registrar(documentoVenta);
            
            documentoVenta.setIdDocumentoVenta(id);
            List<DetalleVenta> listaDetalle = new ArrayList<DetalleVenta>();
            for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                Producto prod = new Producto();
                prod.setIdProducto(Integer.parseInt(tablaVenta.getValueAt(i, 0).toString()));
                DetalleVenta detalle = new DetalleVenta(documentoVenta, prod, Integer.parseInt(tablaVenta.getValueAt(i, 4).toString()), Double.parseDouble(tablaVenta.getValueAt(i, 5).toString()));
                listaDetalle.add(detalle);
                System.out.println("i: " + i);
            }
            detalles.registrarDetalles(listaDetalle);
        } catch (Exception e) {
            System.out.println("errorrrrr cVenta: " + e.getMessage());
        }
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
        dt.addRow(obj);
        return dt;
    }

    public static DefaultTableModel deleteProducto(int fila) {
        dt.removeRow(fila);
        return dt;
    }

}
