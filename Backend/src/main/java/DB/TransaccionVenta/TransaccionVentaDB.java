/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.TransaccionVenta;

import DB.DBconnectionSingleton;
import Modelo.TransaccionVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jfgarcianata
 */
public class TransaccionVentaDB {
    Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_REGISTRAR_TRANSACCION = "INSERT INTO Transaccion_venta (id_usuario, id_juego, fecha_compra, monto_total, comision_plataforma, ingreso_neto_empresa) "
            + "VALUES (?,?,?,?,?,?)";
    public void registrarVenta(TransaccionVenta transaccion){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_REGISTRAR_TRANSACCION)){
            ps.setString(1, transaccion.getIdUsuario());
            ps.setString(2, transaccion.getIdJuego());
            ps.setDate(3, transaccion.getFechaCompra());
            ps.setDouble(4, transaccion.getMontoTotal());
            ps.setDouble(5, transaccion.getComisionPlataforma());
            ps.setDouble(6, transaccion.getIngresoNetoEmpresa());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al registrar la transaccion " + e.getMessage());
        }
    }
    
    
    private static final String QUERY_EXISTE_COMPRA = "SELECT COUNT(*) AS total FROM Transaccion_venta WHERE id_usuario = ? AND id_juego = ?";
    public boolean haCompradoElJuegoAntes(String idUsuario, String idJuego) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_EXISTE_COMPRA)) {
            ps.setString(1, idUsuario);
            ps.setString(2, idJuego);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    return total > 0;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al verificar si ya ha comprado el juego: " + e.getMessage());
        }
        return false;
    }
    
    private final String QUERY_OBTENER_COMISION_ESPECIFICA = "SELECT (porcentaje_comision_especifica) FROM Empresa_desarrolladora "
            + " WHERE id_empresa = ?";
    public double obtnerComisioneEspecifica(String idEmpresa){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_COMISION_ESPECIFICA)){
            ps.setString(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("porcentaje_comision_especifica");
            }
        } catch (SQLException e) {
            System.err.print("Error  al obtener el porcetaje de comision especifica " + e.getMessage());
        }
        return 0;
    }
    
    private final String QUERY_OBTENER_COMISION_GLOBAL = "SELECT porcentaje FROM Comision_global";
    public double obtenerComisionGlobal(){
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_COMISION_GLOBAL)){
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("porcentaje");
            }
        } catch (SQLException e) {
            System.err.print("Error al obtner la comison global" + e.getMessage());
        }
        return 0;
    }
}
