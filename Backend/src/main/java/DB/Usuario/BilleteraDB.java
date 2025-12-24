/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB.Usuario;

import DB.DBconnectionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jfgarcianata
 */
public class BilleteraDB {
    
    private final Connection conn = DBconnectionSingleton.getInstance().getConnection();
    
    private final String QUERY_CREAR_BILLETERA = "INSERT INTO Billetera_digital (id_usuario) VALUES (?)";

    public void crearBilletera(String idUsuario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_CREAR_BILLETERA)) {
            ps.setString(1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al crear la billetera del usuario" + e.getMessage());
        }
    }
    
    private final String QUERY_BUSCAR_BILLETERA = "SELECT COUNT(*) FROM Billetera_digital WHERE id_usuario = ?";

    public boolean existeBilletera(String idUsuario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_BUSCAR_BILLETERA)) {
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar la billetera del usuario" + e.getMessage());
        }
        return false;
    }
    
    private final String QUERY_RECARGAR_SALDO = "UPDATE Billetera_digital SET saldo_actual = ? WHERE id_usuario = ?";
    public void recargarSaldo(String idUsuario, double valor) {
        Double valorTotal = valor + obtenerSaldoActual(idUsuario);
        try (PreparedStatement ps = conn.prepareStatement(QUERY_RECARGAR_SALDO)) {
            ps.setDouble(1, valorTotal);
            ps.setString(2, idUsuario);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.print("Error al buscar recargar saldo" + e.getMessage());
        }
    }
    
    private final String QUERY_OBTENER_SALDO = "SELECT (saldo_actual) FROM Billetera_digital WHERE id_usuario = ?";
    public double obtenerSaldoActual(String idUsuario) {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_SALDO)) {
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("saldo_actual");
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar el saldo de la billetera: " + e.getMessage());
        }
        return 0;
    }
    
    private final String QUERY_RESTAR_SALDO = "UPDATE Billetera_digital SET saldo_actual = ? WHERE id_usuario = ?";
    public void restarSaldo(String idUsuario, double valorRestar){
        Double valorTotal = obtenerSaldoActual(idUsuario) - valorRestar;
        try (PreparedStatement ps = conn.prepareStatement(QUERY_RESTAR_SALDO)) {
            ps.setDouble(1, valorTotal);
            ps.setString(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Error al actulizar el saldo de la billetera " + e.getMessage());
        } 
    }
}
