module ProyectoAL {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    opens com.mycompany.proyectoal to javafx.fxml;
    exports com.mycompany.proyectoal;
}