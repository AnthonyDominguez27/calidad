/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa2_Aplicacion;

import capa3_Dominio.Medico;
import capa4_Persistencia.AccesoDatosJDBC;
import capa4_Persistencia.AccesoDatosJDBCMicrosoftSQLServer;
import capa4_Persistencia.MedicoSQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class RegistrarMedicoServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private MedicoSQL medicoSQL;

    public RegistrarMedicoServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCMicrosoftSQLServer();
        medicoSQL = new MedicoSQL(accesoDatosJDBC);
    }

    public void GuardarMedico(Medico medico) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        medicoSQL.guardarMedico(medico);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Medico buscarMedico(int codigo) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Medico medico = medicoSQL.buscarMedico(codigo);
        accesoDatosJDBC.terminarTransaccion();
        return medico;
    }

    public List<Medico> listarMedicos() throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Medico> medicos = new ArrayList<>();
        medicos = medicoSQL.listarMedicos();
        accesoDatosJDBC.terminarTransaccion();
        return medicos;
    }
}
