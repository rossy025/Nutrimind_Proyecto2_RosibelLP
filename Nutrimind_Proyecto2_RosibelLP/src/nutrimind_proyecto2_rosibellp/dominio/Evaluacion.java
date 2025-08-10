/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dominio;

import java.util.Date;

/**
 *
 * @author carri
 */
public class Evaluacion {
    private int idEvaluacion;
    private int idPaciente;
    private double imc;
    private String categoriaImc; // BAJO PESO, NORMAL, SOBREPESO, OBESIDAD I,  OBESIDAD II y III
    private Date fechaEvaluacion;

    /**
     * @return the idEvaluacion
     */
    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    /**
     * @param idEvaluacion the idEvaluacion to set
     */
    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    /**
     * @return the idPaciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the imc
     */
    public double getImc() {
        return imc;
    }

    /**
     * @param imc the imc to set
     */
    public void setImc(double imc) {
        this.imc = imc;
    }

    /**
     * @return the categoriaImc
     */
    public String getCategoriaImc() {
        return categoriaImc;
    }

    /**
     * @param categoriaImc the categoriaImc to set
     */
    public void setCategoriaImc(String categoriaImc) {
        this.categoriaImc = categoriaImc;
    }

    /**
     * @return the fechaEvaluacion
     */
    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    /**
     * @param fechaEvaluacion the fechaEvaluacion to set
     */
    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
    
    
    
}
