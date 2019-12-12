package modelo;

import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.DetalleVentaDao;
import modelo.entidad.DetalleVenta;

public class mDetalleVenta implements DetalleVentaDao {

    @Override
    public int registrarLista(List<DetalleVenta> detalle) {
        int band = -1;
        if (detalle.size() > 1) {
            for (DetalleVenta objDetalle : detalle) {
                band = Transaccion.actualizacion("INSERT INTO detalleVenta (idDocumentoVenta,idProducto,cantidad,precioVenta) VALUES ('"
                        + objDetalle.getDocumentoVenta().getIdDocumentoVenta() + "','"
                        + objDetalle.getProducto().getIdProducto() + "','"
                        + objDetalle.getCantidad() + "','"
                        + objDetalle.getPrecioVenta() + "')");
            }
        } else {
            band = Transaccion.actualizacion("INSERT INTO detalleVenta (idDocumentoVenta,idProducto,cantidad,precioVenta) VALUES ('"
                    + detalle.get(0).getDocumentoVenta().getIdDocumentoVenta() + "','"
                    + detalle.get(0).getProducto().getIdProducto() + "','"
                    + detalle.get(0).getCantidad() + "','"
                    + detalle.get(0).getPrecioVenta() + "')");
        }
        return band;
    }
}
