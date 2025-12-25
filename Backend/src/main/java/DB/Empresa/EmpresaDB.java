/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Empresa;

import DB.DBconnectionSingleton;
import Modelo.Empresa;
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
public class EmpresaDB {
    
    private final Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_CREAR_EMPRESA = "INSERT INTO Empresa_desarrolladora (id_empresa, nombre_empresa, descripcion, porcentaje_comision_especifica, id_comision) "
            + "VALUES (?,?,?,?,?)";
    public void crearEmpresa(Empresa empresa){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_CREAR_EMPRESA)){
            ps.setString(1, empresa.getIdEmpresa());
            ps.setString(2, empresa.getNombreEmpresa());
            ps.setString(3, empresa.getDescripcion());
            ps.setDouble(4, empresa.getPorcentajeEspecifico());
            ps.setString(5, empresa.getIdPorcentajeGlobal());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al crear la empresa" + e.getMessage());
        }
    }
    
    private final String QUERY_BUSCAR_EMPRESA_POR_ID = "SELECT COUNT(nombre_empresa) FROM Empresa_desarrolladora WHERE id_empresa = ?";
    public boolean existeEmpresa(String idEmpresa){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_BUSCAR_EMPRESA_POR_ID)){
            ps.setString(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar la empresa" + e.getMessage());
        }
        return false;
    }
    
    private final String QUERY_LISTAR_EMPRESAS = "SELECT * FROM Empresa_desarrolladora";
    public List<Empresa> listarEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(QUERY_LISTAR_EMPRESAS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String idEmpresa = rs.getString("id_empresa");
                String nombreEmpresa = rs.getString("nombre_empresa");
                String descripcion = rs.getString("descripcion");
                double comisionEspecifica = rs.getDouble("porcentaje_comision_especifica");
                String idComision = rs.getString("id_comision");

                Empresa empresa = new Empresa(idEmpresa, nombreEmpresa, descripcion, comisionEspecifica, idComision);

                empresaList.add(empresa);
            }

        } catch (SQLException e) {
            System.err.print("Error al listar las empresas " + e.getMessage());
        }
        return empresaList;
    }
    
    private final String QUERY_USUARIO_EMPRESA = "INSERT INTO Usuario_empresa (id_empresa, id_usuario) VALUES (?,?)";
    public void UsuarioEmpresa (String idEmpresa, String idUsuario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_USUARIO_EMPRESA)){
            ps.setString(1, idEmpresa);
            ps.setString(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al llenar tabla usuario-empresa " + e.getMessage());
        }
    }
    
    private final String QUERY_EXISTE_USUARIO_EMPRESA =  "SELECT COUNT(id_usuario) FROM Usuario_empresa WHERE id_usuario = ?";
    public boolean existeUsuarioEmpresa (String idUsuario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_EXISTE_USUARIO_EMPRESA)){
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt("id_usuario");
                return count > 0;
            }
        } catch (Exception e) {
            System.err.print("Error al buscar usuario en usuario-empresa");
        }
        return false;
    }
    
    private final String QUERY_OBTENER_EMPRESA_POR_ID = "SELECT * FROM Empresa_desarrolladora WHERE id_empresa = ?";
    public Empresa obtenerJuegoPorID(String idEmpresa){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_EMPRESA_POR_ID)){
            ps.setString(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nombreEmpresa = rs.getString("nombre_empresa");
                String descripcion = rs.getString("descripcion");
                double porcentajeEspecifico = rs.getDouble("porcentaje_comision_especifica");
                String idComision = rs.getString("id_comision");
                
                Empresa empresa = new Empresa(idEmpresa, nombreEmpresa, descripcion, porcentajeEspecifico, idComision);
                
                return empresa;
            }
        } catch (Exception e) {
            System.err.print("Error al obtener la empresa " + e.getMessage());
        }
        return null;
    }
    
    private final String QUERY_ACTUALIZAR_EMPRESA = "UPDATE Empresa_desarrolladora "
            + "SET nombre_empresa = ?, descripcion = ?, porcentaje_comision_especifica = ? "
            + "WHERE id_empresa = ?";
    public int actualizarEmpresa(Empresa empresa){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_ACTUALIZAR_EMPRESA)){
            ps.setString(1, empresa.getNombreEmpresa());
            ps.setString(2, empresa.getDescripcion());
            ps.setDouble(3, empresa.getPorcentajeEspecifico());
            ps.setString(4, empresa.getIdEmpresa());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.err.print("Error al actualizar la empresa " + e.getMessage());
        }
        return -1;
    }
    
}
