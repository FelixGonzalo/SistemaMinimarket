package modelo.entidad;

public class UnidadMedida {

    private int idUnidadMedidad;
    private String nombre;
    private String abreviatura;

    public UnidadMedida(int idUnidadMedidad, String nombre, String abreviatura) {
        this.idUnidadMedidad = idUnidadMedidad;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public UnidadMedida(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public UnidadMedida() {
    }

    public int getIdUnidadMedidad() {
        return idUnidadMedidad;
    }

    public void setIdUnidadMedidad(int idUnidadMedidad) {
        this.idUnidadMedidad = idUnidadMedidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

}
