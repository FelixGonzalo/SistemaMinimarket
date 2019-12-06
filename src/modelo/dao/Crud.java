/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fekilo
 * @param <Objeto>
 */

public interface Crud<Objeto> {
    public List<Objeto> leer();
    public Objeto leerId(int id);
    public void registrar(Objeto obj);
    public void actualizar(Objeto obj);
    public void eliminar(int id);
}
