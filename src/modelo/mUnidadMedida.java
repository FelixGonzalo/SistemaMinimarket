package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.UnidadMedidaDao;
import modelo.entidad.UnidadMedida;

public class mUnidadMedida implements UnidadMedidaDao {

    @Override
    public List<UnidadMedida> leer() {
        ResultSet rs = Transaccion.consulta("SELECT idUnidadMedida, nombre, UPPER(abreviatura) FROM unidadMedida ORDER BY nombre");
        List<UnidadMedida> lista = new ArrayList<UnidadMedida>();
        try {
            while (rs.next()) {
                UnidadMedida unidadMedida = new UnidadMedida();
                unidadMedida.setIdUnidadMedidad(Integer.parseInt(rs.getString(1)));
                unidadMedida.setNombre(rs.getString(2));
                unidadMedida.setAbreviatura(rs.getString(3));
                lista.add(unidadMedida);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public int registrar(UnidadMedida obj) {
        try {
            Transaccion.actualizacion("INSERT INTO unidadMedida (nombre, abreviatura) VALUES ('"
                    + obj.getNombre() + "','"
                    + obj.getAbreviatura() + "')");
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int actualizar(UnidadMedida obj) {
        try {
            Transaccion.actualizacion("UPDATE unidadMedida SET "
                    + "nombre='" + obj.getNombre()
                    + "' , abreviatura='" + obj.getAbreviatura()
                    + "' WHERE idUnidadMedida=" + obj.getIdUnidadMedidad());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int eliminar(int id) {
        try {
            Transaccion.actualizacion("DELETE FROM unidadMedida WHERE idUnidadMedida=" + id);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public UnidadMedida leerDescripcion(String nombre) {
        ResultSet rs = Transaccion.consulta("SELECT idUnidadMedida, nombre FROM unidadMedida WHERE nombre = '" + nombre + "'");
        UnidadMedida unidadMedida = new UnidadMedida();
        try {
            while (rs.next()) {
                unidadMedida.setIdUnidadMedidad(Integer.parseInt(rs.getString(1)));
                unidadMedida.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return unidadMedida;
    }

}
