package capa3_Dominio;

import com.sun.media.jfxmedia.control.MediaPlayerOverlay;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CitaTest {

    @Test
    public void test1tieneAsuntoValido() {
        System.out.println("ASUNTO Valido");
        Cita cita = new Cita();
        cita.setAsunto("Este es una prueba donde se evaluan las reglas");


        boolean expResult = true;
        boolean result;

        try {
            result = cita.tieneAsuntoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test2tieneAsuntoValido() {//Longitud no valida
        System.out.println("ASUNTO Valido");
        Cita cita = new Cita();
        cita.setAsunto("Este es ");


        boolean expResult = false;
        boolean result;

        try {
            result = cita.tieneAsuntoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test3tieneAsuntoValido() {//Caracteres no validos
        System.out.println("ASUNTO Valido");
        Cita cita = new Cita();
        cita.setAsunto("Este es una prueba donde se $%#evaluan las reglas de negocio");


        boolean expResult = false;
        boolean result;

        try {
            result = cita.tieneAsuntoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test1tieneObservacionesValidas() {
        System.out.println("Observaciones Validas");
        Cita cita = new Cita();
        cita.setObservaciones("Este es una prueba donde se evaluan las reglas");


        boolean expResult = true;
        boolean result;

        try {
            result = cita.tieneObservacionesValidas();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test2tieneObservacionesValidas() {
        System.out.println("Observaciones Validas");
        Cita cita = new Cita();
        cita.setObservaciones("Este es");


        boolean expResult = false;
        boolean result;

        try {
            result = cita.tieneObservacionesValidas();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test

    public void test3tieneObservacionesValidas() {
        System.out.println("Observaciones Validas");
        Cita cita = new Cita();
        cita.setObservaciones("Este es una prueba donde@@%& se evaluan las reglas de negocio");


        boolean expResult = false;
        boolean result;

        try {
            result = cita.tieneObservacionesValidas();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test1tieneEstadoValido() {
        System.out.println("ESTADO Valido");
        Cita cita = new Cita();
        cita.setEstado("PROGRAMADO");


        boolean expResult = true;
        boolean result;

        try {
            result = cita.tieneEstadoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneEstadoValido() {
        System.out.println("ESTADO Valido");
        Cita cita = new Cita();
        cita.setEstado("NO PROGRAMADO");


        boolean expResult = false;
        boolean result;

        try {
            result = cita.tieneEstadoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1tieneCostoValido() {
        System.out.println("Costo Valido");
        Cita cita = new Cita();
        cita.setCosto(new BigDecimal(100));


        boolean expResult = true;
        boolean result;

        try {
            result = cita.tieneCostoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneCostoValido() {
        System.out.println("Costo Valido");
        Cita cita = new Cita();
        cita.setCosto(new BigDecimal(30));


        boolean expResult = false;
        boolean result;

        try {
            result = cita.tieneEstadoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1esPacienteValido() {
        System.out.println("Paciente Valido");
        Paciente paciente = new Paciente();
        paciente.setDni("76622110");
        paciente.setNombre("Cristhian Paul Crisanto Pescoran");
        paciente.setDireccion("Andres Avelino Caceres 245");
        paciente.setCorreo("ccrisantope@gmail.com");
        paciente.setTelefono("989362635");
        paciente.setEdad(28);
        Cita cita= new Cita();
        cita.setPaciente(paciente);
        boolean expResult = true;
        boolean result;

        try {
            result = cita.esPacienteValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2esPacienteValido() {
        System.out.println("Paciente Valido");
        Cita cita= new Cita();
        Paciente paciente = new Paciente();
        paciente.setDni("76622110");
        paciente.setNombre("Cristhian Paul Crisanto Pescoran");
        paciente.setDireccion("Andres Avelino Caceres 245");
        paciente.setCorreo("ccrisantope@gmail.com");
        paciente.setTelefono("989362635");
        paciente.setEdad(28);
        cita.setPaciente(paciente);
        boolean expResult = true;
        boolean result;

        try {
            result = cita.esPacienteValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test1esCitaValida() {
        Medico medico= new Medico(1);
        Especialidad especialidad= new Especialidad(1);
        Paciente paciente= new Paciente();
        Consultorio consultorio= new Consultorio();
        Disponibilidad disponibilidad= new Disponibilidad(LocalDateTime.of(2023, 10, 30, 11, 00), true, especialidad,  medico, new Consultorio());
        paciente.setDni("76622110");
        paciente.setNombre("Cristhian Paul Crisanto Pescoran");
        paciente.setDireccion("Andres Avelino Caceres 245");
        paciente.setCorreo("ccrisantope@gmail.com");
        paciente.setTelefono("989362635");
        paciente.setEdad(28);
        Cita cita= new Cita();
        cita.setDisponibilidad(disponibilidad);
        cita.setPaciente(paciente);
        cita.setAsunto("Asunto acerca de la cita");
        cita.setObservaciones("Observaciones acerca de la cita");
        cita.setEstado("PROGRAMADO");
        cita.setCosto(new BigDecimal(100));
        boolean expResult = true;
        boolean result;

        try {
            result = cita.esCitaValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
       assertEquals(expResult, result);

   }

    @Test
    public void test2esCitaValida() {
        Medico medico= new Medico(1);
        Especialidad especialidad= new Especialidad(1);
        Paciente paciente= new Paciente();
        Disponibilidad disponibilidad= new Disponibilidad(LocalDateTime.of(2023, 10, 30, 11, 00), true, especialidad,  medico, new Consultorio());
        paciente.setDni("76622110");
        paciente.setNombre("Cristhian Paul Crisanto Pescoran");
        paciente.setDireccion("Andres Avelino Caceres 245");
        paciente.setCorreo("ccrisantope@gmail.com");
        paciente.setTelefono("989362635");
        paciente.setEdad(28);
        Cita cita= new Cita();
        cita.setDisponibilidad(disponibilidad);
        cita.setPaciente(paciente);
        cita.setAsunto("Asunto acerca de la cita-------------------------------------------------------------------------------");
        cita.setObservaciones("Observaciones acerca de la cita");
        cita.setEstado("PROGRAMADO");
        cita.setCosto(new BigDecimal(30));
        boolean expResult = false;
        boolean result;

        try {
            result = cita.esCitaValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);

    }

}