/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.dao.ClienteDao;
import modelo.entidad.Cliente;
import modelo.mCliente;

/**
 *
 * @author Fekilo
 */
public class cCliente {

    public static String[] leer(String id) {
        String[] datos = new String[4];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = "";
        }
        try {
            ClienteDao dao = new mCliente();
            int idCliente = Integer.parseInt(id);
            Cliente cliente = dao.leerId(idCliente);
            int condicion = Integer.parseInt(cliente.getIdClienteDniRuc());//permite controlar error de busqueda
            datos[0] = cliente.getIdClienteDniRuc();
            datos[1] = cliente.getNombres();
            datos[2] = cliente.getApellidos();
            datos[3] = Integer.toString(cliente.getSexo());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error controlador Cliente -> leer: \n NO HAY CLIENTE!");
        }
        return datos;
    }

    public static void registrar(String id, String nombres, String apellidos, int sexo) {
        try {
            int verifica = Integer.parseInt(id);//si es numero
            if (!id.equalsIgnoreCase("") && !nombres.equalsIgnoreCase("") && sexo != 0) {
                ClienteDao dao = new mCliente();
                Cliente obj = new Cliente(id, nombres, apellidos, sexo);
                int band = dao.registrar(obj);
                if (band != -1) {
                    JOptionPane.showMessageDialog(null, "Registro de Cliente listo!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error en modelo Cliente -> registrar!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Especifique CODIGO, Nombres y Sexo!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El CODIGO no acepta letras!!");
        }
    }
}
