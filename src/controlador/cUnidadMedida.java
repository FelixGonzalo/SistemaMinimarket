/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.UnidadMedidaDao;
import modelo.entidad.UnidadMedida;
import modelo.mUnidadMedida;

/**
 *
 * @author Fekilo
 */
public class cUnidadMedida {

    public static DefaultTableModel leer() {
        UnidadMedidaDao dao = new mUnidadMedida();
        List<UnidadMedida> lista = dao.leer();

        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("Nombre");
        dt.addColumn("Abreviatura");

        for (UnidadMedida obj : lista) {
            Object[] fila = new Object[9];
            fila[0] = obj.getIdUnidadMedidad();
            fila[1] = obj.getNombre();
            fila[2] = obj.getAbreviatura();
            dt.addRow(fila);
        }
        return dt;
    }

    public static DefaultComboBoxModel leerCombo() {
        UnidadMedidaDao dao = new mUnidadMedida();
        List<UnidadMedida> lista = dao.leer();
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        
        for (UnidadMedida obj : lista) {
            combo.addElement(obj.getNombre());
        }
        return combo;
    }
    
    public static void registrar(String nombre, String abreviatura){
        UnidadMedidaDao dao = new mUnidadMedida();
        try {
            UnidadMedida obj = new UnidadMedida(nombre, abreviatura);
            dao.registrar(obj);
        } catch (Exception e) {
        }
    }
    
    public static void actualizar(String id,String nombre, String abreviatura){
        UnidadMedidaDao dao = new mUnidadMedida();
        try {
            UnidadMedida obj = new UnidadMedida(Integer.parseInt(id),nombre, abreviatura);
            dao.actualizar(obj);
        } catch (Exception e) {
        }
    }
    
    public static void eliminar(String id){
        UnidadMedidaDao dao = new mUnidadMedida();
        try {
            dao.eliminar(Integer.parseInt(id));
        } catch (Exception e) {
        }
    }

}
