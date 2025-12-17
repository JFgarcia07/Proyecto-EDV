/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Usuario;

import DB.Usuario.UsuarioDB;
import Modelo.Usuario;
import java.sql.Date;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class CrearUsuario {
    
    /**
     * CODIGOS
     * TODO OK: 200
     * USUARIO YA EXISTENTE: 400
     * @param json
     * @return 
     */
    public int crearUsuario(JSONObject json){
        UsuarioDB userDB = new UsuarioDB();
        
        String idUsuario = json.getString("idUsuario");
        String nombreUsuario = json.getString("nombreUsuario");
        String email = json.getString("email");
        String password = json.getString("password");
        Date fecha = dateSQL(json.getString("fechaNacimiento"));
        String pais = json.getString("pais");
        String numeroTelefono = json.getString("telefono");
        String idRol = "GAMER";
        
        Usuario nuevoUser = new Usuario(idUsuario, nombreUsuario, email, password,
        fecha, pais, numeroTelefono, idRol);
        
        if(userDB.existeUsuario(nuevoUser) == true) {
            return 400;
        } 
        
        userDB.crearUsuario(nuevoUser);
        return 200;
    }
    
    private java.sql.Date dateSQL(String fecha){
        if(fecha == null || fecha.isEmpty()){
            return null;
        }
        return java.sql.Date.valueOf(fecha);
    }
}
