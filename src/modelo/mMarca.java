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
    public int registrar(Marca obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO marca (nombre) VALUES (?)");
            ps.setString(1, obj.getNombre());
            ps.executeUpdate();
            con.commit();
            return 1;
        } catch (Exception e) {
            Transacciones.usarRollback(con);
            return -1;
        }
    }

    @Override
    public int actualizar(Marca obj) {
        try {
            Transacciones.comandos_Update_Delete("UPDATE marca SET "
                    + "nombre='" + obj.getNombre()
                    + "' WHERE idMarca=" + obj.getIdMarca());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int eliminar(int id) {
        try {
            Transacciones.comandos_Update_Delete("DELETE FROM marca WHERE idMarca=" + id);
            return 1;
        } catch (Exception e) {
            return -1;
        }
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
