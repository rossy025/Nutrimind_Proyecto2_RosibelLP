/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import nutrimind_proyecto2_rosibellp.dao.EvaluacionDAO;
import nutrimind_proyecto2_rosibellp.dao.PacienteDAO;
import nutrimind_proyecto2_rosibellp.dao.PlanAlimentacionDAO;
import nutrimind_proyecto2_rosibellp.dominio.Evaluacion;
import nutrimind_proyecto2_rosibellp.dominio.Paciente;
import nutrimind_proyecto2_rosibellp.dominio.PlanAlimentacion;

/**
 *
 * @author carri
 */
public class PlanAlimentacionController {
        private final PacienteDAO pacDAO = new PacienteDAO();
    private final EvaluacionDAO evaDAO = new EvaluacionDAO();
    private final PlanAlimentacionDAO planDAO = new PlanAlimentacionDAO();

    
    public String obtenerUltimaCategoria(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) throw new Exception("Ingrese cédula.");
        Paciente p = pacDAO.buscarPorCedula(cedula.trim());
        if (p == null) throw new Exception("Paciente no encontrado.");
        List<Evaluacion> hist = evaDAO.listarPorPaciente(p.getIdPaciente()); 
        if (hist.isEmpty()) throw new Exception("El paciente no tiene evaluaciones.");
        return hist.get(hist.size() - 1).getCategoriaImc();
    }

    public String generarPlanTexto(String categoriaImc, String actividad) throws Exception {
        if (categoriaImc == null || categoriaImc.isBlank()) throw new Exception("Categoría vacía.");
        if (actividad == null || actividad.isBlank())       throw new Exception("Actividad vacía.");
        PlanAlimentacion plan = planDAO.buscar(categoriaImc, actividad);
        if (plan == null) throw new Exception("No existe plan para esa combinación.");
        return plan.getPlanTexto();
    }

}
