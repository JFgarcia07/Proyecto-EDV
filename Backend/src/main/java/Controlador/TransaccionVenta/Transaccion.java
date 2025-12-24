/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TransaccionVenta;

import Controlador.Biblioteca.Biblioteca;
import Controlador.Juego.CRUDJuego;
import DB.SesionGlobal;
import DB.TransaccionVenta.TransaccionVentaDB;
import DB.Usuario.BilleteraDB;
import DB.Usuario.UsuarioDB;
import Modelo.Juego;
import Modelo.TransaccionVenta;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import org.json.JSONObject;

/**
 *
 * @author jfgarcianata
 */
public class Transaccion {
    
    TransaccionVentaDB transaccion = new TransaccionVentaDB();
    BilleteraDB billetera = new BilleteraDB();
    CRUDJuego crud = new CRUDJuego();
    UsuarioDB userDB = new UsuarioDB();
    Biblioteca biblio = new Biblioteca();
    
    public int realizaCompra(JSONObject json){
        
        String idJuego = json.getString("idJuego");
        String idEmpresa = json.getString("idEmpresa");
        Date fechaCompra = dateSQL(json.getString("fechaCompra"));
        double precioVideojuego = json.getDouble("precio");
        
        if(billetera.obtenerSaldoActual(SesionGlobal.idPersonal) < precioVideojuego){
            return 400;
        }
        
        Juego juego = crud.obtenerJuegoPorId(idJuego);
        
        if(juego.getClasificacion().equalsIgnoreCase("M")){
            if(comprobarEdad() == false){
                return 403;
            }
        }
        
        double comisionPlataforma = CalcularComisionPlaforma(precioVideojuego, idEmpresa);
        double ingresoEmpresa = CalcularIngresoEmpresa(precioVideojuego, idEmpresa);
        
        TransaccionVenta venta = new TransaccionVenta(SesionGlobal.idPersonal, idJuego, fechaCompra, precioVideojuego, comisionPlataforma, ingresoEmpresa);
        
        transaccion.registrarVenta(venta);
        biblio.llenarDatosBiblioteca(idJuego);
        billetera.restarSaldo(SesionGlobal.idPersonal, precioVideojuego);
        return 200;
    } 
    
    private boolean comprobarEdad() {
        LocalDate fechaNacimiento = userDB.obtenerUsuarioPorId(SesionGlobal.idPersonal).get().getFechaNacimiento().toLocalDate();

        LocalDate hoy = LocalDate.now();

        return Period.between(fechaNacimiento, hoy).getYears() >= 18;

    }
    
    private double elegirPorcentaje(String idEmpresa){
        if(transaccion.obtnerComisioneEspecifica(idEmpresa) != 0){
            return transaccion.obtnerComisioneEspecifica(idEmpresa);
        } else {
            return transaccion.obtenerComisionGlobal();
        }
    }
    
    private double CalcularComisionPlaforma(double montoTotal, String idEmpresa){
        double comision = elegirPorcentaje(idEmpresa)*montoTotal;
        return comision;
    }
    
    private double CalcularIngresoEmpresa(double montoTotal, String idEmpresa){
        double ingresoNeto = montoTotal - CalcularComisionPlaforma(montoTotal, idEmpresa);
        return ingresoNeto;
    }
    
    private java.sql.Date dateSQL(String fecha){
        if(fecha == null || fecha.isEmpty()){
            return null;
        }
        return java.sql.Date.valueOf(fecha);
    }
    
    
}
