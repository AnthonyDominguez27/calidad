package capa3_Dominio;

import org.junit.Test;

import static org.junit.Assert.*;

public class PacienteTest {

    @Test
    public void test1tieneNombreValido() {
        System.out.println("tieneNombreValido");
        Paciente paciente = new Paciente();
        paciente.setNombre("CrishtiaPau lCrisanto");
        boolean expResult = true;
        boolean result;
        try {
            result = paciente.tieneNombreValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneNombreValido() {//Menos caracteres
        System.out.println("tieneNombreValido");
        Paciente paciente = new Paciente();
        paciente.setNombre("Crisanto");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneNombreValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test3tieneNombreValido() {//Caracteres No validos
        System.out.println("tieneNombreValido");
        Paciente paciente = new Paciente();
        paciente.setNombre("Crisanto Paul Crisanto %&&");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneNombreValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test4tieneNombreValido() {//Nulo
        System.out.println("tieneNombreValido");
        Paciente paciente = new Paciente();
        paciente.setNombre("");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneNombreValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }


    @Test
    public void test1tieneDireccionValida() {//VALIDO
        System.out.println("tienedireccionValida");
        Paciente paciente = new Paciente();
        paciente.setDireccion("Andres Avelino Caceres 245");
        boolean expResult = true;
        boolean result;

        try {
            result = paciente.tieneDireccionValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneDireccionValida() {//Caracteres no validos
        System.out.println("tienedireccionValida");
        Paciente paciente = new Paciente();
        paciente.setDireccion("Andres Avelino Caceres%% 245");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneDireccionValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test3tieneDireccionValida() {//Fuera del rango de longitud
        System.out.println("tienedireccionValida");
        Paciente paciente = new Paciente();
        paciente.setDireccion("Andres ");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneDireccionValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test4tieneDireccionValida() {//NULO
        System.out.println("tienedireccionValida");
        Paciente paciente = new Paciente();
        paciente.setDireccion("");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneDireccionValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1tieneDNIValido() {//DNI VALIDO
        System.out.println("tieneDNIValido");
        Paciente paciente = new Paciente();
        paciente.setDni("76622110");
        boolean expResult = true;
        boolean result;

        try {
            result = paciente.tieneDNIValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test2tieneDNIValido() {//Fuera del rango de Longitud
        System.out.println("tieneDNIValido");
        Paciente paciente = new Paciente();
        paciente.setDni("7662211");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneDNIValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test3tieneDNIValido() {//caracteres invalidos
        System.out.println("tieneDNIValido");
        Paciente paciente = new Paciente();
        paciente.setDni("asdfghjk");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneDNIValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1tieneTelefonoValido() {//Telefono Valido
        System.out.println("tieneTelefonoValido");
        Paciente paciente = new Paciente();
        paciente.setTelefono("989362635");
        boolean expResult = true;
        boolean result;

        try {
            result = paciente.tieneTelefonoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneTelefonoValido() {//Longitud no valida
        System.out.println("tieneTelefonoValido");
        Paciente paciente = new Paciente();
        paciente.setTelefono("98936263");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneTelefonoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test3tieneTelefonoValido() {//Caracteres no validos
        System.out.println("tieneTelefonoValido");
        Paciente paciente = new Paciente();
        paciente.setTelefono("asdfghasd");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneTelefonoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1tieneCorreoElectronicoValido() {//Correo valido
        System.out.println("tieneCorreoValido");
        Paciente paciente = new Paciente();
        paciente.setCorreo("ccrisantope@gmail.com");
        boolean expResult = true;
        boolean result;

        try {
            result = paciente.tieneCorreoElectronicoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneCorreoElectronicoValido() {//Formato de Correo no valido
        System.out.println("tieneCorreoValido");
        Paciente paciente = new Paciente();
        paciente.setCorreo("ccrisantopegmail.com");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneCorreoElectronicoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test3tieneCorreoElectronicoValido() {//Correo Nulo
        System.out.println("tieneCorreoValido");
        Paciente paciente = new Paciente();
        paciente.setCorreo("");
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneCorreoElectronicoValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1tieneEdadValida() {
        System.out.println("tieneEdadValida");
        Paciente paciente = new Paciente();
        paciente.setEdad(28);
        boolean expResult = true;
        boolean result;

        try {
            result = paciente.tieneEdadValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test2tieneEdadValida() {
        System.out.println("tieneEdadValida");
        Paciente paciente = new Paciente();
        paciente.setEdad(17);
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneEdadValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test3tieneEdadValida() {
        System.out.println("tieneEdadValida");
        Paciente paciente = new Paciente();
        paciente.setEdad(122);
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.tieneEdadValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void test1esPacienteValido() {//Paciente Valido
        System.out.println("Paciente Valido");
        Paciente paciente = new Paciente();
        paciente.setDni("766221110");
        paciente.setNombre("Cristhian Paul Crisanto Pescoran");
        paciente.setDireccion("Andres Avelino Caceres 245");
        paciente.setCorreo("ccrisantope@gmail.com");
        paciente.setTelefono("989362635");
        paciente.setEdad(28);
        boolean expResult = true;
        boolean result;

        try {
            result = paciente.tieneEdadValida();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
    @Test
    public void test2esPacienteValido() {//Paciente NO Valido
        System.out.println("Paciente Valido");
        Paciente paciente = new Paciente();
        paciente.setDni("76622110");
        paciente.setNombre("Cristhian ");//Dato No valido
        paciente.setDireccion("Andres Avelino Caceres 245");
        paciente.setCorreo("ccrisantope@gmail.com");
        paciente.setTelefono("989362635");
        paciente.setEdad(28);
        boolean expResult = false;
        boolean result;

        try {
            result = paciente.esPacienteValido();
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }
}