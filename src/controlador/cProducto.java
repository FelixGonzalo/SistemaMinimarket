/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
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
        ProductoDao dao = new mProducto();
        CategoriaDao categoriaDao = new mCategoria();
        MarcaDao marcaDao = new mMarca();
        UnidadMedidaDao unidaMedidaDao = new mUnidadMedida();
        Categoria categoria = categoriaDao.leerNombre(nombreCategoria);
        Marca marca = marcaDao.leerNombre(nombreMarca);
        UnidadMedida unidadMedida = unidaMedidaDao.leerNombre(nombreUnidadMedida);
        try {
            Producto producto = new Producto(descripcion, Double.parseDouble(precioVenta), codigoBarras, cantidadAlmacen, cantidadMostrador, categoria, marca, unidadMedida);
            dao.registrar(producto);
        } catch (Exception e) {
            System.out.println("errorrrrr controlador: " + e.getMessage());
        }
    }

    public static void actualizar(String idProducto, String descripcion, String precioVenta, String codigoBarras, int idCategoria, int idMarca, int idUnidadMedida) {
        ProductoDao dao = new mProducto();
        try {
            Producto producto = new Producto(Integer.parseInt(idProducto), descripcion, Double.parseDouble(precioVenta), codigoBarras);
            dao.actualizar(producto);
        } catch (Exception e) {
            System.out.println("error ");
        }
    }

    public static void eliminar(String id) {
        ProductoDao dao = new mProducto();
        try {
            dao.eliminar(Integer.parseInt(id));
        } catch (Exception e) {
            System.out.println("error ");
        }
    }
    
    public static DefaultTableModel leerFiltro(String nombreCategoria,String nombreMarca,String nombreUnidadMedida){
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
    
    public static DefaultTableModel leerDescripcion(String descripcion){
        ProductoDao dao = new mProducto();
        List<Producto> lista = dao.leerDescripcion(descripcion);

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
