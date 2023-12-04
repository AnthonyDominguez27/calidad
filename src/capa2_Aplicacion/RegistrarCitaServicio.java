/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa2_Aplicacion;

import capa3_Dominio.Cita;
import capa4_Persistencia.AccesoDatosJDBC;
import capa4_Persistencia.AccesoDatosJDBCMicrosoftSQLServer;
import capa4_Persistencia.CitaSQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class RegistrarCitaServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private CitaSQL citaSQL;

    public RegistrarCitaServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCMicrosoftSQLServer();
        citaSQL = new CitaSQL(accesoDatosJDBC);
    }

    public void GuardarCita(Cita cita) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        citaSQL.guardarCita(cita);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Cita buscarCita(int citaID) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Cita cita = new Cita();
        cita = citaSQL.buscarCita(citaID);
        accesoDatosJDBC.terminarTransaccion();
        return cita;

    }
    
    public List<Cita> listarCitas() throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Cita> citas = new ArrayList<>();
        citas = citaSQL.listarCitas();
        accesoDatosJDBC.terminarTransaccion();
        return citas;
    }
}
