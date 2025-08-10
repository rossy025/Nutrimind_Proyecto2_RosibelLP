/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dao;



import java.sql.*;

/**
 *
 * @author carri
 */
public class ConexionDB {
    private String urlConexion="jdbc:derby://localhost:1527/Proyecto";
    private String userName="admin_";
    private String password="123";
    
    public Connection conectar() throws SQLException{
        return DriverManager.getConnection(urlConexion,userName,password);
    }
    
}
