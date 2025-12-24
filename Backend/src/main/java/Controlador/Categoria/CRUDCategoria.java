/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Categoria;

import DB.Categoria.CategoriaDB;
import Modelo.Categoria;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class CRUDCategoria {
    private CategoriaDB categoriaDB = new CategoriaDB();
    
    public int crearCategoria(JSONObject json){
        
        String idCategoria = json.getString("idCategoria");
        String nombreCategoria = json.getString("nombreCategoria");;
        
        Categoria categoriaNueva = new Categoria(idCategoria, nombreCategoria);
        
        if(categoriaDB.existeCategoria(idCategoria) == true){
            return 400;
        }   
        
        categoriaDB.crearCategoria(categoriaNueva);
        return 200;
    }
    
    public int editarCategoria(JSONObject json){
        
        String idCategoria = json.getString("idCategoria");
        String nombreCategoria = json.getString("nombreCategoria");
        
        Categoria catgoria = new Categoria(idCategoria, nombreCategoria);
        
        categoriaDB.editarCategoria(catgoria);
        return 200;
    }
    
    public Categoria obtenerCategoriaPorId(String idCategoria){
        return categoriaDB.obtenerCategoria(idCategoria);
    }
    
    public void eliminarCategoria(String idCategoria){
        categoriaDB.eliminarCategoria(idCategoria);
    }
}
