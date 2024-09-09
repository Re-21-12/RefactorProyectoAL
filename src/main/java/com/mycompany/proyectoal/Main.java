package com.mycompany.proyectoal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        ConexionBD conexionBD = new ConexionBD();
        Connection connection = conexionBD.getConnection();
        MostrarDescripcion mostrarDescripcion = new MostrarDescripcion();

        ArrayList<String> declaraciones = new ArrayList<>();
        String Lenguaje_prueba1 = "%int variableuno = 1;";
        String Lenguaje_prueba2 = "%int variabledos = 2;";
        String respuesta = "%int respuesta = variableuno + variabledos;";

        declaraciones.add(Lenguaje_prueba1);
        declaraciones.add(Lenguaje_prueba2);
        declaraciones.add(respuesta);

        Lenguaje prueba = new Lenguaje();
        ArrayList<Respuesta> declaraciones_mapeadas = new ArrayList<>();

        for (String declaracion : declaraciones) {
            prueba.esUnaDeclaracionValida(declaracion);
            declaraciones_mapeadas.add(new Respuesta().filtrarDatos(declaracion));
        }

        Lenguaje resultado = new Lenguaje(declaraciones);
        resultado.esUnLenguajeValido(declaraciones_mapeadas);

        // Crear una tabla para mostrar los resultados
        TableView<ResultadoTabla> tableView = new TableView<>();
        TableColumn<ResultadoTabla, String> simboloColumn = new TableColumn<>("Simbolo");
        simboloColumn.setCellValueFactory(new PropertyValueFactory<>("simbolo"));

        TableColumn<ResultadoTabla, String> tipoColumn = new TableColumn<>("Tipo");
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<ResultadoTabla, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tableView.getColumns().addAll(simboloColumn, tipoColumn, descripcionColumn);

        // Convertir los resultados de la consulta en objetos ResultadoTabla
        ArrayList<ResultadoTabla> resultados = mostrarDescripcion.obtenerResultados(declaraciones);
        tableView.getItems().addAll(resultados);

        // Configurar la escena y el escenario
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Resultados de Consultas");
        primaryStage.show();

    }
}
