/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa2_Aplicacion;

import capa3_Dominio.Paciente;
import capa4_Persistencia.AccesoDatosJDBC;
import capa4_Persistencia.AccesoDatosJDBCMicrosoftSQLServer;
import capa4_Persistencia.PacienteSQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class RegistrarPacienteServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private PacienteSQL pacienteSQL;

    public RegistrarPacienteServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCMicrosoftSQLServer();
        pacienteSQL = new PacienteSQL(accesoDatosJDBC);
    }

    public void GuardarPaciente(Paciente paciente) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        pacienteSQL.guardarPaciente(paciente);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Paciente buscarPaciente(String dni) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Paciente paciente = pacienteSQL.buscarPaciente(dni);
        accesoDatosJDBC.terminarTransaccion();
        return paciente;
    }

    public List<Paciente> listarPacientes() throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Paciente> pacientes = new ArrayList<>();
        pacientes = pacienteSQL.listarPacientes();
        accesoDatosJDBC.terminarTransaccion();
        return pacientes;
    }
}
