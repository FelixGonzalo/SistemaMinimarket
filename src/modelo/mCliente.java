package modelo;

import java.sql.ResultSet;
import modelo.bd.Transaccion;
import modelo.dao.ClienteDao;
import modelo.entidad.Cliente;

public class mCliente implements ClienteDao {

    @Override
    public Cliente leerId(int id) {
        ResultSet rs = Transaccion.consulta("SELECT idClienteDniRuc, nombres, apellidos, sexo, celular, correo, direccion FROM cliente WHERE idClienteDniRuc = '" + id + "'");
        Cliente cliente = new Cliente();
        try {
            while (rs.next()) {
                cliente.setIdClienteDniRuc(rs.getString(1));
                cliente.setNombres(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setSexo(Integer.parseInt(rs.getString(4)));
                cliente.setCelular(rs.getString(5));
                cliente.setCorreo(rs.getString(6));
                cliente.setDireccion(rs.getString(7));
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "ERROR mCliente -> leerId \n" + e.getMessage());
        }
        return cliente;
    }

    @Override
    public int registrar(Cliente obj) {
        int band = Transaccion.actualizacion("INSERT INTO cliente (idClienteDniRuc,nombres,apellidos,sexo,celular,correo,direccion) VALUES ('"
                + obj.getIdClienteDniRuc() + "','"
                + obj.getNombres() + "','"
                + obj.getApellidos() + "','"
                + obj.getSexo() + "','"
                + obj.getCelular() + "','"
                + obj.getCorreo() + "','"
                + obj.getDireccion() + "')");
        return band;
    }

    @Override
    public int actualizar(Cliente obj) {
        int band = Transaccion.actualizacion("UPDATE cliente SET "
                + " nombres ='" + obj.getNombres() + "',"
                + " apellidos ='" + obj.getApellidos() + "',"
                + " sexo = " + obj.getSexo() + ","
                + " celular ='" + obj.getCelular() + "',"
                + " correo = '" + obj.getCorreo() + "',"
                + " direccion = '" + obj.getDireccion()+ "'"
                + " WHERE idClienteDniRuc = '" + obj.getIdClienteDniRuc() + "'");
        return band;
    }
}
