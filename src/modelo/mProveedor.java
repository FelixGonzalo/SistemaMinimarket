package modelo;

import java.sql.ResultSet;
import modelo.bd.Transaccion;
import modelo.dao.ProveedorDao;
import modelo.entidad.Proveedor;

public class mProveedor implements ProveedorDao {

    @Override
    public Proveedor leerId(int id) {
        ResultSet rs = Transaccion.consulta("SELECT rucProveedor, razonSocial, telefono, celular, correo FROM proveedor WHERE rucProveedor = '" + id + "'");
        Proveedor cliente = new Proveedor();
        try {
            while (rs.next()) {
                cliente.setRucProveedor(rs.getString(1));
                cliente.setRazonSocial(rs.getString(2));
                cliente.setTelefono(rs.getString(3));
                cliente.setCelular(rs.getString(4));
                cliente.setCorreo(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public int registrar(Proveedor obj) {
        try {
            int band = Transaccion.actualizacion("INSERT INTO proveedor (rucProveedor,razonSocial,telefono,celular,correo) VALUES ('"
                    + obj.getRucProveedor() + "','"
                    + obj.getRazonSocial() + "','"
                    + obj.getTelefono() + "','"
                    + obj.getCelular() + "','"
                    + obj.getCorreo() + "')");
            if (band != -1) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}
