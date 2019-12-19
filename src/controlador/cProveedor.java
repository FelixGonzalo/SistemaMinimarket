package controlador;

import javax.swing.JOptionPane;
import modelo.dao.ProveedorDao;
import modelo.entidad.Proveedor;
import modelo.mProveedor;

public class cProveedor {

    public static String[] leer(String id) {
        String[] datos = new String[6];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = "";
        }
        try {
            ProveedorDao dao = new mProveedor();
            int idProveedor = Integer.parseInt(id);
            Proveedor proveedor = dao.leerId(idProveedor);
            int condicion = Integer.parseInt(proveedor.getRucProveedor());//permite controlar error de busqueda
            datos[0] = proveedor.getRucProveedor();
            datos[1] = proveedor.getRazonSocial();
            datos[2] = proveedor.getTelefono();
            datos[3] = proveedor.getCelular();
            datos[4] = proveedor.getCorreo();
            datos[5] = proveedor.getDireccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error cProveedor -> leer: \n NO HAY PROVEEDOR!");
        }
        return datos;
    }

    public static int registrar(String id, String razonSocial, String telefono, String celular, String correo, String direccion) {
        int band = -1;
        if (Controlador.isNumeric(id)) {
            if (!id.equalsIgnoreCase("") && !razonSocial.equalsIgnoreCase("") && !telefono.equalsIgnoreCase("")) {
                ProveedorDao dao = new mProveedor();
                Proveedor obj = new Proveedor(id, razonSocial, telefono, celular, correo, direccion);
                band = dao.registrar(obj);
            } else {
                band = 2;
            }
        } else {
            band = 3;
        }
        return band;
    }

    public static int actualizar(String id, String razonSocial, String telefono, String celular, String correo, String direccion) {
        int band = -1;
        if (Controlador.isNumeric(id)) {
            if (!id.equalsIgnoreCase("") && !razonSocial.equalsIgnoreCase("") && !telefono.equalsIgnoreCase("")) {
                ProveedorDao dao = new mProveedor();
                Proveedor obj = new Proveedor(id, razonSocial, telefono, celular, correo, direccion);
                band = dao.actualizar(obj);
            } else {
                band = 2;
            }
        } else {
            band = 3;
        }
        return band;

    }
}
