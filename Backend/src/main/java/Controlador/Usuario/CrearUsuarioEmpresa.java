/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Usuario;

import DB.Empresa.EmpresaDB;
import DB.Usuario.UsuarioDB;
import Modelo.Usuario;
import java.sql.Date;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class CrearUsuarioEmpresa {
    public int crearUsuarioEmpresa(JSONObject json, String idEmpresa){
        UsuarioDB userDB = new UsuarioDB();
        EmpresaDB empresaDB = new EmpresaDB();
    
        String idUsuario = json.getString("idUsuario");
        String nombreUsuario = json.getString("nombreUsuario");
        String email = json.getString("email");
        String password = json.getString("password");
        Date fecha = dateSQL(json.getString("fechaNacimiento"));
        String idRol = "EMPRE";
        
        Usuario empresaUser = new Usuario();
        empresaUser.setIdUsuario(idUsuario);
        empresaUser.setNombreUsuario(nombreUsuario);
        empresaUser.setEmail(email);
        empresaUser.setPassword(password);
        empresaUser.setFechaNacimiento(fecha);
        empresaUser.setIdRol(idRol);
        
        if(userDB.existeUsuario(empresaUser) == true) {
            return 400;
        } 
        
        if(empresaDB.existeUsuarioEmpresa(idUsuario) == true){
            return 401;
        }
        
        
        userDB.crearUsuario(empresaUser);
        empresaDB.UsuarioEmpresa(idEmpresa, idUsuario);
        return 200;
    }
    
    private java.sql.Date dateSQL(String fecha){
        if(fecha == null || fecha.isEmpty()){
            return null;
        }
        return java.sql.Date.valueOf(fecha);
    }
}
