/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Comision;

import DB.Comision.ComisionGlobalDB;

/**
 *
 * @author jfgarcianata
 */
public class ComisionGlob {
    ComisionGlobalDB comisionDB =  new ComisionGlobalDB();
    
    public int cambiarPorcentaje(double valor){
        try {
            if(valor < 0 || valor > 1){
                return 400;
            }
            comisionDB.cambiarPorcentaje(valor);
            return 200;
        } catch (NumberFormatException e) {
            System.err.print("El valor ingresado no es un numero");
            return 401;
        }
    }
}
