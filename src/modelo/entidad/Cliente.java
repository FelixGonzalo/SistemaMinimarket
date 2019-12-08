/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidad;

/**
 *
 * @author Fekilo
 */
public class Cliente {

    private String idClienteDniRuc;
    private String nombres;
    private String apellidos;
    private int sexo;

    public Cliente(String idClienteDniRuc, String nombres, String apellidos, int sexo) {
        this.idClienteDniRuc = idClienteDniRuc;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    public Cliente(String nombres, String apellidos, int sexo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    public Cliente() {
    }

    public String getIdClienteDniRuc() {
        return idClienteDniRuc;
    }

    public void setIdClienteDniRuc(String idClienteDniRuc) {
        this.idClienteDniRuc = idClienteDniRuc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

}
