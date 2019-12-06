/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.entidad.Categoria;

/**
 *
 * @author Fekilo
 */
public interface CategoriaDao extends Crud<Categoria>{
    public Categoria leerNombre(String nombre);
}
