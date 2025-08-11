/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import nutrimind_proyecto2_rosibellp.dao.EvaluacionDAO;
import nutrimind_proyecto2_rosibellp.dao.PacienteDAO;
import nutrimind_proyecto2_rosibellp.dominio.Evaluacion;
import nutrimind_proyecto2_rosibellp.dominio.Paciente;
import nutrimind_proyecto2_rosibellp.dominio.Recomendaciones;

/**
 *
 * @author carri
 */
public class EvaluacionController {
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
    
    // Devuelve [IMC, Categoría, Recomendaciones]
    public String[] evaluarGuardarYRecomendar(String cedula, String pesoTxt, String alturaTxt, String actividad) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) throw new Exception("Ingrese cédula.");
        if (pesoTxt == null || pesoTxt.trim().isEmpty()) throw new Exception("Ingrese peso (kg).");
        if (alturaTxt == null || alturaTxt.trim().isEmpty()) throw new Exception("Ingrese altura (cm).");
        if (actividad == null || actividad.trim().isEmpty()) throw new Exception("Seleccione actividad.");

        // Buscar paciente
        Paciente p = pacienteDAO.buscarPorCedula(cedula.trim());
        if (p == null) throw new Exception("Paciente no encontrado.");

        // Parsear y validar números
        final double peso;
        final int alturaCm;
        try {
            peso = Double.parseDouble(pesoTxt.trim());
            alturaCm = Integer.parseInt(alturaTxt.trim());
        } catch (NumberFormatException nfe) {
            throw new Exception("Peso y altura deben ser numéricos.");
        }
        if (peso <= 0 || alturaCm <= 0) throw new Exception("Peso y altura deben ser > 0.");

        // Calcular IMC y categoría
        double imc = calcularIMC(peso, alturaCm);
        String categoria = categoriaIMC(imc);

        // Guardar evaluación
        Evaluacion ev = new Evaluacion();
        ev.setIdPaciente(p.getIdPaciente());
        ev.setImc(imc);
        ev.setCategoriaImc(categoria);
        ev.setFechaEvaluacion(new Date());
        evaluacionDAO.registrar(ev);

        // Recomendaciones 
        String rec = Recomendaciones.combinar(categoria, actividad);

        // IMC con 2 decimales 
        return new String[] { String.format("%.2f", imc), categoria, rec };
    }

    // Historial en tabla 
    public DefaultTableModel cargarHistorialModelo(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) throw new Exception("Ingrese cédula.");
        Paciente p = pacienteDAO.buscarPorCedula(cedula.trim());
        if (p == null) throw new Exception("Paciente no encontrado.");

        List<Evaluacion> lista = evaluacionDAO.listarPorPaciente(p.getIdPaciente());
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Fecha", "IMC", "Categoría"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        for (Evaluacion e : lista) {
            m.addRow(new Object[]{ e.getFechaEvaluacion(), String.format("%.2f", e.getImc()), e.getCategoriaImc() });
        }
        return m;
    }

   
    private double calcularIMC(double pesoKg, int alturaCm) {
        double m = alturaCm / 100.0;        
        return pesoKg / (m * m);
    }

    private String categoriaIMC(double imc) {
        if (imc < 18.5) return "Bajo peso";
        if (imc < 25)   return "Normal";
        if (imc < 30)   return "Sobrepeso";
        if (imc < 35)   return "Obesidad I";
        if (imc < 40)   return "Obesidad II";
        return "Obesidad III";
    }
}
