/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa3_Dominio;

/**
 *
 * @author Cristhian
 */
public class Especialidad {

    private int especialidadID;
    private String especialidad;


    public Especialidad(int especialidadID, String nombre) {
        this.especialidadID = especialidadID;
        this.especialidad = nombre;

    }

    public Especialidad(String especialidad) {
        this.especialidad = especialidad;
        }

       
    public Especialidad() {
    }

    public Especialidad(int especialidadID) {
        this.especialidadID = especialidadID;
    }

    public int getEspecialidadID() {
        return especialidadID;
    }

    public void setEspecialidadID(int especialidadID) {
        this.especialidadID = especialidadID;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    @Override
    public String toString() {
        return "Especialidad{" + "especialidadID=" + especialidadID + ", especialidad=" + especialidad + ", consultorio="+'}';
    }
    
    
}
