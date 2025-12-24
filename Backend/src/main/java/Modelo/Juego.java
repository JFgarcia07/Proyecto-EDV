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
public class Juego {
    private String idJuego;
    private String idEmpresa;
    private String titulo;
    private String descripcion;
    private double precio;
    private String recursosMinimos;
    private String clasificacion;
    private Date fechaLanzamiento;
    private boolean estadoVenta;
    private String imagen;
    private String nombreEmpresa;

    public Juego(String idJuego, String idEmpresa, String titulo, String descripcion, double precio, String recursosMinimos, String clasificacion, Date fechaLanzamiento, boolean estadoVenta, String imagen) {
        this.idJuego = idJuego;
        this.idEmpresa = idEmpresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.recursosMinimos = recursosMinimos;
        this.clasificacion = clasificacion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.estadoVenta = estadoVenta;
        this.imagen = imagen;
    }

    public Juego() {
    }

    
    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRecursosMinimos() {
        return recursosMinimos;
    }

    public void setRecursosMinimos(String recursosMinimos) {
        this.recursosMinimos = recursosMinimos;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public boolean isEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(boolean estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
}
