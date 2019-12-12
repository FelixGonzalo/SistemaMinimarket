package modelo.dao;

import modelo.dao.crud.Leer;
import modelo.dao.crud.LeerUltimoRegistro;
import modelo.dao.crud.Registrar;
import modelo.entidad.DocumentoCompra;

public interface DocumentoCompraDao extends Registrar<DocumentoCompra>, Leer<DocumentoCompra>, LeerUltimoRegistro<DocumentoCompra> {

}
