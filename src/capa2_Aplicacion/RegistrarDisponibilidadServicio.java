/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa2_Aplicacion;

import capa3_Dominio.Disponibilidad;
import capa3_Dominio.Especialidad;

import capa4_Persistencia.AccesoDatosJDBC;
import capa4_Persistencia.AccesoDatosJDBCMicrosoftSQLServer;
import capa4_Persistencia.DisponibilidadSQL;
import capa4_Persistencia.EspecialidadSQL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class RegistrarDisponibilidadServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private DisponibilidadSQL disponibilidadSQL;

    public RegistrarDisponibilidadServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCMicrosoftSQLServer();
        disponibilidadSQL = new DisponibilidadSQL(accesoDatosJDBC);
    }

    public void GuardarDisponibilidad(Disponibilidad disponibilidad) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        disponibilidadSQL.guardarDisponibilidad(disponibilidad);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Disponibilidad buscarDisponibilidad(int codigo) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Disponibilidad disponibilidad = disponibilidadSQL.buscarDisponibilidad(codigo);
        accesoDatosJDBC.terminarTransaccion();
        return disponibilidad;
    }

    public List<Disponibilidad> listarDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, Especialidad especialidad) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Disponibilidad> disponibilidades = new ArrayList<>();
        disponibilidades = disponibilidadSQL.listarDisponibilidadEntreFechas(fechaInicio, fechaFin,especialidad);
        accesoDatosJDBC.terminarTransaccion();
        return disponibilidades;
    }
    /*public List<Disponibilidad> listarDisponibilidad() throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Disponibilidad> disponibilidades = new ArrayList<>();
        disponibilidades = disponibilidadSQL.listarDisponibilidad();
        accesoDatosJDBC.terminarTransaccion();
        return disponibilidades;
    }*/

}
