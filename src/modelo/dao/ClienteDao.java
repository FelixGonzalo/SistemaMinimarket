package modelo.dao;

import modelo.dao.crud.Actualizar;
import modelo.dao.crud.LeerId;
import modelo.dao.crud.Registrar;
import modelo.entidad.Cliente;

public interface ClienteDao extends Registrar<Cliente>, LeerId<Cliente>,Actualizar<Cliente> {

}
