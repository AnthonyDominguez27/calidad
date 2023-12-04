/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa3_Dominio;

import java.util.Objects;

/**
 *
 * @author Cristhian
 */
public class Medico {

    private int medicoID;
    private String telefono;
    private String nombre;
    private String numeroColegiado;

    public Medico(int MedicoID, String Telefono, String Nombre, String NumeroColegiado) {
        this.medicoID = MedicoID;
        this.telefono = Telefono;
        this.nombre = Nombre;
        this.numeroColegiado = NumeroColegiado;
    }

    public Medico(String telefono, String nombre, String numeroColegiado) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.numeroColegiado = numeroColegiado;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.numeroColegiado, other.numeroColegiado);
    }
    

    public Medico(int MedicoID) {
        this.medicoID = MedicoID;
    }

    public Medico() {
    }

    public int getMedicoID() {
        return medicoID;
    }

    public void setMedicoID(int MedicoID) {
        this.medicoID = MedicoID;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String Telefono) {
        this.telefono = Telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String NumeroColegiado) {
        this.numeroColegiado = NumeroColegiado;
    }
    
    public boolean tieneNumeroColegiadoValido() {
        return numeroColegiado.strip().matches("\\d+") && numeroColegiado.length() == 8;
    }

    public boolean tieneTelefonoValido() {
        return telefono.strip().matches("\\d+") && telefono.length() == 9;
    }
    
    public boolean esMedicoValido(){
        return tieneNumeroColegiadoValido()&&tieneTelefonoValido();
    }

    @Override
    public String toString() {
        return "Medico{" + "medicoID=" + medicoID + ", telefono=" + telefono + ", nombre=" + nombre + ", numeroColegiado=" + numeroColegiado + '}';
    }
    
    
}
