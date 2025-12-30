/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.GrupoFamiliar;

import DB.GrupoFamiliarDB;
import DB.SesionGlobal;
import DB.Usuario.UsuarioDB;
import Modelo.GrupoFamiliar;
import Modelo.MiembroGrupo;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class GrupFamiliar {
    private GrupoFamiliarDB grupoDB = new GrupoFamiliarDB();
    private UsuarioDB userDB = new UsuarioDB();
    
    public int crearGrupo(JSONObject json){
        
        String idGrupo = json.getString("idGrupo");
        String idUsuarioCreador = SesionGlobal.idPersonal;
        String nombreGrupo = json.getString("nombreGrupo");
        
        GrupoFamiliar grupoFam = new GrupoFamiliar(idGrupo, idUsuarioCreador, nombreGrupo);
        
        if (grupoDB.crearGrupo(grupoFam) > 0) {
            return 200;
        }
        
        return 400;
    }
    
    public int agregarMiembroGrupo(JSONObject json){
       
        int cupoMax = 5;
        
        String idGrupo = json.getString("idGrupo");
        String idUsuario = json.getString("idUsuario");
        
        MiembroGrupo newMiembro = new MiembroGrupo(idGrupo, idUsuario);
        
        if(grupoDB.existeUsuarioEnGrupo(newMiembro) == true){
            return 401;
        }
        
        if(grupoDB.existeUsuario(idUsuario) == false){
            return 402;
        }
        
        if(grupoDB.hayCupoEnGrupo(idGrupo, cupoMax) == false){
            return 403;
        }
        
        if(grupoDB.esUsuarioCreador(idGrupo, idUsuario) == true) {
            return 405;
        }
        
        if(grupoDB.agregarUsuarioGrupo(newMiembro) > 0){
            return 200;
        }
        
        return 400;
    }
    
    public int eliminarMiembro(JSONObject json){
        String idGrupo = json.getString("idGrupo");
        String idUsuario = json.getString("idUsuario");
        
        MiembroGrupo miembro = new MiembroGrupo(idGrupo, idUsuario);
        
        if(grupoDB.elimianarMiembro(miembro) > 0){
            return 200;
        }
        
        return 400;
    }
    
    public List<GrupoFamiliar> listaGrupos(){
        return grupoDB.listGruposFamiliares(SesionGlobal.idPersonal);
    }
    
    public List<MiembroGrupo> listaMiembrosGrupo(String idGrupo){
        return grupoDB.listaUsuariosGrupo(idGrupo);
    }
    
}
