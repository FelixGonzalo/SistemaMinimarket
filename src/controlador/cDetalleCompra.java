/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.dao.DetalleCompraDao;
import modelo.entidad.DetalleCompra;
import modelo.mDetalleCompra;

/**
 *
 * @author Fekilo
 */
public class cDetalleCompra {

    public static int registrarDetallesCompra(List<DetalleCompra> lista) {
        DetalleCompraDao detalles = new mDetalleCompra();
        int band = detalles.registrarLista(lista);
        return band;
    }

}
