package edu.fiuba.algo3.vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BotonEntrarEventHandler implements EventHandler<ActionEvent> {

    Stage stage;
    Scene escenaSiguiente;

    public BotonEntrarEventHandler(Stage stage, Scene escenaSiguiente) {
        this.stage = stage;
        this.escenaSiguiente = escenaSiguiente;
    }

    @Override 
    public void handle(ActionEvent actionEvent) {
        stage.setScene(escenaSiguiente);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(false);
    }

}