/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jfgarcianata
 */
public class ComisionGlobal {
    private String idComision;
    private double porcentaje;

    public ComisionGlobal(String idComision, double porcentaje) {
        this.idComision = idComision;
        this.porcentaje = porcentaje;
    }

    public String getIdComision() {
        return idComision;
    }

    public void setIdComision(String idComision) {
        this.idComision = idComision;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    
}
