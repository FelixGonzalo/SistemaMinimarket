package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.CategoriaDao;
import modelo.entidad.Categoria;

public class mCategoria implements CategoriaDao {

    @Override
    public List<Categoria> leer() {
        ResultSet rs = Transaccion.consulta("SELECT idCategoria, nombre FROM categoria ORDER BY nombre");
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(Integer.parseInt(rs.getString(1)));
                categoria.setNombre(rs.getString(2));
                lista.add(categoria);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public int registrar(Categoria obj) {
        try {
            Transaccion.actualizacion("INSERT INTO categoria (nombre) VALUES ('"
                    + obj.getNombre() + "')");
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int actualizar(Categoria obj) {
        try {
            Transaccion.actualizacion("UPDATE categoria SET "
                    + "nombre='" + obj.getNombre()
                    + "' WHERE idCategoria=" + obj.getIdCategoria());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int eliminar(int id) {
        try {
            Transaccion.actualizacion("DELETE FROM categoria WHERE idCategoria=" + id);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Categoria leerDescripcion(String nombre) {
        ResultSet rs = Transaccion.consulta("SELECT idCategoria, nombre FROM categoria WHERE nombre = '" + nombre + "'");
        Categoria categoria = new Categoria();
        try {
            while (rs.next()) {
                categoria.setIdCategoria(Integer.parseInt(rs.getString(1)));
                categoria.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return categoria;
    }

}
