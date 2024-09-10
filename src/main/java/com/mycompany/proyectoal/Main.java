package com.mycompany.proyectoal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO:
// 1. Buscar solucion a cuando una palabra que contiene la siguiente aperece mas corta ejemplo
// int numero = 1;
// int numerodos = 2;
// En este caso numero sale primero y causa un erorr??????????????????
// 2. Revisar division
public class Main extends Application {

    private ArrayList<String> declaraciones = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        ConexionBD conexionBD = new ConexionBD();
        Connection connection = conexionBD.getConnection();
        MostrarDescripcion mostrarDescripcion = new MostrarDescripcion();

        Lenguaje prueba = new Lenguaje();
        ArrayList<Respuesta> declaraciones_mapeadas = new ArrayList<>();

        // Image loading and setup
        Image image = new Image(getClass().getResourceAsStream("/Diagrama.jpeg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        StackPane imagePane = new StackPane();
        imagePane.getChildren().add(imageView);
        imageView.setVisible(false);

        Button showImageButton = new Button("Mostrar Imagen");
        showImageButton.setOnAction(event -> imageView.setVisible(!imageView.isVisible()));

        // Input components
        TextField inputField = new TextField();
        inputField.setPromptText("Ingresa una declaración");

        ListView<String> listView = new ListView<>();
        listView.setPrefHeight(100);
        listView.setPrefWidth(400);

        Button addButton = new Button("Agregar Declaración");
        addButton.setOnAction(event -> {
            String inputText = inputField.getText();
            if (!inputText.isEmpty()) {
                declaraciones.add(inputText);
                listView.getItems().add(inputText);
                inputField.clear();
            }
        });

        // Table setup
        TableView<ResultadoTabla> tableView = new TableView<>();
        TableColumn<ResultadoTabla, String> simboloColumn = new TableColumn<>("Simbolo");
        simboloColumn.setCellValueFactory(new PropertyValueFactory<>("simbolo"));

        TableColumn<ResultadoTabla, String> tipoColumn = new TableColumn<>("Tipo");
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<ResultadoTabla, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tableView.getColumns().addAll(simboloColumn, tipoColumn, descripcionColumn);

        Button uploadButton = new Button("Subir Declaraciones");
        uploadButton.setOnAction(event -> {
            tableView.getItems().clear();
            try {
                for (String declaracion : declaraciones) {
                    prueba.esUnaDeclaracionValida(declaracion);
                    System.out.println("declaracion: "+declaracion);
                    declaraciones_mapeadas.add(new Respuesta().filtrarDatos(declaracion));
                }
                Lenguaje resultado = new Lenguaje(declaraciones);
                resultado.esUnLenguajeValido(declaraciones_mapeadas);
                ArrayList<ResultadoTabla> resultados = mostrarDescripcion.obtenerResultados(declaraciones);
                tableView.getItems().addAll(resultados);

                // Deshabilitar controles de entrada después de cargar los resultados
                inputField.setDisable(true);
                addButton.setDisable(true);
                uploadButton.setDisable(true);
                listView.setDisable(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Layout setup
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(inputField, addButton, listView, uploadButton, showImageButton, imagePane, tableView);
        listView.setPrefWidth(400);  // Ajusta el ancho deseado
        listView.setPrefHeight(200);  // Ajusta la altura deseada
        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Resultados de Consultas con Imagen");
        primaryStage.show();
    }
}