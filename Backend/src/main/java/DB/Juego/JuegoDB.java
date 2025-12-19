/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Juego;

import DB.DBconnectionSingleton;
import Modelo.Juego;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfgarcianata
 */
public class JuegoDB {
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_CREAR_JUEGO = "INSERT INTO Juego (id_juego, id_empresa, titulo, descripcion, precio, recursos_minimos, "
            + "clasificacion_edad, fecha_lanzamiento, estado_venta, imagen) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public void crearJuego(Juego juego){
        try (PreparedStatement ps =  conn.prepareStatement(QUERY_CREAR_JUEGO)){
            ps.setString(1, juego.getIdJuego());
            ps.setString(2, juego.getIdEmpresa());
            ps.setString(3, juego.getTitulo());
            ps.setString(4, juego.getDescripcion());
            ps.setDouble(5, juego.getPrecio());
            ps.setString(6, juego.getRecursosMinimos());
            ps.setString(7, juego.getClasificacion());
            ps.setDate(8, juego.getFechaLanzamiento());
            ps.setBoolean(9, juego.isEstadoVenta());
            ps.setString(10, juego.getImagen());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error en la creacion del juego " + e.getMessage());
        }
    }
    
    private final String QUERY_BUSCAR_JUEGO_POR_ID = "SELECT COUNT(id_juego) FROM id_juego = ?";
    public boolean existeJuego(String idJuego){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_BUSCAR_JUEGO_POR_ID)){
            ps.setString(1, idJuego);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt("id_juego");
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar el juego " + e.getMessage());
        }
        return false;
    }
    
    private final String QUERY_LISTAR_JUEGO_POR_EMPRESA = "SELECT * FROM Juego WHERE id_empresa = ?";
    public List<Juego> listaJuegosPorEmpresa (String idEmpresa) {
        List<Juego> listaJuegos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_JUEGO_POR_EMPRESA)){
            ps.setString(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idJuego = rs.getString("id_juego");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String recursos = rs.getString("recursos_minimos");
                String clasificacion = rs.getString("clasificacion_edad");
                Date fechaLanzamiento = rs.getDate("fecha_lanzamiento");
                boolean estado = rs.getBoolean("estado_venta");
                String imagen = rs.getString("imagen");
                
                Juego juego = new Juego(idJuego, idEmpresa, titulo, descripcion, precio, recursos, clasificacion, fechaLanzamiento, estado, imagen);
                
                listaJuegos.add(juego);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar los juegos " + e.getMessage());
        }
        return listaJuegos;
    }
    
    private final String QUERY_BUSCAR_JUEGO_POR_TITULO = "SELECT * FROM Juego WHERE titulo = ?";
    public Juego buscarJuegoPorTitulo (String titulo) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_BUSCAR_JUEGO_POR_TITULO)) {
            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String idJuego = rs.getString("id_juego");
                String idEmpresa = rs.getString("id_empresa");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String recursos = rs.getString("recursos_minimos");
                String clasificacion = rs.getString("clasificacion_edad");
                Date fechaLanzamiento = rs.getDate("fecha_lanzamiento");
                boolean estado = rs.getBoolean("estado_venta");
                String imagen = rs.getString("imagen");
                
                Juego juego = new Juego(idJuego, idEmpresa, titulo, descripcion, precio, recursos, clasificacion, fechaLanzamiento, estado, imagen);
                
                return juego;
            }
        } catch (Exception e) {
            System.err.print("Error al buscar el juego por su nombre" + e.getMessage());
        }
        
        return null;
    }
    
    private final String QUERY_EDITAR_JUEGO = "UPDATE Juego SET titulo = ?, descripcion = ?, "
            + " precio = ?, recursos_minimos = ?, clasificacion_edad = ?, fecha_lanzamiento = ?, estado_venta = ?,imagen = ?"
            + " where id_juego = ?";
    public void editarJuego(Juego juego){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_EDITAR_JUEGO)){
            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getDescripcion());
            ps.setDouble(3, juego.getPrecio());
            ps.setString(4, juego.getRecursosMinimos());
            ps.setString(5, juego.getClasificacion());
            ps.setDate(6, juego.getFechaLanzamiento());
            ps.setBoolean(7, juego.isEstadoVenta());
            ps.setString(8, juego.getImagen());
            ps.setString(9, juego.getIdJuego());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al editar el juego " + e.getMessage());
        }
    }
    
    
    private final String OBTENER_JUEGO_POR_ID = "SELECT * FROM Juego WHERE id_juego = ?";
    public Juego obtenerJuegoPorId(String idJuego){
        try (PreparedStatement ps = conn.prepareStatement(OBTENER_JUEGO_POR_ID)){
            ps.setString(1, idJuego);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String idEmpresa = rs.getString("id_empresa");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String recursos = rs.getString("recursos_minimos");
                String clasificacion = rs.getString("clasificacion_edad");
                Date fecha = rs.getDate("fecha_lanzamiento");
                boolean estado = rs.getBoolean("estado_venta");
                String imagen = rs.getString("imagen");
                
                Juego juego = new Juego(idJuego, idEmpresa, titulo, descripcion, precio, recursos, clasificacion, fecha, estado ,imagen);
                
                return juego;
            }
        } catch (Exception e) {
            System.err.print("Error al obtner el juego por medio del id " + e.getMessage());
        }
        return null;
    }
}
