package modelo.dao;

import java.util.List;
import modelo.dao.crud.Actualizar;
import modelo.dao.crud.Eliminar;
import modelo.dao.crud.Leer;
import modelo.dao.crud.LeerFiltro;
import modelo.dao.crud.Registrar;
import modelo.entidad.Producto;

public interface ProductoDao extends Registrar<Producto>, Leer<Producto>, LeerFiltro<Producto>, Actualizar<Producto>, Eliminar<Producto> {

    public List<Producto> leerFiltro(String nombreCategoria, String nombreMarca, String nombreUnidadMedida);
}
