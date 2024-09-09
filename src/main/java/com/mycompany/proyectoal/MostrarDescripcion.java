package com.mycompany.proyectoal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MostrarDescripcion {
    private List<String> declaraciones;

    //constructor
    public MostrarDescripcion() {
    }

    public void indicarDescripciones(List<String> declaraciones) throws SQLException {
        this.declaraciones = declaraciones;
        for (String declaracion : declaraciones) {
            consultarDescripcion(declaracion);
        }
    }

    private void consultarDescripcion(String declaracion) throws SQLException {
        ConexionBD conexionBD = new ConexionBD();
        Connection connection = conexionBD.getConnection();

        // Reemplazar caracteres no deseados y dividir la declaración
        declaracion = declaracion.replace("%", "");
        declaracion = declaracion.trim();
        String[] lexemas = declaracion.split(" ");
        String lexema = lexemas[0];
        // Verificar que lexemas tenga al menos un elemento
        if (lexemas.length == 0) {
            System.out.println("Declaración vacía o formato incorrecto.");
            return;
        }

        // Preparar el query con un parámetro
        //SELECT Simbolo,Tipo,Descripcion FROM Alfabeto WHERE Simbolo LIKE 'int' ;
        String query = "SELECT Simbolo, Tipo, Descripcion FROM Alfabeto WHERE Simbolo LIKE ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        // Configurar el parámetro del query
        preparedStatement.setString(1, "%"+lexema+"%");
//        System.out.println("Query: " + preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("---------------------------------------------------------------------------");
        while (resultSet.next()) {
            String simbolo = resultSet.getString("Simbolo");
            String tipo = resultSet.getString("Tipo");
            String descripcion = resultSet.getString("Descripcion");
            System.out.printf("%-15s %-15s %-50s\n", simbolo, tipo, descripcion);
        }

    }


}
