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
            //System.out.println("ERROR mProveedor -> leerId: \n" + e.getMessage());
        }
        return cliente;
    }

    @Override
    public int registrar(Proveedor obj) {
        int band = Transaccion.actualizacion("INSERT INTO proveedor (rucProveedor,razonSocial,telefono,celular,correo) VALUES ('"
                + obj.getRucProveedor() + "','"
                + obj.getRazonSocial() + "','"
                + obj.getTelefono() + "','"
                + obj.getCelular() + "','"
                + obj.getCorreo() + "')");
        return band;
    }

    @Override
    public int actualizar(Proveedor obj) {
        int band = Transaccion.actualizacion("UPDATE proveedor SET "
                + " razonSocial ='" + obj.getRazonSocial()+ "',"
                + " correo ='" + obj.getCorreo()+ "',"
                + " telefono ='" + obj.getTelefono()+ "',"
                + " celular = '" + obj.getCelular()+ "'"
                + " WHERE rucProveedor = '" + obj.getRucProveedor()+"'");
        return band;
    }
}
