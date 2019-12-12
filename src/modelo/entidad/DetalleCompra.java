package modelo.entidad;

public class DetalleCompra {

    private DocumentoCompra documentoCompra;
    private Producto producto;
    private int cantidad;
    private double precioVenta;

    public DetalleCompra(DocumentoCompra documentoCompra, Producto producto, int cantidad, double precioVenta) {
        this.documentoCompra = documentoCompra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public DetalleCompra() {
    }

    public DocumentoCompra getDocumentoCompra() {
        return documentoCompra;
    }

    public void setDocumentoCompra(DocumentoCompra documentoCompra) {
        this.documentoCompra = documentoCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

}
