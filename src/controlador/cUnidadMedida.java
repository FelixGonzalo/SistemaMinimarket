package controlador;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.UnidadMedidaDao;
import modelo.entidad.UnidadMedida;
import modelo.mUnidadMedida;

public class cUnidadMedida {

    public static DefaultTableModel leer() {
        UnidadMedidaDao dao = new mUnidadMedida();
        List<UnidadMedida> lista = dao.leer();

        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("Nombre");
        dt.addColumn("Abreviatura");

        for (UnidadMedida obj : lista) {
            Object[] fila = new Object[3];
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

    public static int registrar(String nombre, String abreviatura) {
        int band = -1;
        if (!nombre.equalsIgnoreCase("") || !abreviatura.equalsIgnoreCase("")) {
            UnidadMedidaDao dao = new mUnidadMedida();
            UnidadMedida obj = new UnidadMedida(nombre, abreviatura);
            band = dao.registrar(obj);
        } else {
            band = 2;
        }
        return band;
    }

    public static int actualizar(String id, String nombre, String abreviatura) {
        int band = -1;
        if (!nombre.equalsIgnoreCase("") && !abreviatura.equalsIgnoreCase("")) {
            if (Controlador.isNumeric(id)) {
                UnidadMedidaDao dao = new mUnidadMedida();
                UnidadMedida obj = new UnidadMedida(Integer.parseInt(id), nombre, abreviatura);
                band = dao.actualizar(obj);
            } else {
                band = 3;
            }
        } else {
            band = 2;
        }
        return band;
    }

    public static int eliminar(String id) {
        int band = -1;
        if (Controlador.isNumeric(id)) {
            UnidadMedidaDao dao = new mUnidadMedida();
            band = dao.eliminar(Integer.parseInt(id));
        } else {
            band = 3;
        }
        return band;
    }

}
