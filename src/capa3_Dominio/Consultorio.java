/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa3_Dominio;

/**
 *
 * @author Cristhian
 */
public class Consultorio {

    private int ConsultorioID;
    private String consultorio;
    private String direccion;
    private String descripcion;

    public Consultorio(int ConsultorioID, String Nombre, String Direccion, String Descripcion) {
        this.ConsultorioID = ConsultorioID;
        this.consultorio = Nombre;
        this.direccion = Direccion;
        this.descripcion = Descripcion;
    }

    public Consultorio(String consultorio, String direccion, String descripcion) {
        this.consultorio = consultorio;
        this.direccion = direccion;
        this.descripcion = descripcion;
    }
    

    public Consultorio(int ConsultorioID) {
        this.ConsultorioID = ConsultorioID;
    }

    public Consultorio() {
    }

    public int getConsultorioID() {
        return ConsultorioID;
    }

    public void setConsultorioID(int ConsultorioID) {
        this.ConsultorioID = ConsultorioID;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String Nombre) {
        this.consultorio = Nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return "Consultorio{" + "ConsultorioID=" + ConsultorioID + ", Nombre=" + consultorio + ", Direccion=" + direccion + ", Descripcion=" + descripcion + '}';
    }

}
