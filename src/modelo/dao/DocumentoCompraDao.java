/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.entidad.DocumentoCompra;

/**
 *
 * @author Fekilo
 */
public interface DocumentoCompraDao extends Crud<DocumentoCompra>{
    public int obtenerIDUltimoRegistro();
}
