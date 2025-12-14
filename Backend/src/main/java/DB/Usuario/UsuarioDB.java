/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Usuario;

import DB.DBconnectionSingleton;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jfgarcianata
 */
public class UsuarioDB {
    private Connection connection = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_CREAR_USUARIO = "INSERT INTO Usuario (id_usuario, nombre_usuario, email, password, fecha_nacimiento, pais, telefono, id_rol) VALUES (?,?,?,?,?,?,?,?)";
    public void crearUsuario(Usuario user){
        try (PreparedStatement ps = connection.prepareStatement(QUERY_CREAR_USUARIO)){
            ps.setString(1, user.getIdUsuario());
            ps.setString(2, user.getNombreUsuario());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setDate(5, user.getFechaNacimiento());
            ps.setString(6, user.getPais());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getIdRol());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario");
            e.printStackTrace();
        }
    }
    
    private final String QUERY_BUSCAR_USUARIO_POR_ID = "SELECT COUNT(id_usuario) FROM Usuario WHERE id_usuario = ?"; 
    public boolean existeUsuario(Usuario user){
        try (PreparedStatement ps = connection.prepareStatement(QUERY_BUSCAR_USUARIO_POR_ID)){
            ps.setString(1, user.getIdUsuario());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            System.err.print("Erro al buscar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
