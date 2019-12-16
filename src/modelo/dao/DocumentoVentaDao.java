package modelo.dao;

import modelo.dao.crud.Leer;
import modelo.dao.crud.LeerUltimoRegistro;
import modelo.dao.crud.Registrar;
import modelo.entidad.DocumentoVenta;

public interface DocumentoVentaDao extends Registrar<DocumentoVenta>, Leer<DocumentoVenta>, LeerUltimoRegistro<DocumentoVenta> {
    public int leerSerieDoc();
    public int leerNumeroDoc();
}
