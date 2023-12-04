/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import capa3_Dominio.Consultorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class ConsultorioSQL {

    private AccesoDatosJDBC accesoDatosJDBC;

    public ConsultorioSQL(AccesoDatosJDBC accesoDatosJDBC) {
        this.accesoDatosJDBC = accesoDatosJDBC;
    }

    public void guardarConsultorio(Consultorio consultorio) throws Exception {
        String insertSQL = "INSERT INTO Consultorio (Consultorio, Direccion, Descripcion) VALUES (?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(insertSQL);
            sentencia.setString(1, consultorio.getConsultorio());
            sentencia.setString(2, consultorio.getDireccion());
            sentencia.setString(3, consultorio.getDescripcion());
            sentencia.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al intentar guardar el consultorio.", e);
        }
    }

    public Consultorio buscarConsultorio(int codigo) throws Exception {
        String consultaSQL = "SELECT * FROM Consultorio WHERE ConsultorioID = ?";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            sentencia.setInt(1, codigo);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                Consultorio consultorio = new Consultorio();
                consultorio.setConsultorioID(resultado.getInt("ConsultorioID"));
                consultorio.setConsultorio(resultado.getString("Consultorio"));
                consultorio.setDireccion(resultado.getString("Direccion"));
                consultorio.setDescripcion(resultado.getString("Descripcion"));
                return consultorio;
            } else {
                throw new Exception("No existe el consultorio.");
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar buscar el consultorio", e);
        }
    }

    public List<Consultorio> listarConsultorio() throws Exception {
        String consultaSQL = "select * from Consultorio";
        PreparedStatement sentencia;
        try {
            sentencia = accesoDatosJDBC.prepararSentencia(consultaSQL);
            ResultSet resultado = sentencia.executeQuery();

            List<Consultorio> consultorios = new ArrayList<>();

            while (resultado.next()) {
                Consultorio consultorio = new Consultorio();
                consultorio.setConsultorioID(resultado.getInt("ConsultorioID"));
                consultorio.setConsultorio(resultado.getString("Consultorio"));
                consultorio.setDireccion(resultado.getString("Direccion"));
                consultorio.setDescripcion(resultado.getString("Descripcion"));
                consultorios.add(consultorio);
            }

            return consultorios;
        } catch (Exception e) {
            throw new Exception("Error al intentar listar las consultorios", e);
        }
    }
}
