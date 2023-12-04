/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AccesoDatosJDBC {

    protected Connection conexion;

    public abstract void abrirConexion() throws Exception;

    public void cerrarConexion() throws SQLException {
        try {
            conexion.close();
        } catch (Exception e) {
            throw new SQLException("Ocurrio un problema con la conexión", e);
        }
    }

    public void iniciarTransaccion() throws SQLException {
        try {
            conexion.setAutoCommit(false);
        } catch (Exception e) {
            throw new SQLException("Ocurrio un problema con la conexión", e);
        }
    }

    public void terminarTransaccion() throws SQLException {
        try {
            conexion.commit();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (Exception e) {
            throw new SQLException("Ocurrio un problema con la conexión", e);
        }
    }

    public void cancelarTransaccion() throws SQLException {
        try {
            conexion.rollback();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (Exception e) {
            throw new SQLException("Ocurrio un problema con la conexión", e);
        }
    }

    public PreparedStatement prepararSentencia(String sql) throws SQLException {
        try {
            PreparedStatement sentencia;
            sentencia = conexion.prepareStatement(sql);
            return sentencia;
        } catch (SQLException e) {
            throw new SQLException("Ocurrio un problema con la conexión", e);
        }
    }

    public ResultSet ejecutarConsulta(String sql) throws SQLException {
        try (Statement sentencia = conexion.createStatement()) {
            // Imprimir la consulta (opcional, para depuración)
            System.out.println(sentencia.toString());

            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet resultado = sentencia.executeQuery(sql);

            // Imprimir el conjunto de resultados (opcional, para depuración)
            System.out.println(resultado.toString());

            // Devolver el conjunto de resultados
            return resultado;
        } catch (Exception e) {
            // Capturar y relanzar excepciones SQLException con un mensaje personalizado
            throw new SQLException("Ocurrió un problema con la conexión", e);
        }
    }


}//end AccesoDatosJDBC
