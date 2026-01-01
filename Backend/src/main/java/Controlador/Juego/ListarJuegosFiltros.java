/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Juego;

import DB.Calificacion_comentario.ComentarioDB;
import DB.Juego.JuegoDB;
import DB.TransaccionVenta.TransaccionVentaDB;
import Modelo.Juego;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
public class ListarJuegosFiltros {
    JuegoDB juegoDB = new JuegoDB();
    ComentarioDB comentarioDB = new ComentarioDB();
    TransaccionVentaDB transaccionDB = new TransaccionVentaDB();
    
    private final Date FECHA_BASE = java.sql.Date.valueOf("2020-01-01");
    private final double CALIFICACION_MINIMA = 4;
    private final int VENTAS_MINIMAS = 1;
    
    public List<Juego> listaJuegosRecientes(){
        
        List<Juego> listJuegosTodos = juegoDB.listarJuegos();
        List<Juego> listJuegosRecientes = new ArrayList<>();
        
        for(Juego juego : listJuegosTodos){
            if(juego.getFechaLanzamiento().after(FECHA_BASE)){
                listJuegosRecientes.add(juego);
            }
        }
        
        return listJuegosRecientes;
    }
    
    public List<Juego> listarJuegosGratuitos(){
        double precio = 0;
        return juegoDB.listarJuegosPorPrecio(precio);
    } 
    
    private double promedioCalificaciones(String idJuego) {

        List<Integer> calificaciones = comentarioDB.listCalificacionesJuego(idJuego);

        if (calificaciones == null || calificaciones.isEmpty()) {
            return 0.0; 
        }

        int suma = 0;
        for (int calificacion : calificaciones) {
            suma = suma + calificacion;
        }
        
        double promedio = (double) suma / calificaciones.size();

        return promedio;
    }
    
    public List<Juego> listarJuegosRecomendados(){
         
        List<Juego> listJuegosTodos = juegoDB.listarJuegos();
        List<Juego> listJuegosRecomendados = new ArrayList<>();
        
        for(Juego juego: listJuegosTodos){
            if(promedioCalificaciones(juego.getIdJuego()) >= CALIFICACION_MINIMA){
                if(transaccionDB.obtnerCantidadDeVentas(juego.getIdJuego()) >= VENTAS_MINIMAS){
                    listJuegosRecomendados.add(juego);
                }
            }
        }
        
        return listJuegosRecomendados;
    }
    
    public Juego buscarJuego(String entrada) {
        if (entrada == null) {
            return null;
        }

        String q = entrada.trim();
        if (q.isEmpty()) {
            return null;
        }

        Juego juego = juegoDB.buscarJuegoPorTitulo(q);
        if (juego != null) {
            return juego;
        }

        return juegoDB.obtenerJuegoPorId(q);
    }

    public List<Juego> juegoPorCategoria(String idCategoria){
        return juegoDB.listaJuegosPorCategoria(idCategoria);
    }
}
