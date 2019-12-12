package modelo.entidad;

/*
    CAMPOS CALCULADOS:
    cantidadProductos -> suma de cantidad de productos
    total             -> la suma de cantidad * precio de productos
 */
public class DocumentoCompra {

    private int idDocumentoCompra;
    private int serie;
    private int numero;
    private String fecha;
    private Proveedor proveedor;
    private String cantidadProductos;
    private String total;

    public DocumentoCompra(int idDocumentoCompra, int serie, int numero, String fecha, Proveedor proveedor) {
        this.idDocumentoCompra = idDocumentoCompra;
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.proveedor = proveedor;
    }

    public DocumentoCompra(int serie, int numero, String fecha, Proveedor proveedor) {
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.proveedor = proveedor;
    }

    public DocumentoCompra() {
    }

    public int getIdDocumentoCompra() {
        return idDocumentoCompra;
    }

    public void setIdDocumentoCompra(int idDocumentoCompra) {
        this.idDocumentoCompra = idDocumentoCompra;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(String cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
