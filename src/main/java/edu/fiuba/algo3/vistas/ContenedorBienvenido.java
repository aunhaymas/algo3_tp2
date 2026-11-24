package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ContenedorBienvenido extends VBox {

    Scene escenaSiguiente;
    Stage stage;

    public ContenedorBienvenido(Stage stage, Scene escenaSiguiente) {
        super();
        this.stage = stage;

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25));

        Label label = new Label("Into the Beach!");
        label.setTextFill(Color.BLUEVIOLET);
        label.setFont(Font.font("Comic Sans", FontWeight.BOLD, 18));

        Button entrar = new Button();
        entrar.setText("Entrar");

        BotonEntrarEventHandler botonEntrarEventHandler = new BotonEntrarEventHandler(stage, escenaSiguiente);
        entrar.setOnAction(botonEntrarEventHandler);

        this.getChildren().setAll(label, entrar);
    }

}