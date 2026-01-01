/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.BannerPrincipal;

import DB.DBconnectionSingleton;
import Modelo.BannerPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
public class BannerDB {
    
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_GUARDAR_IMAGEN = "INSERT INTO Banner_principal (imagen, orden) VALUES (?,?)";
    public int agregarImagenBanner(BannerPrincipal banner){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_GUARDAR_IMAGEN)){
            ps.setString(1, banner.getImagen());
            ps.setInt(2, banner.getOrden());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al guardar la imagen en el banner " + e.getMessage());
        }
        return -1;
    }

    private final String QUERY_LISTAR_BANNER = "SELECT * FROM Banner_principal";
    public List<BannerPrincipal> listarBanner() {
        List<BannerPrincipal> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_BANNER)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                String imagen = rs.getString("imagen");
                int orden = rs.getInt("orden");
                
                BannerPrincipal banner = new BannerPrincipal(imagen, orden);
                lista.add(banner);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar banner: " + e.getMessage());
        }
        return lista;
    }

}
