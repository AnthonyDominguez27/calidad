/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa3_Dominio;

/**
 *
 * @author Cristhian
 */
public class PacienteMenor extends Paciente {

    private Paciente apoderado;

    public PacienteMenor(Paciente apoderado, String DNI, String Nombre, String Direccion, String Correo, String Telefono, boolean mayorDeEdad, int edad) {
        super(DNI, Nombre, Direccion, Correo, Telefono, edad);
        //mayorDeEdad = false;
        this.apoderado = apoderado;
    }

    public PacienteMenor() {
    }

    public Paciente getApoderado() {
        return apoderado;
    }

    public void setApoderado(Paciente apoderado) {
        this.apoderado = apoderado;
    }
    
    @Override
    public boolean tieneEdadValida(){
        return this.getEdad()>=0&&18>this.getEdad();
    }
    
    public boolean esApoderadoValido(){
        return apoderado.getEdad()>=18;
    }
}
