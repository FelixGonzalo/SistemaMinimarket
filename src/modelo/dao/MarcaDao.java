/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.entidad.Marca;

/**
 *
 * @author Fekilo
 */
public interface MarcaDao extends Crud<Marca>{
    public Marca leerNombre(String nombre);
}
