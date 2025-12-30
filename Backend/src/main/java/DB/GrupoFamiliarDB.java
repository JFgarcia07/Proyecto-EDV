/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Modelo.GrupoFamiliar;
import Modelo.MiembroGrupo;
import Modelo.Usuario;
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
public class GrupoFamiliarDB {
 
    private Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_CREAR_GRUPO = "INSERT INTO Grupo_familiar (id_grupo, id_usuario_creador, nombre_grupo) VALUES (?,?,?)";
    public int crearGrupo(GrupoFamiliar grupo){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_CREAR_GRUPO)){
            ps.setString(1, grupo.getIdGrupo());
            ps.setString(2, grupo.getIdUsuarioCreador());
            ps.setString(3, grupo.getNombreGrupo());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al crear el grupo familiar :" + e.getMessage());
        }
        return -1;
    }
    
    private final String QUERY_AGREGAR_USUARIO_A_GRUPO = "INSERT INTO Miembro_grupo (id_grupo, id_usuario) VALUES (?,?)";
    public int agregarUsuarioGrupo(MiembroGrupo miembro){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_AGREGAR_USUARIO_A_GRUPO)){
            ps.setString(1, miembro.getIdGrupo());
            ps.setString(2, miembro.getIdUsuario());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al agregar al usuario al grupo " + e.getMessage());
        }
        return -1;
    }
    
    private final String QUERY_BUSCAR_USUARIO_EN_GRUPO = "SELECT COUNT(*) FROM Miembro_grupo WHERE id_grupo = ? AND id_usuario = ?";

    public boolean existeUsuarioEnGrupo(MiembroGrupo miembro) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_BUSCAR_USUARIO_EN_GRUPO)) {
            ps.setString(1, miembro.getIdGrupo());
            ps.setString(2, miembro.getIdUsuario());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar al usuario en el grupo " + e.getMessage());
        }
        return false;
    }

    
    private final String QUERY_OBTENER_CANITAD_INTEGRANTES_GRUPO = "SELECT COUNT(*) AS total FROM Miembro_grupo WHERE id_grupo = ?";
    public boolean hayCupoEnGrupo(String idGrupo, int cupoMax) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_CANITAD_INTEGRANTES_GRUPO)) {
            ps.setString(1, idGrupo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("total");
                return count < cupoMax;
            }
        } catch (SQLException e) {
            System.err.print("Error al verificar cupo " + e.getMessage());
        }
        return false;
    }


    private final String QUERY_LISTAR_GRUPOS = "SELECT * FROM Grupo_familiar WHERE id_usuario_creador = ?";
    public List<GrupoFamiliar> listGruposFamiliares(String idUsuarioCreador){
        List<GrupoFamiliar> listGrupos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_GRUPOS)){
            ps.setString(1, idUsuarioCreador);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idGrupo = rs.getString("id_grupo");
                String nombreGrupo = rs.getString("nombre_grupo");
                
                GrupoFamiliar grupo = new GrupoFamiliar(idGrupo, idUsuarioCreador, nombreGrupo);
                
                listGrupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.print("Error al listar los grupos " + e.getMessage());
        }
        return listGrupos;
    }
    
    private final String QUERY_LISTAR_USUARIOS_DE_GRUPO = "SELECT mg.id_grupo, mg.id_usuario, u.nombre_usuario FROM "
            + "Miembro_grupo mg "
            + "INNER JOIN Usuario u ON mg.id_usuario = u.id_usuario "
            + "WHERE mg.id_grupo = ?";
    public List<MiembroGrupo> listaUsuariosGrupo(String idGrupo){
        List<MiembroGrupo> listUsuarioGrupo = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_USUARIOS_DE_GRUPO)){
            ps.setString(1, idGrupo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idUsuario = rs.getString("id_usuario");
                String nombreUsuario = rs.getString("nombre_usuario");
                
                MiembroGrupo miembro = new MiembroGrupo(idGrupo, idUsuario);
                miembro.setNombreUsuario(nombreUsuario);
                
                listUsuarioGrupo.add(miembro);
            }
        } catch (SQLException e) {
            System.err.print("Error al cargar los usuarios del grupo " + e.getMessage());
        }
        return listUsuarioGrupo;
    }
    
    private final String QUERY_BUSCAR_USUARIO_POR_ID = "SELECT COUNT(id_usuario) FROM Usuario WHERE id_usuario = ?"; 
    public boolean existeUsuario(String idUsuario){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_BUSCAR_USUARIO_POR_ID)){
            ps.setString(1,idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar el usuario: " + e.getMessage());
        }
        return false;
    }

    private static final String QUERY_ES_CREADOR = "SELECT COUNT(*) FROM Grupo_familiar WHERE id_grupo = ? AND id_usuario_creador = ?";
    public boolean esUsuarioCreador(String idGrupo, String idUsuario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_ES_CREADOR)) {
            ps.setString(1, idGrupo);
            ps.setString(2, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);  
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar creador: " + e.getMessage());
        }
        return false;
    }

    private final String QUERY_ELIMINAR_MIEMBRO_GRUPO = "DELETE FROM Miembro_grupo WHERE id_grupo = ? AND id_usuario = ?";
    public int elimianarMiembro(MiembroGrupo miembro){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_MIEMBRO_GRUPO)){
            ps.setString(1, miembro.getIdGrupo());
            ps.setString(2, miembro.getIdUsuario());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al eliminar el miembro " + e.getMessage());
        }
        return -1;
    }
}
