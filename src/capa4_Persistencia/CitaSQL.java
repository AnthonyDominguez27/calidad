/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import capa3_Dominio.Cita;
import capa3_Dominio.Disponibilidad;
import capa3_Dominio.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class CitaSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public CitaSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarCita(Cita cita) throws Exception {
        String insertSQL = "INSERT INTO Cita (PacienteDNI, DisponibilidadID, Asunto, Observaciones, Estado,costo) VALUES (?, ?, ?, ?, ?,?)";

        PreparedStatement sentencia;
        try {

            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, cita.getPaciente().getDni());
            sentencia.setInt(2, cita.getDisponibilidad().getDisponibilidadID());
            sentencia.setString(3, cita.getAsunto());
            sentencia.setString(4, cita.getObservaciones());
            sentencia.setString(5, cita.getEstado());
            sentencia.setBigDecimal(6, cita.getCosto());
            sentencia.executeLargeUpdate();

        } catch (Exception e) {
            throw new Exception("Error al intentar guardar la cita.", e);
        }
    }

    public Cita buscarCita(int codigo) throws Exception {
        String consultaSQL = "select * from cita where CitaID = ?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setInt(1, codigo);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Cita cita = new Cita();
                cita.setCitaID(resultado.getInt("CitaID"));
                PacienteSQL pacienteSql = new PacienteSQL(accesoDatosJDBC);
                Paciente paciente = pacienteSql.buscarPaciente(resultado.getString("PacienteDNI"));
                System.out.println("BuscarCita"+paciente.toString());
                System.out.println(paciente.toString());
                DisponibilidadSQL disponibilidadSql = new DisponibilidadSQL(accesoDatosJDBC);
                Disponibilidad disponibilidad = disponibilidadSql.buscarDisponibilidad(resultado.getInt("DisponibilidadID"));
                System.out.println(disponibilidad.toString());
                cita.setDisponibilidad(disponibilidad);
                cita.setPaciente(paciente);
                cita.setAsunto(resultado.getString("Asunto"));
                cita.setObservaciones(resultado.getString("Observaciones"));
                cita.setCosto(resultado.getBigDecimal("costo"));
                cita.setEstado(resultado.getString("Estado"));
                return cita;
            } else {
                throw new Exception("No existe la cita");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    public List<Cita> listarCitas() throws Exception {
        String consultaSQL = "select * from Cita";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();

            List<Cita> especialidades = new ArrayList<>();

            while (resultado.next()) {
                Cita cita = new Cita();
                cita.setCitaID(resultado.getInt("CitaID"));
                DisponibilidadSQL disponiblidadSQL = new DisponibilidadSQL(accesoDatosJDBC);
                Disponibilidad disponibilidad = disponiblidadSQL.buscarDisponibilidad(resultado.getInt("DisponibilidadID"));
                cita.setDisponibilidad(disponibilidad);
                PacienteSQL pacienteSQL = new PacienteSQL(accesoDatosJDBC);
                Paciente paciente = pacienteSQL.buscarPaciente(resultado.getString("PacienteDNI"));
                cita.setPaciente(paciente);
                cita.setAsunto(resultado.getString("Asunto"));
                cita.setObservaciones(resultado.getString("Observaciones"));
                cita.setCosto(resultado.getBigDecimal("costo"));
                cita.setEstado(resultado.getString("Estado"));
                especialidades.add(cita);
            }

            return especialidades;
        } catch (Exception e) {
            throw new Exception("Error al intentar listar las citas", e);
        }
    }
}
