package modelo.entidad;

public class Producto {

    private int idProducto;
    private String descripcion;
    private double precioVenta;
    private String codigoBarras;
    private int cantidadAlmacen;
    private int cantidadMostrador;
    private Categoria categoria;
    private Marca marca;
    private UnidadMedida unidadMedida;

    public Producto(int idProducto, String descripcion, double precioVenta, String codigoBarras, int cantidadAlmacen, int cantidadMostrador, Categoria categoria, Marca marca, UnidadMedida unidadMedida) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.codigoBarras = codigoBarras;
        this.cantidadAlmacen = cantidadAlmacen;
        this.cantidadMostrador = cantidadMostrador;
        this.categoria = categoria;
        this.marca = marca;
        this.unidadMedida = unidadMedida;
    }

    public Producto(String descripcion, double precioVenta, String codigoBarras, int cantidadAlmacen, int cantidadMostrador, Categoria categoria, Marca marca, UnidadMedida unidadMedida) {
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.codigoBarras = codigoBarras;
        this.cantidadAlmacen = cantidadAlmacen;
        this.cantidadMostrador = cantidadMostrador;
        this.categoria = categoria;
        this.marca = marca;
        this.unidadMedida = unidadMedida;
    }

    public Producto(int idProducto, String descripcion, double precioVenta, String codigoBarras, Categoria categoria, Marca marca, UnidadMedida unidadMedida) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.codigoBarras = codigoBarras;
        this.categoria = categoria;
        this.marca = marca;
        this.unidadMedida = unidadMedida;
    }

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getCantidadAlmacen() {
        return cantidadAlmacen;
    }

    public void setCantidadAlmacen(int cantidadAlmacen) {
        this.cantidadAlmacen = cantidadAlmacen;
    }

    public int getCantidadMostrador() {
        return cantidadMostrador;
    }

    public void setCantidadMostrador(int cantidadMostrador) {
        this.cantidadMostrador = cantidadMostrador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}
