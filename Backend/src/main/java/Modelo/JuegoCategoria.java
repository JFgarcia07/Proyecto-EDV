/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jfgarcianata
 */
public class JuegoCategoria {
    private String idJuego;
    private String nombreEmpresa;
    private String tituloJuego;
    private String idCategoria;
    private String nombreCategoria;

    public JuegoCategoria(String idJuego, String nombreEmpresa, String tituloJuego, String idCategoria, String nombreCategoria) {
        this.idJuego = idJuego;
        this.nombreEmpresa = nombreEmpresa;
        this.tituloJuego = tituloJuego;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTituloJuego() {
        return tituloJuego;
    }

    public void setTituloJuego(String tituloJuego) {
        this.tituloJuego = tituloJuego;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    
    
    
}
