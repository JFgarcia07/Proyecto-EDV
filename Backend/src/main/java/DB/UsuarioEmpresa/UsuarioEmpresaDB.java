/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.UsuarioEmpresa;

import DB.DBconnectionSingleton;
import Modelo.Usuario;
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
public class UsuarioEmpresaDB {
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_OBTENER_USUARIO_POR_EMPRESA = "SELECT u.id_usuario, u.nombre_usuario, u.email, u.fecha_nacimiento "
            + "FROM Usuario u INNER JOIN Usuario_empresa ue ON "
            + "u.id_usuario = ue.id_usuario WHERE ue.id_empresa = ? AND u.id_usuario != ?";
    public List<Usuario> obtenerUsuariosPorEmpresa (String idEmpresa, String idUser) {
        List<Usuario> listaUsuariosEmpresa = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_USUARIO_POR_EMPRESA)){
            ps.setString(1, idEmpresa);
            ps.setString(2, idUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idUsuario = rs.getString("id_usuario");
                String nombreUsuario = rs.getString("nombre_usuario");
                String email = rs.getString("email");
                Date fecha = rs.getDate("fecha_nacimiento");
                
                Usuario user = new Usuario();
                
                user.setIdUsuario(idUsuario);
                user.setNombreUsuario(nombreUsuario);
                user.setEmail(email);
                user.setFechaNacimiento(fecha);
                
                listaUsuariosEmpresa.add(user);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar los usuarios de empresa " + e.getMessage());
        }
        
        return listaUsuariosEmpresa;
    } 
}
