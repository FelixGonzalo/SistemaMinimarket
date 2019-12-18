package controlador;

import java.util.List;
import modelo.dao.DetalleCompraDao;
import modelo.entidad.DetalleCompra;
import modelo.mDetalleCompra;

public class cDetalleCompra {

    public static int registrarDetallesCompra(List<DetalleCompra> lista) {
        DetalleCompraDao detalles = new mDetalleCompra();
        int band = detalles.registrarLista(lista);
        return band;
    }

}
