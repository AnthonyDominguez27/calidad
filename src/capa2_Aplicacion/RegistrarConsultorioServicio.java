/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa2_Aplicacion;

import capa3_Dominio.Consultorio;

import capa4_Persistencia.AccesoDatosJDBC;
import capa4_Persistencia.AccesoDatosJDBCMicrosoftSQLServer;
import capa4_Persistencia.ConsultorioSQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class RegistrarConsultorioServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private ConsultorioSQL consultorioSQL;

    public RegistrarConsultorioServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCMicrosoftSQLServer();
        consultorioSQL = new ConsultorioSQL(accesoDatosJDBC);
    }

    public void GuardarConsultorio(Consultorio consultorio) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        consultorioSQL.guardarConsultorio(consultorio);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Consultorio buscarConsultorio(int consultorioID) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Consultorio consultorio = new Consultorio();
        consultorioSQL.buscarConsultorio(consultorioID);
        accesoDatosJDBC.terminarTransaccion();
        return consultorio;

    }

    public List<Consultorio> listarConsultorios() throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Consultorio> consultorios = new ArrayList<>();
        consultorios = consultorioSQL.listarConsultorio();
        accesoDatosJDBC.terminarTransaccion();
        return consultorios;
    }
}
