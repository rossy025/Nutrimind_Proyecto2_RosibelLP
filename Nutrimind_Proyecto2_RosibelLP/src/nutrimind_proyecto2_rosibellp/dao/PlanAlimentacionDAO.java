/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import nutrimind_proyecto2_rosibellp.dominio.PlanAlimentacion;
/**
 *
 * @author carri
 */
public class PlanAlimentacionDAO {
    
    private Connection conexionDB;
    
    public PlanAlimentacionDAO(){
        try{
            conexionDB = new ConexionDB().conectar();
        }catch (SQLException ex){
            Logger.getLogger(PlanAlimentacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PlanAlimentacion buscar (String categoriaImc, String actividad){
        String sql = "SELECT id_plan, categoria_imc, actividad_fisica, plan_texto " + "FROM PlanAlimentacion WHERE categoria_imc = ? AND actividad_fisica = ?";
        try (PreparedStatement pst = conexionDB.prepareStatement(sql)) {
            pst.setString(1, categoriaImc);
            pst.setString(2, actividad);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    PlanAlimentacion p = new PlanAlimentacion();
                    p.setIdPlan(rs.getInt("id_plan"));
                    p.setCategoriaImc(rs.getString("categoria_imc"));
                    p.setActividadFisica(rs.getString("actividad_fisica"));
                    p.setPlanTexto(rs.getString("plan_texto"));
                    return p;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanAlimentacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    } 
}
