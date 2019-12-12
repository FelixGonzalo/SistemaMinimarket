package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transaccion;
import modelo.entidad.Categoria;
import modelo.entidad.Marca;
import modelo.entidad.Producto;
import modelo.entidad.UnidadMedida;
import modelo.dao.ProductoDao;

public class mProducto implements ProductoDao {

    @Override
    public List<Producto> leer() {
        ResultSet rs = Transaccion.consulta("SELECT p.idProducto, p.descripcion, p.precioVenta, p.codigoBarras, p.cantidadAlmacen, p.cantidadMostrador , c.nombre, m.nombre, u.nombre "
                + "FROM producto as p "
                + "join marca as m "
                + "	on m.idMarca = p.idMarca "
                + "join categoria as c "
                + "	on c.idCategoria = p.idCategoria "
                + "join unidadmedida as u "
                + "	on u.idUnidadMedida = p.idUnidadMedida "
                + "ORDER BY p.descripcion;");
        List<Producto> lista = new ArrayList<Producto>();
        try {
            while (rs.next()) {
                Producto producto = new Producto();
                Categoria categoria = new Categoria();
                Marca marca = new Marca();
                UnidadMedida nodo4 = new UnidadMedida();
                producto.setIdProducto(Integer.parseInt(rs.getString(1)));
                producto.setDescripcion(rs.getString(2));
                producto.setPrecioVenta(Double.parseDouble(rs.getString(3)));
                producto.setCodigoBarras(rs.getString(4));
                producto.setCantidadAlmacen(Integer.parseInt(rs.getString(5)));
                producto.setCantidadMostrador(Integer.parseInt(rs.getString(6)));
                categoria.setNombre(rs.getString(7));
                producto.setCategoria(categoria);
                marca.setNombre(rs.getString(8));
                producto.setMarca(marca);
                nodo4.setNombre(rs.getString(9));
                producto.setUnidadMedida(nodo4);
                lista.add(producto);
            }
        } catch (Exception e) {
            //System.out.println("ERROR mProducto -> leer: \n" + e.getMessage());
        }
        return lista;
    }

    @Override
    public int registrar(Producto obj) {
        int band = Transaccion.actualizacion("INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) VALUES ('"
                + obj.getDescripcion() + "','"
                + obj.getPrecioVenta() + "','"
                + obj.getCodigoBarras() + "','"
                + obj.getCantidadAlmacen() + "','"
                + obj.getCantidadMostrador() + "','"
                + obj.getCategoria().getIdCategoria() + "','"
                + obj.getMarca().getIdMarca() + "','"
                + obj.getUnidadMedida().getIdUnidadMedidad() + "')");
        return band;
    }

    @Override
    public int actualizar(Producto obj) {
        int band = Transaccion.actualizacion("UPDATE producto SET "
                + "descripcion='" + obj.getDescripcion()
                + "' , precioVenta='" + obj.getPrecioVenta()
                + "' , codigoBarras='" + obj.getCodigoBarras()
                + "' , idCategoria='" + obj.getCategoria().getIdCategoria()
                + "' , idMarca='" + obj.getMarca().getIdMarca()
                + "' , idUnidadMedida='" + obj.getUnidadMedida().getIdUnidadMedidad()
                + "' WHERE idProducto=" + obj.getIdProducto());
        return band;
    }

    @Override
    public int eliminar(int id) {
        int band = Transaccion.actualizacion("DELETE FROM producto WHERE idProducto=" + id);
        return band;
    }

    @Override
    public List<Producto> leerFiltro(String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        ResultSet rs = Transaccion.consulta("SELECT p.idProducto, p.descripcion, p.precioVenta, p.codigoBarras, p.cantidadAlmacen, p.cantidadMostrador , c.nombre, m.nombre, u.nombre "
                + "FROM producto as p "
                + "join categoria as c "
                + "	on c.idCategoria = p.idCategoria and c.nombre = '" + nombreCategoria + "' "
                + "join marca as m "
                + "	on m.idMarca = p.idMarca and m.nombre = '" + nombreMarca + "' "
                + "join unidadmedida as u "
                + "	on u.idUnidadMedida = p.idUnidadMedida and u.nombre = '" + nombreUnidadMedida + "' "
                + "ORDER BY p.descripcion;");
        List<Producto> lista = new ArrayList<Producto>();
        try {
            while (rs.next()) {
                Producto producto = new Producto();
                Categoria categoria = new Categoria();
                Marca marca = new Marca();
                UnidadMedida nodo4 = new UnidadMedida();
                producto.setIdProducto(Integer.parseInt(rs.getString(1)));
                producto.setDescripcion(rs.getString(2));
                producto.setPrecioVenta(Double.parseDouble(rs.getString(3)));
                producto.setCodigoBarras(rs.getString(4));
                producto.setCantidadAlmacen(Integer.parseInt(rs.getString(5)));
                producto.setCantidadMostrador(Integer.parseInt(rs.getString(6)));
                categoria.setNombre(rs.getString(7));
                producto.setCategoria(categoria);
                marca.setNombre(rs.getString(8));
                producto.setMarca(marca);
                nodo4.setNombre(rs.getString(9));
                producto.setUnidadMedida(nodo4);
                lista.add(producto);
            }
        } catch (Exception e) {
            //System.out.println("ERROR mProducto -> leerFiltro: \n" + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Producto> leerFiltro(String descripcion) {
        ResultSet rs = Transaccion.consulta("SELECT p.idProducto, p.descripcion, p.precioVenta, p.codigoBarras, p.cantidadAlmacen, p.cantidadMostrador , c.nombre, m.nombre, u.nombre "
                + "FROM producto as p "
                + "join categoria as c "
                + "	on c.idCategoria = p.idCategoria "
                + "join marca as m "
                + "	on m.idMarca = p.idMarca "
                + "join unidadmedida as u "
                + "	on u.idUnidadMedida = p.idUnidadMedida and p.descripcion LIKE '%" + descripcion + "%' "
                + "ORDER BY p.descripcion;");
        List<Producto> lista = new ArrayList<Producto>();
        try {
            while (rs.next()) {
                Producto producto = new Producto();
                Categoria categoria = new Categoria();
                Marca marca = new Marca();
                UnidadMedida nodo4 = new UnidadMedida();
                producto.setIdProducto(Integer.parseInt(rs.getString(1)));
                producto.setDescripcion(rs.getString(2));
                producto.setPrecioVenta(Double.parseDouble(rs.getString(3)));
                producto.setCodigoBarras(rs.getString(4));
                producto.setCantidadAlmacen(Integer.parseInt(rs.getString(5)));
                producto.setCantidadMostrador(Integer.parseInt(rs.getString(6)));
                categoria.setNombre(rs.getString(7));
                producto.setCategoria(categoria);
                marca.setNombre(rs.getString(8));
                producto.setMarca(marca);
                nodo4.setNombre(rs.getString(9));
                producto.setUnidadMedida(nodo4);
                lista.add(producto);
            }
        } catch (Exception e) {
            //System.out.println("ERROR mProducto -> leerFiltro: \n" + e.getMessage());
        }
        return lista;
    }

}
