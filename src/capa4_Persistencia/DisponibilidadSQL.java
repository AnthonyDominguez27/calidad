/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import capa3_Dominio.Consultorio;
import capa3_Dominio.Disponibilidad;
import capa3_Dominio.Especialidad;
import capa3_Dominio.Medico;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author Cristhian
 */
public class DisponibilidadSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public DisponibilidadSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarDisponibilidad(Disponibilidad disponibilidad) throws Exception {
        String insertSQL = "INSERT INTO Disponibilidad (FechaHora, EspecialidadID,Estado, MedicoID, ConsultorioID) VALUES ( ?, ?,?, ?,?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setTimestamp(1, Timestamp.valueOf(disponibilidad.getFechaHora()));
            sentencia.setInt(2, disponibilidad.getEspecialidad().getEspecialidadID());
            sentencia.setBoolean(3, disponibilidad.isEstado());
            sentencia.setInt(4, disponibilidad.getMedico().getMedicoID());
            sentencia.setInt(5, disponibilidad.getConsultorio().getConsultorioID());

            sentencia.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Error al intentar guardar el disponibilidad.", e);
        }
    }

    public Disponibilidad buscarDisponibilidad(int codigo) throws Exception {
        String consultaSQL = "select * from Disponibilidad where DisponibilidadID  = ?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setInt(1, codigo);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Disponibilidad disponibilidad = new Disponibilidad();
                disponibilidad.setDisponibilidadID(resultado.getInt("DisponibilidadID"));
                MedicoSQL medicoSql = new MedicoSQL(accesoDatosJDBC);
                Medico medico = medicoSql.buscarMedico(resultado.getInt("MedicoID"));
                EspecialidadSQL especialidadSql = new EspecialidadSQL(accesoDatosJDBC);
                Especialidad especialidad = especialidadSql.buscarEspecialidad(resultado.getInt("especialidadID"));
                ConsultorioSQL consultorioSql = new ConsultorioSQL(accesoDatosJDBC);
                Consultorio consultorio = consultorioSql.buscarConsultorio(resultado.getInt("consultorioID"));
                disponibilidad.setConsultorio(consultorio);
                disponibilidad.setEspecialidad(especialidad);
                disponibilidad.setMedico(medico);
                disponibilidad.setEstado(resultado.getBoolean("Estado"));
                disponibilidad.setFechaHora(resultado.getTimestamp("fechaHora").toLocalDateTime());
                return disponibilidad;
            } else {
                throw new Exception("No existe la disponibilidad");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }



    public List<Disponibilidad> listarDisponibilidadEntreFechas(LocalDate fechaInicio, LocalDate fechaFin, Especialidad especialidadBuscada) throws Exception {
        String consultaSQL= "";
        if(especialidadBuscada.getEspecialidad().equalsIgnoreCase("TODAS")) {
            consultaSQL = "SELECT * FROM Disponibilidad WHERE Estado = 1 AND FechaHora BETWEEN ? AND ? ORDER BY FechaHora";
        }
        else {
            consultaSQL = "SELECT * FROM Disponibilidad WHERE Estado = 1 AND EspecialidadID=? AND FechaHora BETWEEN ? AND ? ORDER BY FechaHora";
        }
        PreparedStatement sentencia;
        Date sqlFechaInicio = Date.valueOf(fechaInicio);
        Date sqlFechaFin = Date.valueOf(fechaFin);
        try {

            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            if(especialidadBuscada.getEspecialidad().equalsIgnoreCase("TODAS")) {
                sentencia.setDate(1, sqlFechaInicio);
                sentencia.setDate(2, sqlFechaFin);
            }
            else {
                sentencia.setInt(1, especialidadBuscada.getEspecialidadID());
                sentencia.setDate(2, sqlFechaInicio);
                sentencia.setDate(3, sqlFechaFin);
            }

            ResultSet resultado = sentencia.executeQuery();

            List<Disponibilidad> disponibilidades = new ArrayList<>();

            while (resultado.next()) {
                Disponibilidad disponibilidad = new Disponibilidad();
                disponibilidad.setDisponibilidadID(resultado.getInt("DisponibilidadID"));
                MedicoSQL medicoSql = new MedicoSQL(accesoDatosJDBC);
                Medico medico = medicoSql.buscarMedico(resultado.getInt("MedicoID"));
                EspecialidadSQL especialidadSql = new EspecialidadSQL(accesoDatosJDBC);
                Especialidad especialidad = especialidadSql.buscarEspecialidad(resultado.getInt("EspecialidadID"));
                ConsultorioSQL consultorioSQL = new ConsultorioSQL(accesoDatosJDBC);
                Consultorio consultorio = consultorioSQL.buscarConsultorio(resultado.getInt("ConsultorioID"));
                disponibilidad.setConsultorio(consultorio);
                disponibilidad.setEspecialidad(especialidad);
                disponibilidad.setMedico(medico);
                disponibilidad.setEstado(resultado.getBoolean("Estado"));
                disponibilidad.setFechaHora(resultado.getTimestamp("fechaHora").toLocalDateTime());
                disponibilidades.add(disponibilidad);
            }

            return disponibilidades;
        } catch (Exception e) {
            throw new Exception("Error al intentar listar las disponibilidades", e);
        }
    }



}
