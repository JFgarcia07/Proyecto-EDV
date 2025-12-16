/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jfgarcianata
 */
public class LogInDB {
    public boolean validarLogIn(Usuario user){
        boolean credencialesCorrectas = false;
        Connection connection = DBconnectionSingleton.getInstance().getConnection();
        String sql = "SELECT COUNT(email) FROM Usuario WHERE email = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1); 
                if (count > 0){
                    //GUARDAMOS EL CARGO QUE TIENE LA PERSONA QUE ESTA INICIANDO SESION
                    SesionGlobal.idRol = getCargo(user);
                    SesionGlobal.idPersonal = getIdPersonal(user);
                    System.out.println("ROL:" + SesionGlobal.idRol);
                    credencialesCorrectas = true;
                    
                  
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en el logIn: " + e.getMessage());
            e.printStackTrace();
        }
        return credencialesCorrectas;
    }
    
    private String getCargo(Usuario user){
        Connection con = DBconnectionSingleton.getInstance().getConnection();
        String sql = "SELECT id_rol FROM Usuario WHERE email = ? AND password = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("id_rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private String getIdPersonal(Usuario user){
        Connection connection = DBconnectionSingleton.getInstance().getConnection();
        String sql = "SELECT id_usuario FROM Usuario WHERE email = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               return rs.getString("id_usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
