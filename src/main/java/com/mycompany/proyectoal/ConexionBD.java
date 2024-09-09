package com.mycompany.proyectoal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:sqlserver://127.0.0.1:1434;databaseName=proyecto;encrypt=false"; // Cambia localhost y el nombre de la base de datos
    private static final String USER = "Automatas";  // Usuario de la base de datos
    private static final String PASSWORD = "lenguajes";  // Contraseña de la base de datos
    public ConexionBD() {
    }

    private static Connection connection = null;



    // Método estático para obtener la conexión
    public static Connection getConnection() throws SQLException{
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

        }
    }
}
