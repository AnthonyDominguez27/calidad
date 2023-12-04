/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import capa3_Dominio.Paciente;
import capa3_Dominio.PacienteMenor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class PacienteSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public PacienteSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarPaciente(Paciente paciente) throws Exception {
        String insertSQL = "INSERT INTO Paciente(DNI,Nombre,Direccion,Correo,Telefono,Edad,MayorDeEdad) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement sentencia;
        try {
            if (paciente.esPacienteValido()) {
                sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
                sentencia.setString(1, paciente.getDni());
                sentencia.setString(2, paciente.getNombre());
                sentencia.setString(3, paciente.getDireccion());
                sentencia.setString(4, paciente.getCorreo());
                sentencia.setString(5, paciente.getTelefono());
                sentencia.setInt(6, paciente.getEdad());
                sentencia.setBoolean(7, paciente.isMayorDeEdad());
                sentencia.executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception(e);

        }
    }

    public void guardarPacienteMenor(PacienteMenor paciente) throws Exception {
        String insertSQL = "INSERT INTO Paciente(DNI,Nombre,Direccion,Correo,Telefono,Edad,MayorDeEdad,Apoderado) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, paciente.getDni());
            sentencia.setString(2, paciente.getNombre());
            sentencia.setString(3, paciente.getDireccion());
            sentencia.setString(4, paciente.getCorreo());
            sentencia.setString(5, paciente.getTelefono());
            sentencia.setInt(6, paciente.getEdad());
            sentencia.setBoolean(7, paciente.isMayorDeEdad());
            sentencia.setString(8, paciente.getApoderado().getDni());
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);

        }
    }

    public Paciente buscarPaciente(String dni) throws Exception {
        String consultaSQL = "select * from paciente where DNI = ?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setString(1, dni);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Paciente paciente = new Paciente();
                paciente.setDni(dni);
                paciente.setNombre(resultado.getString("Nombre"));
                paciente.setDireccion(resultado.getString("Direccion"));
                paciente.setCorreo(resultado.getString("Correo"));
                paciente.setTelefono(resultado.getString("Telefono"));
                paciente.setEdad(resultado.getInt("Edad"));
                paciente.setMayorDeEdad(resultado.getBoolean("MayorDeEdad"));

                return paciente;
            } else {
                throw new Exception("No existe el paciente.");
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar buscar el paciente", e);
        }
    }

    public List<Paciente> listarPacientes() throws Exception {
        String consultaSQL = "select * from Paciente";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();

            List<Paciente> pacientes = new ArrayList<>();

            while (resultado.next()) {
                Paciente paciente = new Paciente();
                paciente.setDni(resultado.getString("DNI"));
                paciente.setNombre(resultado.getString("Nombre"));
                paciente.setDireccion(resultado.getString("Direccion"));
                paciente.setCorreo(resultado.getString("Correo"));
                paciente.setEdad(resultado.getInt("Edad"));
                paciente.setTelefono(resultado.getString("Telefono"));
                pacientes.add(paciente);
            }

            return pacientes;
        } catch (Exception e) {
            throw new Exception("Error al intentar listar los pacientes", e);
        }
    }
}
