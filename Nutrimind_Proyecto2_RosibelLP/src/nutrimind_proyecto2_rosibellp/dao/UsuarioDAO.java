/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import nutrimind_proyecto2_rosibellp.dominio.Usuario;
/**
 *
 * @author carri
 */
public class UsuarioDAO {
     private Connection conexionDB; 
     
    public UsuarioDAO(){
        try{
            conexionDB = new ConexionDB().conectar();
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
             
        }
    }
    
    public void crear(Usuario u) {
        String sql = "INSERT INTO USUARIO (USUARIO, CONTRASEÑA, ROL) VALUES (?,?,?)";
        try (PreparedStatement ps = conexionDB.prepareStatement(sql)) {
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContraseña());
            ps.setString(3, u.getRol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existeUsuario(String user) {
        String sql = "SELECT 1 FROM USUARIO WHERE USUARIO=?";
        try (PreparedStatement ps = conexionDB.prepareStatement(sql)) {
            ps.setString(1, user);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Usuario buscarPorUsuario(String user) {
        String sql = "SELECT ID_USUARIO, USUARIO, CONTRASEÑA, ROL FROM USUARIO WHERE USUARIO=?";
        try (PreparedStatement ps = conexionDB.prepareStatement(sql)) {
            ps.setString(1, user);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("ID_USUARIO"));
                    u.setUsuario(rs.getString("USUARIO"));
                    u.setContraseña(rs.getString("CONTRASEÑA"));
                    u.setRol(rs.getString("ROL"));
                    return u;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // (renombré el método con el typo)
    public boolean validarCredenciales(String user, String pass) {
        String sql = "SELECT 1 FROM USUARIO WHERE USUARIO=? AND CONTRASEÑA=?";
        try (PreparedStatement ps = conexionDB.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Usuario buscarPorUsuarioYContrasena(String user, String pass) {
        String sql = "SELECT ID_USUARIO, USUARIO, CONTRASEÑA, ROL "
                   + "FROM USUARIO WHERE USUARIO=? AND CONTRASEÑA=?";
        try (PreparedStatement ps = conexionDB.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("ID_USUARIO"));
                    u.setUsuario(rs.getString("USUARIO"));
                    u.setContraseña(rs.getString("CONTRASEÑA"));
                    u.setRol(rs.getString("ROL"));
                    return u;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
