/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.dao.ProveedorDao;
import modelo.entidad.Proveedor;
import modelo.mProveedor;

/**
 *
 * @author Fekilo
 */
public class cProveedor {
    public static String[] leer(String id) {
        String[] datos = new String[5];
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error controlador Proveedor -> leer: \n NO HAY CLIENTE!");
        }
        return datos;
    }

    public static void registrar(String id, String razonSocial, String telefono, String celular, String correo) {
        try {
            int verifica = Integer.parseInt(id);//si es numero
            if (!id.equalsIgnoreCase("") && !razonSocial.equalsIgnoreCase("")) {
                ProveedorDao dao = new mProveedor();
                Proveedor obj = new Proveedor(id, razonSocial, telefono, celular, correo);
                int band = dao.registrar(obj);
                if (band != -1) {
                    JOptionPane.showMessageDialog(null, "Registro de Proveedor listo!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error en modelo Proveedor -> registrar!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Especifique CODIGO y Razon Social!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El CODIGO no acepta letras!!");
        }
    }
    
    public static void actualizar(String id, String razonSocial, String telefono, String celular, String correo) {
        try {
            int verifica = Integer.parseInt(id);//si es numero
            if (!id.equalsIgnoreCase("") && !razonSocial.equalsIgnoreCase("") && !telefono.equalsIgnoreCase("") && !celular.equalsIgnoreCase("") && !correo.equalsIgnoreCase("") ) {
                ProveedorDao dao = new mProveedor();
                Proveedor obj = new Proveedor(id, razonSocial, telefono, celular, correo);
                int band = dao.actualizar(obj);
                if (band != -1) {
                    JOptionPane.showMessageDialog(null, "Actualización de Proveedor listo!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Especifique los datos!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El CODIGO no acepta letras!!");
        }
    }
}
