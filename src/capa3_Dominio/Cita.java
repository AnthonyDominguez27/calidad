/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa3_Dominio;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Cristhian
 */
public class Cita {

    private int citaID;
    private Paciente paciente;
    private Disponibilidad disponibilidad;
    private String asunto;
    private String observaciones;
    private String estado;
    private BigDecimal costo;

    public Cita(int CitaID, Paciente paciente, Disponibilidad disponibilidad, String Asunto, String Estado, BigDecimal costo) {
        this.citaID = CitaID;
        this.paciente = paciente;
        this.disponibilidad = disponibilidad;
        this.asunto = Asunto;
        this.estado = Estado;
        this.costo = costo;
    }

    public Cita(int CitaID) {
        this.citaID = CitaID;
    }

    public Cita(Paciente paciente, Disponibilidad disponibilidad, String asunto, String observaciones, String estado, BigDecimal costo) {
        this.paciente = paciente;
        this.disponibilidad = disponibilidad;
        this.asunto = asunto;
        this.observaciones = observaciones;
        this.estado = estado;
        this.costo = costo;
    }

    public Cita(Paciente paciente, Disponibilidad disponibilidad, String asunto, String estado, BigDecimal costo) {
        this.paciente = paciente;
        this.disponibilidad = disponibilidad;
        this.asunto = asunto;
        this.estado = estado;
        this.costo = costo;
    }

    public Cita(Paciente paciente, Disponibilidad disponibilidad, String asunto, BigDecimal costo) {
        this.paciente = paciente;
        this.disponibilidad = disponibilidad;
        this.asunto = asunto;
        this.costo = costo;
    }

    public Cita() {
    }

    public int getCitaID() {
        return citaID;
    }

    public void setCitaID(int CitaID) {
        this.citaID = CitaID;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String Asunto) {
        this.asunto = Asunto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.observaciones = Observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String Estado) {
        this.estado = Estado;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public boolean tieneAsuntoValido() throws IllegalArgumentException {
        if (asunto != null) {
            if (asunto.length() >= 15 && asunto.length() <= 50) {
                String patron = "^[A-Za-zÁáÉéÍíÓóÚúÜüÇçÑñ0-9.,\\-\\s]+$";
                if (!asunto.matches(patron)) {
                    throw new IllegalArgumentException("El asunto contiene caracteres no válidos.");
                }
                return true;
            }
        }
        throw new IllegalArgumentException("El asunto no cumple con la longitud requerida.");
    }


    public boolean tieneObservacionesValidas() {
        if (observaciones != null) {
            if (observaciones.length() >= 15 && observaciones.length() <= 50) {

                String patron = "^[A-Za-zÁáÉéÍíÓóÚúÜüÇçÑñ0-9.,\\-\\s]+$";
                return observaciones.matches(patron);
            }
        }
        return false;
    }

    public boolean tieneEstadoValido() throws IllegalArgumentException {
        if (estado != null) {
            if (estado.equalsIgnoreCase("ATENDIDO") || estado.equalsIgnoreCase("PROGRAMADO") || estado.equalsIgnoreCase("CANCELADO")) {
                return true;
            } else {
                throw new IllegalArgumentException("El estado no es válido. Debe ser 'ATENDIDO', 'PROGRAMADO' o 'CANCELADO'.");
            }
        }
        throw new IllegalArgumentException("El estado no puede ser nulo.");
    }


    public boolean tieneCostoValido() throws IllegalArgumentException {
        BigDecimal minimo = new BigDecimal("40");
        BigDecimal maximo = new BigDecimal("150");

        if (costo == null || costo.compareTo(minimo) < 0 || costo.compareTo(maximo) > 0) {
            // Si costo es nulo o está por debajo de 40 o por encima de 150, lanza una excepción.
            throw new IllegalArgumentException("Monto invalido");
        }

        // Si costo está dentro del rango, la función devuelve verdadero.
        return true;
    }


    public void llenarObservaciones(String observaciones) {
        if (estado.equalsIgnoreCase("CANCELADO") || estado.equalsIgnoreCase("ATENDIDO")) {
            this.observaciones = observaciones;
        }
    }

    public boolean esPacienteValido() throws IllegalArgumentException {
        if (this.paciente == null||!this.paciente.esPacienteValido()) {
            throw new IllegalArgumentException("El paciente no valido.");
        }
        return true;
    }

    public boolean esDisponibilidadValida() throws IllegalArgumentException {
        if (this.disponibilidad == null) {
            throw new IllegalArgumentException("La disponibilidad no puede ser nula.");
        }
        return true;
    }

    public boolean esCitaValida() {
        List<String> errores = new ArrayList<>();

        try {
            if (!tieneCostoValido()) {
                errores.add("Monto invalido");
            }
        } catch (IllegalArgumentException e) {
            errores.add(e.getMessage());
        }

        try {
            if (!tieneEstadoValido()) {
                errores.add("El estado no es válido.");
            }
        } catch (IllegalArgumentException e) {
            errores.add(e.getMessage());
        }

        try {
            if (!tieneAsuntoValido()) {
                errores.add("Asunto inválido.");
            }
        } catch (IllegalArgumentException e) {
            errores.add(e.getMessage());
        }

        try {
            if (!esPacienteValido()) {
                errores.add("Paciente no valido.");
            }
        } catch (IllegalArgumentException e) {
            errores.add(e.getMessage());
        }

        try {
            if (!esDisponibilidadValida()) {
                errores.add("Disponibilidad no válida.");
            }
        } catch (IllegalArgumentException e) {
            errores.add(e.getMessage());
        }

        if (errores.isEmpty()) {
            return true;
        } else {
            System.out.println(errores);
            throw new IllegalArgumentException("Error en la cita: " + errores);

        }

        }



    @Override
    public String toString() {
        return "Cita{" + "CitaID=" + citaID + ", paciente=" + getPaciente().getNombre() + ", disponibilidad=" + getDisponibilidad().getDisponibilidadID() + ", Asunto=" + asunto + ", Observaciones=" + observaciones + ", Estado=" + estado + ", costo=" + costo + '}';
    }

}
