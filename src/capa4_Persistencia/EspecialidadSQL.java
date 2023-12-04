/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import capa3_Dominio.Consultorio;
import capa3_Dominio.Especialidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class EspecialidadSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public EspecialidadSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarEspecialidad(Especialidad especialidad) throws Exception {
        String insertSQL = "INSERT INTO Especialidad (Especialidad) VALUES (?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, especialidad.getEspecialidad());
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Especialidad buscarEspecialidad(int codigo) throws Exception {
        String consultaSQL = "select * from especialidad where EspecialidadID = ?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setInt(1, codigo);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setEspecialidadID(resultado.getInt("especialidadId"));
                especialidad.setEspecialidad(resultado.getString("especialidad"));
                return especialidad;
            } else {
                throw new Exception("No existe la especialidad.");
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar buscar la especialidad", e);
        }
    }

    public List<Especialidad> listarEspecialidad() throws Exception {
        String consultaSQL = "select * from Especialidad";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();

            List<Especialidad> especialidades = new ArrayList<>();

            while (resultado.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setEspecialidadID(resultado.getInt("EspecialidadID"));
                especialidad.setEspecialidad(resultado.getString("Especialidad"));
                especialidades.add(especialidad);
            }

            return especialidades;
        } catch (Exception e) {
            throw new Exception("Error al intentar listar las especialidades", e);
        }
    }
}
