package controlador;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.MarcaDao;
import modelo.entidad.Marca;
import modelo.mMarca;

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

    public static int registrar(String nombre) {
        int band = -1;
        if (!nombre.equalsIgnoreCase("")) {
            MarcaDao dao = new mMarca();
            Marca obj = new Marca(nombre);
            band = dao.registrar(obj);
        } else {
            band = 2;
        }
        return band;
    }

    public static int actualizar(String id, String nombre) {
        int band = -1;
        if (!nombre.equalsIgnoreCase("")) {
            if (Controlador.isNumeric(id)) {
                MarcaDao dao = new mMarca();
                Marca obj = new Marca(Integer.parseInt(id), nombre);
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
            MarcaDao dao = new mMarca();
            band = dao.eliminar(Integer.parseInt(id));
        } else {
            band = 3;
        }
        return band;
    }
}
