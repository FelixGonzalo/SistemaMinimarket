/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.entidad.UnidadMedida;

/**
 *
 * @author Fekilo
 */
public interface UnidadMedidaDao extends Crud<UnidadMedida>{
    public UnidadMedida leerNombre(String nombre);
}
