/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Biblioteca;

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
public class BibliotecaDB {
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_INSERTAR_DATOS_BIBLIOTECA = "INSERT INTO Biblioteca (id_usuario, id_juego) VALUES (?,?)";
    public void llenarBiblioteca(String idUsuario, String idJuego){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_DATOS_BIBLIOTECA)){
            ps.setString(1, idUsuario);
            ps.setString(2, idJuego);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al llenar los datos de la biblioteca " + e.getMessage());
        }
    }
    
    private static final String QUERY_OBTENER_JUEGOS_BIBLIOTECA = "SELECT j.id_juego, j.titulo, j.descripcion, j.precio, "
        + "j.recursos_minimos, j.clasificacion_edad, j.fecha_lanzamiento, j.imagen "
        + "FROM Biblioteca b "
        + "INNER JOIN Juego j ON b.id_juego = j.id_juego "
        + "WHERE b.id_usuario = ?";
    public List<Juego> obtenerJuegosBiblioteca(String idUsuario){
        List<Juego> listaJuegosBiblioteca = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_JUEGOS_BIBLIOTECA)){
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idJuego = rs.getString("id_juego");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String recursos = rs.getString("recursos_minimos");
                String clasificacion = rs.getString("clasificacion_edad");
                Date fecha = rs.getDate("fecha_lanzamiento");
                String imagen = rs.getString("imagen");
                
                Juego juego = new Juego();
                
                juego.setIdJuego(idJuego);
                juego.setTitulo(titulo);
                juego.setDescripcion(descripcion);
                juego.setPrecio(precio);
                juego.setRecursosMinimos(recursos);
                juego.setClasificacion(clasificacion);
                juego.setFechaLanzamiento(fecha);
                juego.setImagen(imagen);
                
                listaJuegosBiblioteca.add(juego);
                
            }
        } catch (SQLException e) {
            System.err.print("Error al obtener los juegos de la biblioteca " + e.getMessage());
        }
        return listaJuegosBiblioteca;
    }
}
