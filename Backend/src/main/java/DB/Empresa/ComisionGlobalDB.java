/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Empresa;

import DB.DBconnectionSingleton;
import Modelo.ComisionGlobal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
public class ComisionGlobalDB {
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_LISTAR_COMISIONES = "SELECT * FROM Comision_global";
    public List<ComisionGlobal> listarComisiones(){
        List<ComisionGlobal> listaComision = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_COMISIONES)){
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               String idComision =  rs.getString("id_comision");
               double porcentaje = rs.getDouble("porcentaje");
               
               ComisionGlobal comision = new ComisionGlobal(idComision, porcentaje);
               listaComision.add(comision);
           }
        } catch (Exception e) {
            System.err.print("Error al listar las comisiones " + e.getMessage());
        }
        return listaComision;
    }
    
    
}
