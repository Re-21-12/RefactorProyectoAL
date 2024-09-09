package com.mycompany.proyectoal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=yourDatabase"; // Cambia localhost y el nombre de la base de datos
    private static final String USER = "yourUsername";  // Usuario de la base de datos
    private static final String PASSWORD = "yourPassword";  // Contraseña de la base de datos

    private static Connection connection = null;

    // Método estático para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Cargar el driver de SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (ClassNotFoundException e) {
                System.out.println("No se encontró el driver JDBC");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                e.printStackTrace();
            }
        }
    }
}
