/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.CategoriaDao;
import modelo.entidad.Categoria;
import modelo.mCategoria;

/**
 *
 * @author Fekilo
 */
public class cCategoria {

    public static DefaultTableModel leer() {
        CategoriaDao dao = new mCategoria();
        List<Categoria> lista = dao.leer();

        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("Nombre");

        for (Categoria obj : lista) {
            Object[] fila = new Object[2];
            fila[0] = obj.getIdCategoria();
            fila[1] = obj.getNombre();
            dt.addRow(fila);
        }
        return dt;
    }

    public static DefaultComboBoxModel leerCombo() {
        CategoriaDao dao = new mCategoria();
        List<Categoria> lista = dao.leer();
        DefaultComboBoxModel combo = new DefaultComboBoxModel();

        for (Categoria obj : lista) {
            combo.addElement(obj.getNombre());
        }
        return combo;
    }
    
    public static void registrar(String nombre){
        CategoriaDao dao = new mCategoria();
        try {
            Categoria obj = new Categoria(nombre);
            dao.registrar(obj);
        } catch (Exception e) {
        }
    }
    
    public static void actualizar(String id,String nombre){
        CategoriaDao dao = new mCategoria();
        try {
            Categoria obj = new Categoria(Integer.parseInt(id),nombre);
            dao.actualizar(obj);
        } catch (Exception e) {
        }
    }
    
    public static void eliminar(String id){
        CategoriaDao dao = new mCategoria();
        try {
            dao.eliminar(Integer.parseInt(id));
        } catch (Exception e) {
        }
    }
}
