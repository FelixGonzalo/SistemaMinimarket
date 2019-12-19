package controlador;

import javax.swing.JOptionPane;
import modelo.dao.ClienteDao;
import modelo.entidad.Cliente;
import modelo.mCliente;

public class cCliente {

    public static String[] leerId(String id) {
        String[] datos = new String[7];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = "";
        }
        try {
            ClienteDao dao = new mCliente();
            int idCliente = Integer.parseInt(id);
            Cliente cliente = dao.leerId(idCliente);
            datos[0] = cliente.getIdClienteDniRuc();
            datos[1] = cliente.getNombres();
            datos[2] = cliente.getApellidos();
            datos[3] = Integer.toString(cliente.getSexo());
            datos[4] = cliente.getCelular();
            datos[5] = cliente.getCorreo();
            datos[6] = cliente.getDireccion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR cCliente -> leerId: \n NO HAY CLIENTE!");
        }
        return datos;
    }

    public static int registrar(String id, String nombres, String apellidos, int sexo, String celular, String correo,String direccion) {
        int band = -1;
        if ((Controlador.isNumeric(id) && celular.equalsIgnoreCase("")) || (Controlador.isNumeric(id) && Controlador.isNumeric(celular))) {
            if (!id.equalsIgnoreCase("") && !nombres.equalsIgnoreCase("") && !apellidos.equalsIgnoreCase("") && sexo != 0) {
                ClienteDao dao = new mCliente();
                Cliente obj = new Cliente(id, nombres, apellidos, sexo, celular, correo, direccion);
                band = dao.registrar(obj);
            } else {
                band = 2;
            }
        } else {
            band = 3;
        }
        return band;
    }

    public static int actualizar(String id, String nombres, String apellidos, int sexo, String celular, String correo,String direccion) {
        int band = -1;
        if ((Controlador.isNumeric(id) && celular.equalsIgnoreCase("")) || (Controlador.isNumeric(id) && Controlador.isNumeric(celular))) {
            if (!id.equalsIgnoreCase("") && !nombres.equalsIgnoreCase("") && !apellidos.equalsIgnoreCase("") && sexo != 0) {
                ClienteDao dao = new mCliente();
                Cliente obj = new Cliente(id, nombres, apellidos, sexo, celular, correo, direccion);
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
