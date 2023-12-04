/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import capa3_Dominio.Medico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class MedicoSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public MedicoSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarMedico(Medico medico) throws Exception {
        String insertSQL = "INSERT INTO Medico (Telefono, Nombre, NumeroColegiado) VALUES (?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, medico.getTelefono());
            sentencia.setString(2, medico.getNombre());
            sentencia.setString(3, medico.getNumeroColegiado());
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al intentar guardar el medico.", e);
        }
    }

    public Medico buscarMedico(int codigo) throws Exception {
        String consultaSQL = "select * from medico where MedicoID = ?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setInt(1, codigo);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Medico medico = new Medico();
                medico.setMedicoID(resultado.getInt("MedicoID"));
                medico.setTelefono(resultado.getString("telefono"));
                medico.setNombre(resultado.getString("nombre"));
                medico.setNumeroColegiado(resultado.getString("numeroColegiado"));
                return medico;
            } else {
                throw new Exception("No existe el medico.");
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar buscar el medico", e);
        }
    }

    public List<Medico> listarMedicos() throws Exception {
        String consultaSQL = "select * from Medico";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();

            List<Medico> medicos = new ArrayList<>();

            while (resultado.next()) {
                Medico medico = new Medico();
                medico.setMedicoID(resultado.getInt("MedicoID"));
                medico.setNombre(resultado.getString("Nombre"));
                medico.setTelefono(resultado.getString("Telefono"));
                medico.setNumeroColegiado(resultado.getString("NumeroColegiado"));
                medicos.add(medico);
            }

            return medicos;
        } catch (Exception e) {
            throw new Exception("Error al intentar listar los pacientes", e);
        }
    }
}
