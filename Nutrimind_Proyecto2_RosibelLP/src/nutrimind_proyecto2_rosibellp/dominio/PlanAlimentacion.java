/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dominio;

/**
 *
 * @author carri
 */
public class PlanAlimentacion {
    private int idPlan;
    private String categoriaImc;
    private String actividadFisica;
    private String planTexto;

    /**
     * @return the idPlan
     */
    public int getIdPlan() {
        return idPlan;
    }

    /**
     * @param idPlan the idPlan to set
     */
    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
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
     * @return the actividadFisica
     */
    public String getActividadFisica() {
        return actividadFisica;
    }

    /**
     * @param actividadFisica the actividadFisica to set
     */
    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    /**
     * @return the planTexto
     */
    public String getPlanTexto() {
        return planTexto;
    }

    /**
     * @param planTexto the planTexto to set
     */
    public void setPlanTexto(String planTexto) {
        this.planTexto = planTexto;
    }
    
    
}
