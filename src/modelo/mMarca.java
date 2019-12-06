/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.bd.Transacciones;
import modelo.dao.MarcaDao;
import modelo.entidad.Marca;

/**
 *
 * @author Fekilo
 */
public class mMarca implements MarcaDao {

    @Override
    public List<Marca> leer() {
        ResultSet rs = Transacciones.consulta("SELECT idMarca, nombre FROM marca ORDER BY nombre");
        List<Marca> lista = new ArrayList<Marca>();
        try {
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(Integer.parseInt(rs.getString(1)));
                marca.setNombre(rs.getString(2));
                lista.add(marca);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Marca leerId(int id) {
        ResultSet rs = Transacciones.consulta("SELECT idMarca, nombre FROM marca WHERE idMarca = " + id);
        Marca marca = new Marca();
        try {
            marca.setIdMarca(Integer.parseInt(rs.getString(1)));
            marca.setNombre(rs.getString(2));
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return marca;
    }

    @Override
    public void registrar(Marca obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO marca (nombre) VALUES (?)");
            ps.setString(1, obj.getNombre());
            ps.executeUpdate();
            con.commit();
            System.out.println("listo");
        } catch (Exception e) {
            System.out.println("error modelo: " + e.getMessage());
            Transacciones.usarRollback(con);
        }
    }

    @Override
    public void actualizar(Marca obj) {
        Transacciones.comandos_Update_Delete("UPDATE marca SET "
                + "nombre='" + obj.getNombre()
                + "' WHERE idMarca=" + obj.getIdMarca());
    }

    @Override
    public void eliminar(int id) {
        Transacciones.comandos_Update_Delete("DELETE FROM marca WHERE idMarca=" + id);
    }

    @Override
    public Marca leerNombre(String nombre) {
        ResultSet rs = Transacciones.consulta("SELECT idMarca, nombre FROM marca WHERE nombre = '" + nombre + "'");
        Marca marca = new Marca();
        try {
            while (rs.next()) {
                marca.setIdMarca(Integer.parseInt(rs.getString(1)));
                marca.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return marca;
    }

}
