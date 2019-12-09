/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.entidad.DetalleCompra;

/**
 *
 * @author Fekilo
 */
public interface DetalleCompraDao extends Crud<DetalleCompra>{
    public int registrarDetalles(List<DetalleCompra> detalleCompra);
}
