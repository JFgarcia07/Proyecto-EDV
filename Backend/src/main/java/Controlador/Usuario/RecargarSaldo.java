/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Usuario;

import DB.Usuario.BilleteraDB;

/**
 *
 * @author jfgarcianata
 */
public class RecargarSaldo {
    public void recargarSaldo(String idUsuario, double valorIngresado){
        BilleteraDB billeteraDB = new BilleteraDB();
       
        if(!billeteraDB.existeBilletera(idUsuario)){
            billeteraDB.crearBilletera(idUsuario);
        }
        
        billeteraDB.recargarSaldo(idUsuario, valorIngresado);
    }
}
