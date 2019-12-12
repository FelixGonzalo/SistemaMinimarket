package modelo.entidad;

/*
    CAMPOS CALCULADOS:
    cantidadProductos -> suma de cantidad de productos
    subtotal          -> total / 1.18
    montoIGV          -> subTotal * 0.18
    total             -> la suma de cantidad * precio de productos
 */
public class DocumentoVenta {

    private int idDocumentoVenta;
    private int serie;
    private int numero;
    private String fecha;
    private double igv;
    private Cliente cliente;
    private String cantidadProductos;
    private String subtotal;
    private String montoIGV;
    private String total;

    public DocumentoVenta(int idDocumentoVenta, int serie, int numero, String fecha, double igv, Cliente cliente) {
        this.idDocumentoVenta = idDocumentoVenta;
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.igv = igv;
        this.cliente = cliente;
    }

    public DocumentoVenta(int serie, int numero, String fecha, double igv, Cliente cliente) {
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.igv = igv;
        this.cliente = cliente;
    }

    public DocumentoVenta() {
    }

    public int getIdDocumentoVenta() {
        return idDocumentoVenta;
    }

    public void setIdDocumentoVenta(int idDocumentoVenta) {
        this.idDocumentoVenta = idDocumentoVenta;
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

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //campos calculados
    public String getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(String cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getMontoIGV() {
        return montoIGV;
    }

    public void setMontoIGV(String montoIGV) {
        this.montoIGV = montoIGV;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
