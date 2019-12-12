package modelo.dao;

import modelo.dao.crud.Actualizar;
import modelo.dao.crud.Registrar;
import modelo.dao.crud.Eliminar;
import modelo.dao.crud.Leer;
import modelo.dao.crud.LeerDescripcion;
import modelo.entidad.UnidadMedida;

public interface UnidadMedidaDao extends Registrar<UnidadMedida>, Leer<UnidadMedida>, LeerDescripcion<UnidadMedida>, Actualizar<UnidadMedida>, Eliminar<UnidadMedida> {

}
