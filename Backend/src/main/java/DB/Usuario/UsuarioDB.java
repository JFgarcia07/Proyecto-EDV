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
import java.util.Optional;

/**
 *
 * @author jfgarcianata
 */
public class UsuarioDB {
    private final Connection connection = DBconnectionSingleton.getInstance().getConnection();
    
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
    
    private final String QUERY_ENCONTRAR_USUARIO_POR_ID = "SELECT * FROM Usuario WHERE id_usuario = ?";
    public Optional<Usuario> obtenerUsuarioPorId(String idUsuario){
        try (PreparedStatement ps = connection.prepareStatement(QUERY_ENCONTRAR_USUARIO_POR_ID)){
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(idUsuario);
                user.setNombreUsuario(rs.getString("nombre_usuario"));
                user.setEmail(rs.getString("email"));
                user.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                user.setPais(rs.getString("pais"));
                user.setTelefono(rs.getString("telefono"));
                user.setIdRol(rs.getString("id_rol"));
                user.setSaldo(obtenerBilletera(idUsuario));
                user.setNombreRol(obtenerNombreRol(rs.getString("id_rol")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.err.print("Error al obtener usuario" + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    private final String QUERY_OBTENER_BILLETERA_DIGITAL = "SELECT saldo_actual FROM Billetera_digital WHERE id_usuario = ?";
    private double obtenerBilletera(String idUsuario){
        try (PreparedStatement ps  = connection.prepareStatement(QUERY_OBTENER_BILLETERA_DIGITAL)){
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("saldo_actual");
            }
        } catch (SQLException e) {
             System.err.print("Error al obtener billetera" + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    } 
    
    private String obtenerNombreRol (String idRol) {
        if (idRol.equalsIgnoreCase("ADMIN")) {
            return "Administrador de Sistema";
        } else if (idRol.equalsIgnoreCase("EMPRE")) {
            return "Usuario de Empresa";
        } else {
            return "Gamer";
        }
    }
    
    private final String QUERY_OBTENER_ID_ROL = "SELECT (id_rol) FROM Usuario WHERE id_usuario = ?";
    public String obtenerRol (String idUsuario) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_OBTENER_ID_ROL)){
            ps.setString(1,idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("id_rol");
            }
        } catch (SQLException e) {
            System.err.print("Error al obtener id rol " + e.getMessage());
        }
        return null;
    }

}
