package edu.fiuba.algo3.vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ClickEnMechEventHandler implements EventHandler<ActionEvent> {

    VistaPieza pieza;
    VistaTablero tablero;

    public ClickEnMechEventHandler(VistaPieza pieza, VistaTablero tablero) {
        this.pieza = pieza;
        this.tablero = tablero;
    }

    @Override 
    public void handle(ActionEvent actionEvent) {
        pieza.seleccionar();
        // Falta indicar: Todas las demas piezas pasan a estar deseleccionadas.
        tablero.dibujar();
    }

}