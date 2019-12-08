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
import modelo.dao.CategoriaDao;
import modelo.entidad.Categoria;

/**
 *
 * @author Fekilo
 */
public class mCategoria implements CategoriaDao {

    @Override
    public List<Categoria> leer() {
        ResultSet rs = Transacciones.consulta("SELECT idCategoria, nombre FROM categoria ORDER BY nombre");
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(Integer.parseInt(rs.getString(1)));
                categoria.setNombre(rs.getString(2));
                lista.add(categoria);
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Categoria leerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int registrar(Categoria obj) {
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO categoria (nombre) VALUES (?)");
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
    public int actualizar(Categoria obj) {
        try {
            Transacciones.comandos_Update_Delete("UPDATE categoria SET "
                    + "nombre='" + obj.getNombre()
                    + "' WHERE idCategoria=" + obj.getIdCategoria());
            return 1;
        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public int eliminar(int id) {
        try {
            Transacciones.comandos_Update_Delete("DELETE FROM categoria WHERE idCategoria=" + id);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Categoria leerNombre(String nombre) {
        ResultSet rs = Transacciones.consulta("SELECT idCategoria, nombre FROM categoria WHERE nombre = '" + nombre + "'");
        Categoria categoria = new Categoria();
        try {
            while (rs.next()) {
                categoria.setIdCategoria(Integer.parseInt(rs.getString(1)));
                categoria.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error en lectura: " + e.getMessage());
        }
        return categoria;
    }

}
