/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Calificacion_comentario;

import DB.DBconnectionSingleton;
import Modelo.Comentario;
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
public class ComentarioDB {
    
    private final Connection conn = DBconnectionSingleton.getInstance().getConnection();

    private static final String QUERY_INSERT_COMENTARIO = "INSERT INTO Comentario (id_juego, id_usuario, comentario, comentario_padre, calificacion) VALUES (?,?,?,?,?)";
    public int guardarComentario(Comentario comentario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_INSERT_COMENTARIO)) {
            ps.setString(1, comentario.getIdJuego());
            ps.setString(2, comentario.getIdUsuario());
            ps.setString(3, comentario.getComentario());
            
            int comentarioPadre = comentario.getComentarioPadre();

            if (comentarioPadre == 0) {
                ps.setNull(4, java.sql.Types.INTEGER); 
                ps.setInt(5, comentario.getCalificacion());
            } else {
                ps.setInt(4, comentario.getComentarioPadre()); 
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al guardar comentario: " + e.getMessage());
            return -1;
        }
    }

    
    private final String QUERY_OBTENER_COMENTARIOS = "SELECT * FROM Comentario WHERE id_juego = ?";
    public List<Comentario> listComentarios (String idJuego) {
        List<Comentario> listaComentarios = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_COMENTARIOS)){
            ps.setString(1, idJuego);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int idComentario = rs.getInt("id_comentario");
                String idUsuario = rs.getString("id_usuario");
                String comentario = rs.getString("comentario");
                Integer comentarioPadre = (Integer) rs.getInt("comentario_padre");
                int calificacion = rs.getInt("calificacion");
                
                Comentario comentarios = new Comentario(idJuego, idUsuario, comentario, calificacion);
                comentarios.setComentarioPadre(comentarioPadre);
                comentarios.setIdComentario(idComentario);
                
                listaComentarios.add(comentarios);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar las comentarios " + e.getMessage());
        }
        return listaComentarios;
    }
    
    private final String QUERY_OBTENER_CALIFICACIONES_JUEGO = "SELECT (calificacion) FROM Comentario WHERE id_juego = ? AND comentario_padre IS NULL;";
    public List<Integer> listCalificacionesJuego(String idJuego){
        List<Integer> listaCalificaciones = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_CALIFICACIONES_JUEGO)){
            ps.setString(1, idJuego);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int calificacion = rs.getInt("calificacion");
                listaCalificaciones.add(calificacion);
            }
        } catch (SQLException e) {
            System.err.print("Error al obtner las calificaciones del juego " + e.getMessage());
        }
        return listaCalificaciones;
    }
    
}
