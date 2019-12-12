package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.MarcaDao;
import modelo.entidad.Marca;

public class mMarca implements MarcaDao {

    @Override
    public List<Marca> leer() {
        ResultSet rs = Transaccion.consulta("SELECT idMarca, nombre FROM marca ORDER BY nombre");
        List<Marca> lista = new ArrayList<Marca>();
        try {
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(Integer.parseInt(rs.getString(1)));
                marca.setNombre(rs.getString(2));
                lista.add(marca);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public int registrar(Marca obj) {
        try {
            Transaccion.actualizacion("INSERT INTO marca (nombre) VALUES ('"
                    + obj.getNombre() + "')");
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int actualizar(Marca obj) {
        try {
            Transaccion.actualizacion("UPDATE marca SET "
                    + "nombre='" + obj.getNombre()
                    + "' WHERE idMarca=" + obj.getIdMarca());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int eliminar(int id) {
        try {
            Transaccion.actualizacion("DELETE FROM marca WHERE idMarca=" + id);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Marca leerDescripcion(String nombre) {
        ResultSet rs = Transaccion.consulta("SELECT idMarca, nombre FROM marca WHERE nombre = '" + nombre + "'");
        Marca marca = new Marca();
        try {
            while (rs.next()) {
                marca.setIdMarca(Integer.parseInt(rs.getString(1)));
                marca.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return marca;
    }

}
