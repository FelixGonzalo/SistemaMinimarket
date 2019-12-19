package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.entidad.DetalleCompra;
import modelo.entidad.DocumentoCompra;
import modelo.entidad.Proveedor;
import modelo.entidad.Producto;

public class cCompra {

    public static int registrarCompra(DefaultTableModel tablaCompra, String idProveedor, String fecha, String serie, String numero) {
        Proveedor proveedor = new Proveedor();
        proveedor.setRucProveedor(idProveedor);

        try {
            int band = cDocumentoCompra.registrarDocumentoCompra(proveedor, fecha, serie, numero);
            if (band != -1) {
                int id = cDocumentoCompra.leerIdUltimoRegistro();//obteniendo el id del registro
                DocumentoCompra documentocompra = new DocumentoCompra();
                documentocompra.setIdDocumentoCompra(id);

                List<DetalleCompra> listaDetalle = new ArrayList<DetalleCompra>();
                for (int i = 0; i < tablaCompra.getRowCount(); i++) {
                    Producto prod = new Producto();
                    prod.setIdProducto(Integer.parseInt(tablaCompra.getValueAt(i, 0).toString()));
                    DetalleCompra detalle = new DetalleCompra(documentocompra, prod, Integer.parseInt(tablaCompra.getValueAt(i, 4).toString()), Double.parseDouble(tablaCompra.getValueAt(i, 5).toString()));
                    listaDetalle.add(detalle);
                }

                int band2 = cDetalleCompra.registrarDetallesCompra(listaDetalle);
                if (band2 != -1) {
                    return 1;
                } else {
                    //JOptionPane.showMessageDialog(null, "Error en modelo DetalleCompra -> registrarDetalles!!");
                }
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
                if (columna == 4 || columna == 5) {
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
        } else {
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

    public static double calcularTotal(DefaultTableModel tablacompra, int fila) {
        try {
            double cantidad = Double.parseDouble(tablacompra.getValueAt(fila, 4).toString());
            double precio = Double.parseDouble(tablacompra.getValueAt(fila, 5).toString());
            if (cantidad > 0) {
                return Math.round(cantidad * precio * 100) / 100d;
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad por producto debe ser mayor a 0 !!");
                return Math.round(1 * precio * 100) / 100d;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Especifica Cantidad !!");
            return 0;
        }
    }//total por un producto

    public static Double calculaSumaTotal(DefaultTableModel tablacompra, int columna) {
        double sumaTotal = 0;
        try {
            for (int i = 0; i < tablacompra.getRowCount(); i++) {
                sumaTotal = sumaTotal + Double.parseDouble(tablacompra.getValueAt(i, columna).toString());
            }
        } catch (Exception e) {
            System.out.println("error en calcular suma total");
        }
        return Math.round(sumaTotal * 100) / 100d;
    }//total por todos los producto
}
