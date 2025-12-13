/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jfgarcianata
 */
public class DBconnectionSingleton {
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "DB_tienda_digital";
    private static final String USER_NAME = "admin_db";
    private static final String PASSWORD = "Admin@123";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;
    
    private static DBconnectionSingleton instance;
    
    private Connection connection;

    private DBconnectionSingleton() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("CONEXION EXITOSA A LA BASE DE DATOS");
        } catch (SQLException e) {
            System.out.println("Error al conectarse");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
     public static DBconnectionSingleton getInstance() {
        if (instance == null) {
            instance = new DBconnectionSingleton();
        }
        return instance;
    }
}
