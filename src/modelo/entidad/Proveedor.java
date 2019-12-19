package modelo.entidad;

public class Proveedor {

    private String rucProveedor;
    private String razonSocial;
    private String telefono;
    private String celular;
    private String correo;
    private String direccion;

    public Proveedor(String rucProveedor, String razonSocial, String telefono, String celular, String correo, String direccion) {
        this.rucProveedor = rucProveedor;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Proveedor() {
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
