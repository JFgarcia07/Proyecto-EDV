/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Juego;

import DB.Categoria.CategoriaDB;
import DB.Juego.JuegoDB;
import Modelo.CategoriasAprobadas;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class JuegoCategorias {
    private final JuegoDB juegoDB = new JuegoDB();
    private final CategoriaDB categoriaDB = new CategoriaDB();
    
    public int asignarCategoriaJuego(JSONObject json) {
        String idJuego = json.getString("idJuego");
        String idCategoria = json.getString("idCategoria");
        
        juegoDB.asignarCategoriaJuego(idJuego, idCategoria);
        return 200;
    }
    
    public List<Modelo.JuegoCategoria> listaPropuesta(){
        return categoriaDB.listaPropuestasCategoria();
    }
    
    public void aceptarPropuesta(String idJuego, String idCategoria){
       categoriaDB.aceptarPropuesta(idJuego, idCategoria);
    }
    
    public void rechazarPropuesta(String idJuego, String idCategoria){
        categoriaDB.rechazarPropuesta(idJuego, idCategoria);
    }
    
    public List<CategoriasAprobadas> catoriasAprobadas(String idJuego){
        return juegoDB.listCategoriasAprobadas(idJuego);
    }
    
}
