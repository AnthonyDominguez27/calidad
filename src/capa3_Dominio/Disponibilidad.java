/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa3_Dominio;

import java.util.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Cristhian
 */
public class Disponibilidad {

    private int disponibilidadID;
    private LocalDateTime fechaHora;
    private boolean estado;
    private Especialidad especialidad;
    private Medico medico;

    private Consultorio consultorio;

    public Disponibilidad(int disponibilidadID, LocalDateTime fechaHora, boolean estado, Especialidad especialidad, Medico medico, Consultorio consultorio) {
        this.disponibilidadID = disponibilidadID;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.especialidad = especialidad;
        this.medico = medico;
        this.consultorio = consultorio;
    }



    public Disponibilidad(LocalDateTime fechaHora, boolean estado, Especialidad especialidad, Medico medico, Consultorio consultorio) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.especialidad = especialidad;
        this.medico = medico;
        this.consultorio=consultorio;
    }

   

    
    public Disponibilidad(int disponibilidadID) {
        this.disponibilidadID = disponibilidadID;
    }

    public Disponibilidad() {
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDisponibilidadID() {
        return disponibilidadID;
    }

    public void setDisponibilidadID(int disponibilidadID) {
        this.disponibilidadID = disponibilidadID;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public boolean esFechaValida(){
        int diaDeLaSemana = fechaHora.getDayOfWeek().getValue();
        return diaDeLaSemana >= 2 && diaDeLaSemana <= 7;
    }
    
    public boolean esHoraValida() {
        int hora = fechaHora.getHour();
        int minuto = fechaHora.getMinute();
        boolean rango1 = (hora >= 7 && hora <= 12);
        boolean rango2 = (hora >= 15 && hora <= 19);
        boolean horaEnPunto = (minuto == 0);
        return (rango1 || rango2)&&horaEnPunto;
    }
    
    public boolean esDisponibilidadValida(){
        return esFechaValida()&&esHoraValida();
    } 
    
    @Override
    public String toString() {
        return "Disponibilidad{" + "disponibilidadID=" + disponibilidadID +" fecha hora "+ fechaHora + ", especialidad=" + especialidad.getEspecialidad() + ", medico=" + medico.getNombre() + '}';
    }

}
