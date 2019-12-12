package modelo.dao.crud;

import java.util.List;

public interface LeerFiltro<Objeto> {

    public List<Objeto> leerFiltro(String descripcion);
}
