package modelo.dao;

import modelo.dao.crud.Actualizar;
import modelo.dao.crud.LeerId;
import modelo.dao.crud.Registrar;
import modelo.entidad.Proveedor;

public interface ProveedorDao extends Registrar<Proveedor>, LeerId<Proveedor>, Actualizar<Proveedor> {

}
