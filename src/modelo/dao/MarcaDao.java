package modelo.dao;

import modelo.dao.crud.Actualizar;
import modelo.dao.crud.Registrar;
import modelo.dao.crud.Eliminar;
import modelo.dao.crud.Leer;
import modelo.dao.crud.LeerDescripcion;
import modelo.entidad.Marca;

public interface MarcaDao extends Registrar<Marca>, Leer<Marca>, LeerDescripcion<Marca>, Actualizar<Marca>, Eliminar<Marca> {

}
