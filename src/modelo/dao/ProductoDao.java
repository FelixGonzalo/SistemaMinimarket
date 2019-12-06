/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.entidad.Producto;

/**
 *
 * @author Fekilo
 */
public interface ProductoDao extends Crud<Producto>{
    public List<Producto> leerFiltro(String nombreCategoria,String nombreMarca,String nombreUnidadMedida);
    public List<Producto> leerDescripcion(String descripcion);
}
