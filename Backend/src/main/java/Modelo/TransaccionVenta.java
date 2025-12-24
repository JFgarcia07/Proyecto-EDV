/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author jfgarcianata
 */
public class TransaccionVenta {
    private String idUsuario;
    private String idJuego;
    private Date fechaCompra;
    private double montoTotal;
    private double comisionPlataforma;
    private double ingresoNetoEmpresa;

    public TransaccionVenta(String idUsuario, String idJuego, Date fechaCompra, double montoTotal, double comisionPlataforma, double ingresoNetoEmpresa) {
        this.idUsuario = idUsuario;
        this.idJuego = idJuego;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.comisionPlataforma = comisionPlataforma;
        this.ingresoNetoEmpresa = ingresoNetoEmpresa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getComisionPlataforma() {
        return comisionPlataforma;
    }

    public void setComisionPlataforma(double comisionPlataforma) {
        this.comisionPlataforma = comisionPlataforma;
    }

    public double getIngresoNetoEmpresa() {
        return ingresoNetoEmpresa;
    }

    public void setIngresoNetoEmpresa(double ingresoNetoEmpresa) {
        this.ingresoNetoEmpresa = ingresoNetoEmpresa;
    }
    
    
}
