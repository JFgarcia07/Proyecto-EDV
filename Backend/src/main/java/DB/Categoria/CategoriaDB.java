/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Categoria;

import DB.DBconnectionSingleton;
import Modelo.Categoria;
import Modelo.JuegoCategoria;
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
public class CategoriaDB {
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_CREAR_CATEGORIA = "INSERT INTO Categoria (id_categoria, nombre_categoria) VALUES (?,?)";
    public void crearCategoria(Categoria categoria){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_CREAR_CATEGORIA)){
            ps.setString(1, categoria.getIdCategoria());
            ps.setString(2, categoria.getNombreCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al crear una nueva categoria: " + e.getMessage());
        }
    }
    
    private final String QUERY_EXISTE_CATEGORIA = "SELECT COUNT(id_categoria) FROM Categoria WHERE id_categoria = ?";
    public boolean existeCategoria(String idCategoria){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_EXISTE_CATEGORIA)){
            ps.setString(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt("id_categoria");
                return count > 0;
            }
        } catch (SQLException e) {
             System.err.print("Error al buscar una categoria: " + e.getMessage());
        }
        return false;
    }
    
    private final String QUERY_LISTAR_CATEGORIAS = "SELECT * FROM Categoria";
    public List<Categoria> listaCategorias () {
        List<Categoria> listCategorias = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_CATEGORIAS)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idCategoria = rs.getString("id_categoria");
                String nombre = rs.getString("nombre_categoria");
                
                Categoria categoria = new Categoria(idCategoria, nombre);
                
                listCategorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar las categorias " + e.getMessage());
        }
        return listCategorias;
    }
    
    private final String QUERY_EDITAR_CATEGORIA = "UPDATE Categoria SET nombre_categoria = ? WHERE id_categoria = ?";
    public void editarCategoria(Categoria categoria){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_EDITAR_CATEGORIA)){
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al editar la categoria");
        }
    }
    
    private final String QUERY_OBTENER_CATEGORIA_POR_ID = "SELECT * FROM Categoria WHERE id_categoria = ?";
    public Categoria obtenerCategoria(String idCategoria){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_CATEGORIA_POR_ID)){
            ps.setString(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nombreCategoria = rs.getString("nombre_categoria");
                
                Categoria categoria = new Categoria(idCategoria, nombreCategoria);
                return categoria;
            }
        } catch (SQLException e) {
            System.err.print("Error al obtener la categoria " + e.getMessage());
        }
        return null;
    }
    
    private final String QUERY_ELIMINAR_CATEGORIA = "DELETE FROM Categoria WHERE id_categoria = ?";
    public void eliminarCategoria(String idCategoria){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_CATEGORIA)){
            ps.setString(1, idCategoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al eliminar la categoria " + e.getMessage());
        }
    }
    
    private final String QUERY_LISTAR_PROPUESTAS_CATEGORIA
            = "SELECT j.id_juego, e.nombre_empresa, j.titulo, jc.id_categoria, c.nombre_categoria "
            + "FROM Juego_categoria jc "
            + "INNER JOIN Juego j ON jc.id_juego = j.id_juego "
            + "INNER JOIN Empresa_desarrolladora e ON j.id_empresa = e.id_empresa "
            + "INNER JOIN Categoria c ON jc.id_categoria = c.id_categoria "
            + "WHERE jc.aprobado = 0";
    public List<JuegoCategoria> listaPropuestasCategoria () {
        List<JuegoCategoria> listPropuestas = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_PROPUESTAS_CATEGORIA)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idJuego = rs.getString("id_juego");
                String nombreEmpresa = rs.getString("nombre_empresa");
                String titulo = rs.getString("titulo");
                String idCategoria = rs.getString("id_categoria");
                String nombreCategoria = rs.getString("nombre_categoria");
                
                JuegoCategoria propuesta = new JuegoCategoria(idJuego, nombreEmpresa,titulo, idCategoria ,nombreCategoria);
                
                listPropuestas.add(propuesta);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar las propuestas de categorias");
        }
        return listPropuestas;
    }
    
    private final String QUERY_ACEPTAR_PROPUESTA_CATEGORIA = "UPDATE Juego_categoria SET aprobado = 1 WHERE id_juego = ? AND id_categoria = ?";
    public void aceptarPropuesta(String idJuego, String idCategoria) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_ACEPTAR_PROPUESTA_CATEGORIA)) {
            ps.setString(1, idJuego);
            ps.setString(2, idCategoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al aceptar propuesta: " + e.getMessage());
        }
    }

    private final String QUERY_RECHAZAR_PROPUESTA_CATEGORIA = "DELETE FROM Juego_categoria WHERE id_juego = ? AND id_categoria = ?";
    public void rechazarPropuesta(String idJuego, String idCategoria){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_RECHAZAR_PROPUESTA_CATEGORIA)){
            ps.setString(1, idJuego);
            ps.setString(2, idCategoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al rechazar propuesta: " + e.getMessage());
        }
    }
}
