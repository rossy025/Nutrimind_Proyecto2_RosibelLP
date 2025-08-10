/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nutrimind_proyecto2_rosibellp.dominio.Evaluacion;
/**
 *
 * @author carri
 */
public class EvaluacionDAO {
    private Connection conexionDB;
    
    public EvaluacionDAO(){
        try{
            conexionDB = new ConexionDB().conectar();
        }catch (SQLException ex){
            Logger.getLogger(EvaluacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrar(Evaluacion e){
        String sql ="INSERT INTO EVALUACION(ID_PACIENTE, IMC, CATEGORIA_IMC, FECHA_EVALUACION) VALUES (?,?,?,?)";
        try{
            PreparedStatement pst = conexionDB.prepareStatement(sql);
            pst.setInt(1, e.getIdPaciente());
            pst.setDouble(2, e.getImc());
            pst.setString(3, e.getCategoriaImc());
            pst.setDate(4, new java.sql.Date(e.getFechaEvaluacion().getTime()));
            pst.execute();
        } catch(SQLException ex){
                Logger.getLogger(EvaluacionDAO.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
    
    public List<Evaluacion>listarPorPaciente(int idPaciente){
       List<Evaluacion> lista = new ArrayList<>();
       String sql = "SELECT * FROM EVALUACION WHERE ID_PACIENTE=? ORDER BY FECHA_EVALUACION DESC";
       try{
           PreparedStatement pst = conexionDB.prepareStatement(sql);
           pst.setInt(1, idPaciente);
           ResultSet rs = pst.executeQuery();
           while (rs.next()){
               Evaluacion e = new Evaluacion();
               e.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
               e.setIdPaciente(rs.getInt("ID_PACIENTE"));
               e.setImc(rs.getDouble("IMC"));
               e.setCategoriaImc(rs.getString("CATEGORIA_IMC"));
               e.setFechaEvaluacion(rs.getDate("FECHA_EVALUACION"));
               lista.add(e);
           }
       }catch(SQLException ex){
           Logger.getLogger(EvaluacionDAO.class.getName()).log(Level.SEVERE, null, ex);     
       }
       return lista;
    } 
}
