/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.CategoriaDao;
import modelo.dao.MarcaDao;
import modelo.dao.ProductoDao;
import modelo.dao.UnidadMedidaDao;
import modelo.mProducto;
import modelo.entidad.Categoria;
import modelo.entidad.Marca;
import modelo.entidad.Producto;
import modelo.entidad.UnidadMedida;
import modelo.mCategoria;
import modelo.mMarca;
import modelo.mUnidadMedida;

/**
 *
 * @author Fekilo
 */
public class cProducto {

    public static DefaultTableModel leer() {
        ProductoDao dao = new mProducto();
        List<Producto> lista = dao.leer();

        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 0 || columna == 7 || columna == 8) {
                    return false;
                } else {
                    return true;
                }//No permite la modificacion de ciertos datos dentro  dentro de la tabla productos
            }
        };
        dt.addColumn("ID");
        dt.addColumn("CodigoBarras");
        dt.addColumn("Descripción");
        dt.addColumn("PrecioVenta");
        dt.addColumn("Categoria");
        dt.addColumn("Marca");
        dt.addColumn("UnidadMedida");
        dt.addColumn("En Almacen");
        dt.addColumn("En Mostrador");

        for (Producto obj : lista) {
            Object[] fila = new Object[9];
            fila[0] = obj.getIdProducto();
            fila[1] = obj.getCodigoBarras();
            fila[2] = obj.getDescripcion();
            fila[3] = obj.getPrecioVenta();
            fila[4] = obj.getCategoria().getNombre();
            fila[5] = obj.getMarca().getNombre();
            fila[6] = obj.getUnidadMedida().getNombre();
            fila[7] = obj.getCantidadAlmacen();
            fila[8] = obj.getCantidadMostrador();
            dt.addRow(fila);
        }
        return dt;
    }

    public static void registrar(String descripcion, String precioVenta, String codigoBarras, int cantidadAlmacen, int cantidadMostrador, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        try {
            ProductoDao dao = new mProducto();
            CategoriaDao categoriaDao = new mCategoria();
            MarcaDao marcaDao = new mMarca();
            UnidadMedidaDao unidaMedidaDao = new mUnidadMedida();
            Categoria categoria = categoriaDao.leerDescripcion(nombreCategoria);
            Marca marca = marcaDao.leerDescripcion(nombreMarca);
            UnidadMedida unidadMedida = unidaMedidaDao.leerDescripcion(nombreUnidadMedida);
            Producto producto = new Producto(descripcion, Double.parseDouble(precioVenta), codigoBarras, cantidadAlmacen, cantidadMostrador, categoria, marca, unidadMedida);
            int band = dao.registrar(producto);
            if (band != -1) {
                JOptionPane.showMessageDialog(null, "Registro de Producto listo!!");
            } else {
                JOptionPane.showMessageDialog(null, "Error en modelo Producto -> registrar!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en controlador Producto -> registrar: \n" + e.getMessage());
        }
    }

    public static void actualizar(String idProducto, String descripcion, String precioVenta, String codigoBarras, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        try {
            ProductoDao dao = new mProducto();
            CategoriaDao categoriaDao = new mCategoria();
            MarcaDao marcaDao = new mMarca();
            UnidadMedidaDao unidaMedidaDao = new mUnidadMedida();
            Categoria categoria = categoriaDao.leerDescripcion(nombreCategoria);
            Marca marca = marcaDao.leerDescripcion(nombreMarca);
            UnidadMedida unidadMedida = unidaMedidaDao.leerDescripcion(nombreUnidadMedida);
            Producto producto = new Producto(Integer.parseInt(idProducto), descripcion, Double.parseDouble(precioVenta), codigoBarras, categoria, marca, unidadMedida);
            int band = dao.actualizar(producto);
            if (band != -1) {
                JOptionPane.showMessageDialog(null, "Actualización de Producto lista!!");
            } else {
                JOptionPane.showMessageDialog(null, "Error en modelo Producto -> actualizar!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en controlador Producto -> actualizar: \n" + e.getMessage());
        }
    }

    public static void eliminar(String id) {
        try {
            ProductoDao dao = new mProducto();
            int band = dao.eliminar(Integer.parseInt(id));
            if (band != -1) {
                JOptionPane.showMessageDialog(null, "Eliminación de Producto lista!!");
            } else {
                JOptionPane.showMessageDialog(null, "Error en modelo Producto -> eliminar!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en controlador Producto -> eliminar: \n" + e.getMessage());
        }
    }

    public static DefaultTableModel leerFiltro(String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        ProductoDao dao = new mProducto();
        List<Producto> lista = dao.leerFiltro(nombreCategoria, nombreMarca, nombreUnidadMedida);

        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 0 || columna == 7 || columna == 8) {
                    return false;
                } else {
                    return true;
                }//No permite la modificacion de ciertos datos dentro  dentro de la tabla productos
            }
        };
        dt.addColumn("ID");
        dt.addColumn("CodigoBarras");
        dt.addColumn("Descripción");
        dt.addColumn("PrecioVenta");
        dt.addColumn("Categoria");
        dt.addColumn("Marca");
        dt.addColumn("UnidadMedida");
        dt.addColumn("En Almacen");
        dt.addColumn("En Mostrador");

        for (Producto obj : lista) {
            Object[] fila = new Object[9];
            fila[0] = obj.getIdProducto();
            fila[1] = obj.getCodigoBarras();
            fila[2] = obj.getDescripcion();
            fila[3] = obj.getPrecioVenta();
            fila[4] = obj.getCategoria().getNombre();
            fila[5] = obj.getMarca().getNombre();
            fila[6] = obj.getUnidadMedida().getNombre();
            fila[7] = obj.getCantidadAlmacen();
            fila[8] = obj.getCantidadMostrador();
            dt.addRow(fila);
        }
        return dt;
    }

    public static DefaultTableModel leerDescripcion(String descripcion) {
        ProductoDao dao = new mProducto();
        List<Producto> lista = dao.leerFiltro(descripcion);

        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 0 || columna == 7 || columna == 8) {
                    return false;
                } else {
                    return true;
                }//No permite la modificacion de ciertos datos dentro  dentro de la tabla productos
            }
        };
        dt.addColumn("ID");
        dt.addColumn("CodigoBarras");
        dt.addColumn("Descripción");
        dt.addColumn("PrecioVenta");
        dt.addColumn("Categoria");
        dt.addColumn("Marca");
        dt.addColumn("UnidadMedida");
        dt.addColumn("En Almacen");
        dt.addColumn("En Mostrador");

        for (Producto obj : lista) {
            Object[] fila = new Object[9];
            fila[0] = obj.getIdProducto();
            fila[1] = obj.getCodigoBarras();
            fila[2] = obj.getDescripcion();
            fila[3] = obj.getPrecioVenta();
            fila[4] = obj.getCategoria().getNombre();
            fila[5] = obj.getMarca().getNombre();
            fila[6] = obj.getUnidadMedida().getNombre();
            fila[7] = obj.getCantidadAlmacen();
            fila[8] = obj.getCantidadMostrador();
            dt.addRow(fila);
        }
        return dt;
    }
}
