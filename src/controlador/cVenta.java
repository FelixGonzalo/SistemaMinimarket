package controlador;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DocumentoVentaDao;
import modelo.entidad.Cliente;
import modelo.entidad.DetalleVenta;
import modelo.entidad.DocumentoVenta;
import modelo.entidad.Producto;
import modelo.mDocumentoVenta;

public class cVenta {
    //FALTA TRABAJAR SERIE y NUMERO

    public static int registrarVenta(DefaultTableModel tablaVenta, String idCliente, String fecha) {
        Cliente cliente = new Cliente();
        cliente.setIdClienteDniRuc(idCliente);
        try {
            int band = cDocumentoVenta.registrarDocumentoVenta(cliente, fecha);
            if (band != -1) {
                int id = cDocumentoVenta.leerIdUltimoRegistro();//obteniendo el id del registro
                DocumentoVenta documentoVenta = new DocumentoVenta();
                documentoVenta.setIdDocumentoVenta(id);

                List<DetalleVenta> listaDetalle = new ArrayList<DetalleVenta>();
                for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                    Producto prod = new Producto();
                    prod.setIdProducto(Integer.parseInt(tablaVenta.getValueAt(i, 0).toString()));
                    DetalleVenta detalle = new DetalleVenta(documentoVenta, prod, Integer.parseInt(tablaVenta.getValueAt(i, 4).toString()), Double.parseDouble(tablaVenta.getValueAt(i, 5).toString()));
                    listaDetalle.add(detalle);
                }

                int band2 = cDetalleVenta.registrarDetallesVenta(listaDetalle);
                if (band2 != -1) {
                    return 1;
                } else {
                    //JOptionPane.showMessageDialog(null, "Error en modelo DetalleVenta -> registrarDetalles!!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error controlador venta -> registrarVenta: \n" + e.getMessage());
        }
        return -1;
    }

    public static String mostrarNumeroSerieDoc() {
        DecimalFormat formato1 = new DecimalFormat("0000");//solo sirve para mostrar
        DecimalFormat formato2 = new DecimalFormat("000000");//solo sirve para mostrar
        DocumentoVentaDao documento = new mDocumentoVenta();
        int serie = documento.leerSerieDoc();
        int numero = documento.leerNumeroDoc();
        String serieNumero = "";
        if (serie != -1 && numero != -1) {
            numero = numero + 1;
            serieNumero = formato1.format(serie) + " - " + formato2.format(numero);
        } else {
            serieNumero = formato1.format(1) + " - " + formato2.format(1);
        }
        serieNumero = formato1.format(serie) + " - " + formato2.format(numero);
        return serieNumero;
    }

    public static String mostrarFecha() {
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        String anio = Integer.toString(c.get(Calendar.YEAR));
        return anio + "-" + mes + "-" + dia;
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
        columnas[1] = "Codigo Barras";
        columnas[2] = "Descripción";
        columnas[3] = "Medida";
        columnas[4] = "Cantidad";
        columnas[5] = "precio";
        columnas[6] = "total";
        dt.setColumnIdentifiers(columnas);
    }

    public static DefaultTableModel agregarProducto(Object[] obj) {
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

    public static DefaultTableModel quitarProducto(int fila) {
        dt.removeRow(fila);
        return dt;
    }

    public static double calcularTotal(DefaultTableModel tablaVenta, int fila) {
        try {
            double cantidad = Double.parseDouble(tablaVenta.getValueAt(fila, 4).toString());
            double precio = Double.parseDouble(tablaVenta.getValueAt(fila, 5).toString());
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

    public static Double calculaSumaTotal(DefaultTableModel tablaVenta, int columna) {
        double sumaTotal = 0;
        try {
            for (int i = 0; i < tablaVenta.getRowCount(); i++) {
                sumaTotal = sumaTotal + Double.parseDouble(tablaVenta.getValueAt(i, columna).toString());
            }
        } catch (Exception e) {
            System.out.println("error en calcular suma total");
        }
        return Math.round(sumaTotal * 100) / 100d;
    }//total por todos los producto
}
