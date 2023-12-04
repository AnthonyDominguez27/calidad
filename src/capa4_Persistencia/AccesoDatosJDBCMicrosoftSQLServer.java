/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa4_Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cristhian
 */
public class AccesoDatosJDBCMicrosoftSQLServer extends AccesoDatosJDBC {

    public void abrirConexion() throws Exception {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=CitaMedica; encrypt=false;integratedSecurity=true;";


        try {
            String usuario = "localhost";
            String contraseña = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // La conexión se ha establecido correctamente
        } catch (Exception e) {
            throw new Exception("Ocurrió un problema en la conexión con la base de datos: " + e.getMessage());
        }
    }
}
