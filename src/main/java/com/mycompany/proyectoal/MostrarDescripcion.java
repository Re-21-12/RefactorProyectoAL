package com.mycompany.proyectoal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MostrarDescripcion {
    private List<String> declaraciones;

    public MostrarDescripcion() {
        this.declaraciones = new ArrayList<>();
    }

    public void indicarDescripciones(List<String> declaraciones) throws SQLException {
        this.declaraciones = declaraciones;
    }

    public ArrayList<ResultadoTabla> obtenerResultados(ArrayList<String> declaraciones) throws SQLException {
        ArrayList<ResultadoTabla> resultados = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        Connection connection = conexionBD.getConnection();
        System.out.println("---------------------------------------------------------------------------");
//TODO: Agregar una manera de que solo se muestre una consulta por cada simbolo o lexema encontrado en cada cadena como una interseccion pero entre todas
        for (String declaracion : declaraciones) {
            String[] lexemas = declaracion.split("=");
            String declaracion_variable = lexemas[0];
            String tipo_dato = declaracion_variable.split(" ")[0];
            tipo_dato = "%" + tipo_dato + "%";


            System.out.println(declaracion);
            String query = "SELECT Simbolo, Tipo, Descripcion FROM Alfabeto WHERE Simbolo LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tipo_dato);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String simbolo = resultSet.getString("Simbolo");
                String tipo = resultSet.getString("Tipo");
                String descripcion = resultSet.getString("Descripcion");
                System.out.printf("%-15s %-15s %-50s\n", simbolo, tipo, descripcion);
                resultados.add(new ResultadoTabla(simbolo, tipo, descripcion));
            }
        }

        return resultados;
    }
}
