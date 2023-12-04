/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa2_Aplicacion;

import capa3_Dominio.Especialidad;
import capa3_Dominio.Medico;
import capa4_Persistencia.AccesoDatosJDBC;
import capa4_Persistencia.AccesoDatosJDBCMicrosoftSQLServer;
import capa4_Persistencia.EspecialidadSQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhian
 */
public class RegistrarEspecialidadServicio {

    private AccesoDatosJDBC accesoDatosJDBC;
    private EspecialidadSQL especialidadSQL;

    public RegistrarEspecialidadServicio() {
        accesoDatosJDBC = new AccesoDatosJDBCMicrosoftSQLServer();
        especialidadSQL = new EspecialidadSQL(accesoDatosJDBC);
    }

    public void GuardarEspecialidad(Especialidad especialidad) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        especialidadSQL.guardarEspecialidad(especialidad);
        accesoDatosJDBC.terminarTransaccion();
    }

    public Especialidad buscarEspecialidad(int codigo) throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        Especialidad especialidad = especialidadSQL.buscarEspecialidad(codigo);
        accesoDatosJDBC.terminarTransaccion();
        return especialidad;
    }

    public List<Especialidad> listarEspecialidades() throws Exception {
        accesoDatosJDBC.abrirConexion();
        accesoDatosJDBC.iniciarTransaccion();
        List<Especialidad> especialidades = new ArrayList<>();
        especialidades = especialidadSQL.listarEspecialidad();
        accesoDatosJDBC.terminarTransaccion();
        return especialidades;
    }
}
