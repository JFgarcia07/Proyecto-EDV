/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jfgarcianata
 */
public class Comentario {

    private String idJuego;
    private String idUsuario;
    private String comentario;
    private int comentarioPadre;
    private int idComentario;
    private int calificacion;

    public Comentario(String idJuego, String idUsuario, String comentario, int calificacion) {
        this.idJuego = idJuego;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getComentarioPadre() {
        return comentarioPadre;
    }

    public void setComentarioPadre(int comentarioPadre) {
        this.comentarioPadre = comentarioPadre;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

}
