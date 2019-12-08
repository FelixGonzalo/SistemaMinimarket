/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transacciones;
import modelo.entidad.Categoria;
import modelo.entidad.Marca;
import modelo.entidad.Producto;
import modelo.entidad.UnidadMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.bd.Conexion;
import modelo.dao.ProductoDao;

/**
 *
 * @author Fekilo
 */
public class mProducto implements ProductoDao {

    @Override
    public List<Producto> leer() {
        ResultSet rs = Transacciones.consulta("SELECT p.idProducto, p.descripcion, p.precioVenta, p.codigoBarras, p.cantidadAlmacen, p.cantidadMostrador , c.nombre, m.nombre, u.nombre "
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
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Producto leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar(Producto obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO producto (descripcion, precioVenta, codigoBarras, cantidadAlmacen, cantidadMostrador, idCategoria, idMarca, idUnidadMedida) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getDescripcion());
            ps.setDouble(2, obj.getPrecioVenta());
            ps.setString(3, obj.getCodigoBarras());
            ps.setInt(4, obj.getCantidadAlmacen());
            ps.setInt(5, obj.getCantidadMostrador());
            ps.setInt(6, obj.getCategoria().getIdCategoria());
            ps.setInt(7, obj.getMarca().getIdMarca());
            ps.setInt(8, obj.getUnidadMedida().getIdUnidadMedidad());
            ps.executeUpdate();
            con.commit();
            System.out.println("listo");
        } catch (Exception e) {
            System.out.println("error modelo: " + e.getMessage());
            Transacciones.usarRollback(con);
        }
    }

    @Override
    public void actualizar(Producto obj) {
        try {
            Transacciones.comandos_Update_Delete("UPDATE producto SET "
                + "descripcion='" + obj.getDescripcion()
                + "' , precioVenta='" + obj.getPrecioVenta()
                + "' , codigoBarras='" + obj.getCodigoBarras()
                + "' , idCategoria='" + obj.getCategoria().getIdCategoria()
                + "' , idMarca='" + obj.getMarca().getIdMarca()
                + "' , idUnidadMedida='" + obj.getUnidadMedida().getIdUnidadMedidad()
                + "' WHERE idProducto=" + obj.getIdProducto());
            System.out.println("actualizacion lista");
        } catch (Exception e) {
            System.out.println("error actualizar modelo" + e.getMessage());
        }
        
    }

    @Override
    public void eliminar(int id) {
        Transacciones.comandos_Update_Delete("DELETE FROM producto WHERE idProducto=" + id);
    }

    @Override
    public List<Producto> leerFiltro(String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        ResultSet rs = Transacciones.consulta("SELECT p.idProducto, p.descripcion, p.precioVenta, p.codigoBarras, p.cantidadAlmacen, p.cantidadMostrador , c.nombre, m.nombre, u.nombre "
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
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Producto> leerDescripcion(String descripcion) {
        ResultSet rs = Transacciones.consulta("SELECT p.idProducto, p.descripcion, p.precioVenta, p.codigoBarras, p.cantidadAlmacen, p.cantidadMostrador , c.nombre, m.nombre, u.nombre "
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
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

}
