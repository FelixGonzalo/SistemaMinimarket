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
        dt.addColumn("Precio");
        dt.addColumn("Categoria");
        dt.addColumn("Marca");
        dt.addColumn("Medida");
        dt.addColumn("Almacen");
        dt.addColumn("Mostrador");

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

    public static int registrar(String descripcion, String precioVenta, String codigoBarras, int cantidadAlmacen, int cantidadMostrador, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        int band = -1;
        if (Controlador.isNumeric(precioVenta)) {
            if (!descripcion.equalsIgnoreCase("") && !precioVenta.equalsIgnoreCase("")) {
                ProductoDao dao = new mProducto();
                CategoriaDao categoriaDao = new mCategoria();
                MarcaDao marcaDao = new mMarca();
                UnidadMedidaDao unidaMedidaDao = new mUnidadMedida();
                Categoria categoria = categoriaDao.leerDescripcion(nombreCategoria);
                Marca marca = marcaDao.leerDescripcion(nombreMarca);
                UnidadMedida unidadMedida = unidaMedidaDao.leerDescripcion(nombreUnidadMedida);
                Producto producto = new Producto(descripcion, Double.parseDouble(precioVenta), codigoBarras, cantidadAlmacen, cantidadMostrador, categoria, marca, unidadMedida);
                band = dao.registrar(producto);
            } else {
                band = 2;
            }
        } else {
            band = 3;
        }
        return band;
    }

    public static int actualizar(String idProducto, String descripcion, String precioVenta, String codigoBarras, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        int band = -1;
        if (Controlador.isNumeric(precioVenta)) {
            if (!descripcion.equalsIgnoreCase("") && !precioVenta.equalsIgnoreCase("") && !nombreCategoria.equalsIgnoreCase("") && !nombreMarca.equalsIgnoreCase("") && !nombreUnidadMedida.equalsIgnoreCase("")) {
                ProductoDao dao = new mProducto();
                CategoriaDao categoriaDao = new mCategoria();
                MarcaDao marcaDao = new mMarca();
                UnidadMedidaDao unidaMedidaDao = new mUnidadMedida();
                Categoria categoria = categoriaDao.leerDescripcion(nombreCategoria);
                Marca marca = marcaDao.leerDescripcion(nombreMarca);
                UnidadMedida unidadMedida = unidaMedidaDao.leerDescripcion(nombreUnidadMedida);
                Producto producto = new Producto(Integer.parseInt(idProducto), descripcion, Double.parseDouble(precioVenta), codigoBarras, categoria, marca, unidadMedida);
                band = dao.actualizar(producto);
            } else {
                band = 2;
            }
        } else {
            band = 3;
        }
        return band;
    }

    public static int eliminar(String id) {
        int band = -1;
        if (Controlador.isNumeric(id)) {
            ProductoDao dao = new mProducto();
            band = dao.eliminar(Integer.parseInt(id));
        } else {
            band = 3;
        }
        return band;
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
        dt.addColumn("Precio");
        dt.addColumn("Categoria");
        dt.addColumn("Marca");
        dt.addColumn("Medida");
        dt.addColumn("Almacen");
        dt.addColumn("Mostrador");

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
        dt.addColumn("Precio");
        dt.addColumn("Categoria");
        dt.addColumn("Marca");
        dt.addColumn("Medida");
        dt.addColumn("Almacen");
        dt.addColumn("Mostrador");

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
