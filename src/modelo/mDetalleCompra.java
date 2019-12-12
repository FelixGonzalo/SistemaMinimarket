package modelo;

import java.util.List;
import modelo.bd.Transaccion;
import modelo.dao.DetalleCompraDao;
import modelo.entidad.DetalleCompra;

public class mDetalleCompra implements DetalleCompraDao {

    @Override
    public int registrarLista(List<DetalleCompra> detalle) {
        int band = -1;
        if (detalle.size() > 1) {
            for (DetalleCompra objDetalle : detalle) {
                band = Transaccion.actualizacion("INSERT INTO detalleCompra (idDocumentoCompra,idProducto,cantidad,precioCompra) VALUES ('"
                        + objDetalle.getDocumentoCompra().getIdDocumentoCompra() + "','"
                        + objDetalle.getProducto().getIdProducto() + "','"
                        + objDetalle.getCantidad() + "','"
                        + objDetalle.getPrecioVenta() + "')");
            }
        } else {
            band = Transaccion.actualizacion("INSERT INTO detalleCompra (idDocumentoCompra,idProducto,cantidad,precioCompra) VALUES ('"
                    + detalle.get(0).getDocumentoCompra().getIdDocumentoCompra() + "','"
                    + detalle.get(0).getProducto().getIdProducto() + "','"
                    + detalle.get(0).getCantidad() + "','"
                    + detalle.get(0).getPrecioVenta() + "')");
        }
        return band;
    }
}
