package controlador;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.dao.CategoriaDao;
import modelo.entidad.Categoria;
import modelo.mCategoria;

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

    public static int registrar(String nombre) {
        int band = -1;
        if (!nombre.equalsIgnoreCase("")) {
            CategoriaDao dao = new mCategoria();
            Categoria obj = new Categoria(nombre);
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
                CategoriaDao dao = new mCategoria();
                Categoria obj = new Categoria(Integer.parseInt(id), nombre);
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
            CategoriaDao dao = new mCategoria();
            band = dao.eliminar(Integer.parseInt(id));
        } else {
            band = 3;
        }
        return band;
    }
}
