/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jfgarcianata
 */
public class Empresa {
    private String idEmpresa;
    private String nombreEmpresa;
    private String descripcion;
    private double porcentajeEspecifico;
    private String idPorcentajeGlobal;

    public Empresa(String idEmpresa, String nombreEmpresa, String descripcion, double porcentajeEspecifico, String idPorcentajeGlobal) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
        this.porcentajeEspecifico = porcentajeEspecifico;
        this.idPorcentajeGlobal = idPorcentajeGlobal;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeEspecifico() {
        return porcentajeEspecifico;
    }

    public void setPorcentajeEspecifico(double porcentajeEspecifico) {
        this.porcentajeEspecifico = porcentajeEspecifico;
    }

    public String getIdPorcentajeGlobal() {
        return idPorcentajeGlobal;
    }

    public void setIdPorcentajeGlobal(String idPorcentajeGlobal) {
        this.idPorcentajeGlobal = idPorcentajeGlobal;
    }
}
