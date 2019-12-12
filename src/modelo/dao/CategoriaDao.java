package modelo.dao;

import modelo.dao.crud.Actualizar;
import modelo.dao.crud.Registrar;
import modelo.dao.crud.Eliminar;
import modelo.dao.crud.Leer;
import modelo.dao.crud.LeerDescripcion;
import modelo.entidad.Categoria;

public interface CategoriaDao extends Registrar<Categoria>, Leer<Categoria>, LeerDescripcion<Categoria>, Actualizar<Categoria>, Eliminar<Categoria> {

}
