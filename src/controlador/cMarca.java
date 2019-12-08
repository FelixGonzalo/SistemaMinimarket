/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.MarcaDao;
import modelo.entidad.Marca;
import modelo.mMarca;

/**
 *
 * @author Fekilo
 */
public class cMarca {

    public static DefaultTableModel leer() {
        MarcaDao dao = new mMarca();
        List<Marca> lista = dao.leer();

        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("Nombre");

        for (Marca obj : lista) {
            Object[] fila = new Object[2];
            fila[0] = obj.getIdMarca();
            fila[1] = obj.getNombre();
            dt.addRow(fila);
        }
        return dt;
    }

    public static DefaultComboBoxModel leerCombo() {
        MarcaDao dao = new mMarca();
        List<Marca> lista = dao.leer();
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        for (Marca obj : lista) {
            combo.addElement(obj.getNombre());
        }
        return combo;
    }
    
     public static void registrar(String nombre){
        MarcaDao dao = new mMarca();
        try {
            Marca obj = new Marca(nombre);
            dao.registrar(obj);
        } catch (Exception e) {
        }
    }
    
    public static void actualizar(String id,String nombre){
        MarcaDao dao = new mMarca();
        try {
            Marca obj = new Marca(Integer.parseInt(id),nombre);
            dao.actualizar(obj);
        } catch (Exception e) {
        }
    }
    
    public static void eliminar(String id){
        MarcaDao dao = new mMarca();
        try {
            dao.eliminar(Integer.parseInt(id));
        } catch (Exception e) {
        }
    }
}
