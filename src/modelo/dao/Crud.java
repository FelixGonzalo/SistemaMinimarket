/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;

/**
 *
 * @author Fekilo
 * @param <Objeto>
 */

public interface Crud<Objeto> {
    public List<Objeto> leer();
    public Objeto leerId(int id);
    public int registrar(Objeto obj);
    public int actualizar(Objeto obj);
    public int eliminar(int id);
}
