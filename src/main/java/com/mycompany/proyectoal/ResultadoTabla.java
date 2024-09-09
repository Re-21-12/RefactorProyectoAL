package com.mycompany.proyectoal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResultadoTabla {
    private final StringProperty simbolo;
    private final StringProperty tipo;
    private final StringProperty descripcion;

    public ResultadoTabla(String simbolo, String tipo, String descripcion) {
        this.simbolo = new SimpleStringProperty(simbolo);
        this.tipo = new SimpleStringProperty(tipo);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public String getSimbolo() {
        return simbolo.get();
    }

    public void setSimbolo(String simbolo) {
        this.simbolo.set(simbolo);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
}
