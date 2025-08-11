/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import nutrimind_proyecto2_rosibellp.dao.PacienteDAO;
import nutrimind_proyecto2_rosibellp.dominio.Paciente;

/**
 *
 * @author carri
 */
public class PacientesController {
    private final PacienteDAO dao = new PacienteDAO();

    public boolean eliminar(String cedula) throws Exception {
        return dao.eliminar(cedula);
    }
    public void registrarPaciente(String cedula, String nombre, Object edadValor,String sexo, String alturaTxt, String pesoTxt, String actividad) throws Exception {

        
        if (cedula == null || cedula.trim().isEmpty()) throw new Exception("Cédula es obligatoria.");
        if (nombre == null || nombre.trim().isEmpty()) throw new Exception("Nombre es obligatorio.");
        if (sexo == null || sexo.isEmpty())            throw new Exception("Seleccione sexo (M/F).");
        if (actividad == null || actividad.isEmpty())  throw new Exception("Seleccione actividad.");

        
        int edad;
        int alturaCm;
        double pesoKg;
        try {
            
            edad = (edadValor instanceof Integer) ? (Integer) edadValor : Integer.parseInt(String.valueOf(edadValor).trim());
            alturaCm = Integer.parseInt(alturaTxt.trim());
            pesoKg = Double.parseDouble(pesoTxt.trim());
        } catch (Exception e) {
            throw new Exception("Revise que edad, altura y peso sean numéricos.");
        }
        if (edad <= 0)       throw new Exception("Edad debe ser mayor que 0.");
        if (alturaCm <= 0)   throw new Exception("Altura debe ser mayor que 0.");
        if (pesoKg <= 0)     throw new Exception("Peso debe ser mayor que 0.");

        
        if (dao.existeCedula(cedula.trim())) throw new Exception("Ya existe un paciente con esa cédula.");

        // Armar y guardar
        Paciente p = new Paciente();
        p.setCedula(cedula.trim());
        p.setNombre(nombre.trim());
        p.setEdad(edad);
        p.setSexo(sexo);                    
        p.setAltura((double) alturaCm);     
        p.setPeso(pesoKg);
        p.setActividadFisica(actividad);
        p.setFechaRegistro(new java.util.Date());

        dao.registrar(p);
    }

    public List<Paciente> listar() {
        return dao.listar();
    }

    // BUSCAR POR CÉDULA 
    public Paciente buscarPorCedula(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty())
            throw new Exception("Ingrese una cédula.");
        return dao.buscarPorCedula(cedula.trim());
    }

    // ACTUALIZAR 
    public void actualizar(Paciente p) throws Exception {
        if (p == null) throw new Exception("Datos inválidos.");
        if (vacio(p.getCedula()) || vacio(p.getNombre()))
            throw new Exception("Cédula y nombre son obligatorios.");
        if (p.getEdad() <= 0) throw new Exception("Edad debe ser mayor a 0.");
        if (p.getAltura() <= 0 || p.getPeso() <= 0)
            throw new Exception("Altura y peso deben ser positivos.");

        dao.actualizar(p);
    }

    private boolean vacio(String s) { return s == null || s.trim().isEmpty(); }

    public DefaultTableModel cargarTabla() {
        String[] columnas = {"Cédula", "Nombre", "Edad", "Sexo", "Peso", "Altura", "Actividad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Paciente> lista = dao.listar();
        for (Paciente p : lista) {
            Object[] fila = {
                p.getCedula(),
                p.getNombre(),
                p.getEdad(),
                p.getSexo(),
                p.getPeso(),
                p.getAltura(),
                p.getActividadFisica()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }
    
    
}
