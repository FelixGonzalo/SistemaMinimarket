/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
            Cliente cliente = dao.leerId(Integer.parseInt(id));
            datos[0] = cliente.getIdClienteDniRuc();
            datos[1] = cliente.getNombres();
            datos[2] = cliente.getApellidos();
            datos[3] = Integer.toString(cliente.getSexo());
        } catch (Exception e) {
            System.out.println(" error leer cCliente");
        }
        return datos;
    }

    public static void registrar(String id, String nombres, String apellidos, int sexo) {
        ClienteDao dao = new mCliente();
        Cliente obj = new Cliente(id, nombres, apellidos, sexo);
        dao.registrar(obj);

    }
;
}
