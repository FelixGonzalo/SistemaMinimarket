/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.dao.DetalleVentaDao;
import modelo.entidad.DetalleVenta;
import modelo.mDetalleVenta;

/**
 *
 * @author Fekilo
 */
public class cDetalleVenta {

    public static int registrarDetallesVenta(List<DetalleVenta> lista) {
        DetalleVentaDao detalles = new mDetalleVenta();
        int band = detalles.registrarLista(lista);
        return band;
    }
}
