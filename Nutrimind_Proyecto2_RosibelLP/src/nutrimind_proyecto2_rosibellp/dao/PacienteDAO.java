/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import nutrimind_proyecto2_rosibellp.dominio.Paciente;
/**
 *
 * @author carri
 */
public class PacienteDAO {
    private Connection conexionDB;
    
    public PacienteDAO(){
        try{
            conexionDB = new ConexionDB().conectar();
        }catch (SQLException ex){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean existeCedula(String cedula){
        String sql = "SELECT 1 FROM Paciente WHERE cedula = ?";
        
        try {
            PreparedStatement pst = conexionDB.prepareStatement(sql);
            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }catch (SQLException ex){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void registrar(Paciente p){
        String sql = "INSERT INTO Paciente(cedula, nombre, edad,sexo, peso, altura,actividad_fisica,fecha_registro)" + "VALUES(?,?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement pst = conexionDB.prepareStatement(sql);
            pst.setString(1, p.getCedula());
            pst.setString(2, p.getNombre());
            pst.setInt(3, p.getEdad());
            pst.setString(4, p.getSexo());
            pst.setDouble(5, p.getPeso());
            pst.setDouble(6, p.getAltura());
            pst.setString(7, p.getActividadFisica());
            pst.setDate(8, new java.sql.Date(p.getFechaRegistro().getTime())); //Se convierte antes de pasarlo porque espera un sql.date y devolvía un util.date al seguir el orden como los demás
            pst.execute();
           
        }catch (SQLException ex){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Paciente buscarPorCedula(String cedula){
        String sql = "SELECT * FROM Paciente WHERE cedula = ?";
        
        try{
            PreparedStatement pst = conexionDB.prepareStatement(sql);
            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt("id_paciente"));
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setPeso(rs.getDouble("peso"));
                p.setAltura(rs.getDouble("altura"));
                p.setActividadFisica(rs.getString("actividad_fisica"));
                p.setFechaRegistro(rs.getDate("fecha_registro"));
                
                return p;
            }
        } catch (SQLException ex){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void actualizar(Paciente p){
        String sql = "UPDATE Paciente SET nombre = ?, edad = ?, sexo = ?, peso = ?, altura = ?, actividad_fisica = ? " + "WHERE cedula = ?";
        try (PreparedStatement pst = conexionDB.prepareStatement(sql)) {
            pst.setString(1, p.getNombre());
            pst.setInt(2, p.getEdad());
            pst.setString(3, p.getSexo());
            pst.setDouble(4, p.getPeso());
            pst.setDouble(5, p.getAltura());
            pst.setString(6, p.getActividadFisica());
            pst.setString(7, p.getCedula());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Paciente> listar(){
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente ORDER BY nombre";
        try{
            PreparedStatement pst = conexionDB.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt("id_paciente"));
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setPeso(rs.getDouble("peso"));
                p.setAltura(rs.getDouble("altura"));
                p.setActividadFisica(rs.getString("actividad_fisica"));
                p.setFechaRegistro(rs.getDate("fecha_registro"));
                lista.add(p);
            }
        }catch (SQLException ex){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public boolean eliminar(String cedula) {
    String sql = "DELETE FROM Paciente WHERE cedula = ?";
    try (PreparedStatement pst = conexionDB.prepareStatement(sql)) {
        pst.setString(1, cedula);
        return pst.executeUpdate() > 0;
    } catch (SQLException ex) {
        Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}
}
