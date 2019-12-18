package controlador;

import java.util.List;
import modelo.dao.DetalleVentaDao;
import modelo.entidad.DetalleVenta;
import modelo.mDetalleVenta;

public class cDetalleVenta {

    public static int registrarDetallesVenta(List<DetalleVenta> lista) {
        DetalleVentaDao detalles = new mDetalleVenta();
        int band = detalles.registrarLista(lista);
        return band;
    }
}
