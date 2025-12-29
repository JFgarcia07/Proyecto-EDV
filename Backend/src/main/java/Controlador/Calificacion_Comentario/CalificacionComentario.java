package Controlador.Calificacion_Comentario;

import DB.Calificacion_comentario.ComentarioDB;
import DB.SesionGlobal;
import Modelo.Comentario;
import java.util.List;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfgarcianata
 */
public class CalificacionComentario {
    private final ComentarioDB comentarioDB = new ComentarioDB();
    
    public List<Comentario> listaComentarios(String idJuego){
        return comentarioDB.listComentarios(idJuego);
    }

    
    public void comentarJuego(JSONObject json){
        String idJuego = json.getString("idJuego");
        String idUsuario = SesionGlobal.idPersonal;
        String comentario = json.getString("comentario");
        int comentarioPadre = json.getInt("comentarioPadre");

        Comentario nuevo = new Comentario(idJuego, idUsuario, comentario, 0);
        nuevo.setComentarioPadre(comentarioPadre);

        if (comentarioPadre == 0) {
            int calificacion = json.getInt("calificacion"); 
            nuevo.setCalificacion(calificacion);
        }

        comentarioDB.guardarComentario(nuevo);
    }
}
