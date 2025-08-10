/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dao;

/**
 *
 * @author carri
 */
public class PruebaConexion {
    public static void main(String[] args) {
        try (var con = new ConexionDB().conectar()) {
            System.out.println("Conexión OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
