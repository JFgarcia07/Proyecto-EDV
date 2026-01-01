/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.BannerPrincipal;

import DB.BannerPrincipal.BannerDB;
import Modelo.BannerPrincipal;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class CRUDBanner {
    private BannerDB bannerDB = new BannerDB();
    
    public int agregarImagen(JSONObject json){
        
        String imagen = json.getString("imagen");
        int orden = json.getInt("orden");
        
        BannerPrincipal newBanner = new BannerPrincipal(imagen, orden);
        
        if(bannerDB.agregarImagenBanner(newBanner) > 0){
            return 200;
        }
        
        return 400;
    }
    
    public List<BannerPrincipal> listaImagenesBanner(){
        return bannerDB.listarBanner();
    }
}
