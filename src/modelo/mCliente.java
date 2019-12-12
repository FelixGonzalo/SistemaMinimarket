package modelo;

import java.sql.ResultSet;
import modelo.bd.Transaccion;
import modelo.dao.ClienteDao;
import modelo.entidad.Cliente;

public class mCliente implements ClienteDao {

    @Override
    public Cliente leerId(int id) {
        ResultSet rs = Transaccion.consulta("SELECT idClienteDniRuc, nombres, apellidos, sexo FROM cliente WHERE idClienteDniRuc = '" + id + "'");
        Cliente cliente = new Cliente();
        try {
            while (rs.next()) {
                cliente.setIdClienteDniRuc(rs.getString(1));
                cliente.setNombres(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setSexo(Integer.parseInt(rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public int registrar(Cliente obj) {
        try {
            int band = Transaccion.actualizacion("INSERT INTO cliente (idClienteDniRuc,nombres,apellidos,sexo) VALUES ('"
                    + obj.getIdClienteDniRuc() + "','"
                    + obj.getNombres() + "','"
                    + obj.getApellidos() + "','"
                    + obj.getSexo() + "')");
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
