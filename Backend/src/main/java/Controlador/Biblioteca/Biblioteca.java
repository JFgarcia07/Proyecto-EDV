/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Biblioteca;

import DB.Biblioteca.BibliotecaDB;
import DB.SesionGlobal;
import Modelo.Juego;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
public class Biblioteca {
    BibliotecaDB biblioDB = new BibliotecaDB();
    
    public void llenarDatosBiblioteca(String idJuego){
        biblioDB.llenarBiblioteca(SesionGlobal.idPersonal, idJuego);
    }
    
    public List<Juego> obtnerJuegosBiblioteca(String idUsuario){
        return biblioDB.obtenerJuegosBiblioteca(idUsuario);
    }
    
    public void instalarJuego(String idJuego){
        biblioDB.instalarJuego(SesionGlobal.idPersonal, idJuego);
    }
}
