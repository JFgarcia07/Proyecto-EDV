/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Juego;

import DB.Juego.JuegoDB;
import Modelo.Juego;
import java.sql.Date;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class CRUDJuego {
    
    private JuegoDB juegoDB = new JuegoDB();
    
    public int crearJuego(JSONObject json) {
       
        String idJuego = json.getString("id_juego");
        String idEmpresa = json.getString("id_empresa");
        String titulo = json.getString("titulo");
        String descripcion = json.getString("descripcion");
        double precio = json.getDouble("precio");
        String recursos = json.getString("recursos_minimos");
        String clasificacion = json.getString("clasificacion_edad");
        Date fechaLanzamiento = dateSQL(json.getString("fecha_lanzamiento"));
        boolean estado = true;
        String imagen = json.getString("imagen");

        Juego juego = new Juego(idJuego, idEmpresa, titulo, descripcion, precio, recursos, clasificacion, fechaLanzamiento, estado, imagen);
    
        if(juegoDB.existeJuego(idJuego) == true){
            return 400;
        }
        
        juegoDB.crearJuego(juego);
        return 200;
    }
    
    private java.sql.Date dateSQL(String fecha){
        if(fecha == null || fecha.isEmpty()){
            return null;
        }
        return java.sql.Date.valueOf(fecha);
    }
    
    
    public int editarJuego(JSONObject json){
        String idJuego = json.getString("id_juego");
        String titulo = json.getString("titulo");
        String descripcion = json.getString("descripcion");
        double precio = json.getDouble("precio");
        String recursos = json.getString("recursos_minimos");
        String clasificacion = json.getString("clasificacion_edad");
        Date fechaLanzamiento = dateSQL(json.getString("fecha_lanzamiento"));
        boolean estado = json.getBoolean("estado_venta");
        String imagen = json.getString("imagen");

        Juego juego = new Juego();
        juego.setIdJuego(idJuego);
        juego.setTitulo(titulo);
        juego.setDescripcion(descripcion);
        juego.setPrecio(precio);
        juego.setRecursosMinimos(recursos);
        juego.setClasificacion(clasificacion);
        juego.setFechaLanzamiento(fechaLanzamiento);
        juego.setEstadoVenta(estado);
        juego.setImagen(imagen);
        
        juegoDB.editarJuego(juego);
        return 200;
    }
    
    
    public Juego obtenerJuegoPorId(String idJuego){
        return juegoDB.obtenerJuegoPorId(idJuego);
    }
}
