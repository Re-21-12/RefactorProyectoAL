package com.mycompany.proyectoal;



import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Pintar {

    public Scene createScene() {
        // Crear un contenedor raíz
        StackPane root = new StackPane();

        // Crear un componente de etiqueta
        Label helloLabel = new Label("Hello, JavaFX!");

        // Añadir el componente al contenedor
        root.getChildren().add(helloLabel);

        // Crear y devolver una escena
        return new Scene(root, 300, 250);
    }
}
