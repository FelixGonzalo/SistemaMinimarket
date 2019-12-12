package modelo.entidad;

public class DetalleVenta {

    private DocumentoVenta documentoVenta;
    private Producto producto;
    private int cantidad;
    private double precioVenta;

    public DetalleVenta(DocumentoVenta documentoVenta, Producto producto, int cantidad, double precioVenta) {
        this.documentoVenta = documentoVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public DetalleVenta() {
    }

    public DocumentoVenta getDocumentoVenta() {
        return documentoVenta;
    }

    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
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
