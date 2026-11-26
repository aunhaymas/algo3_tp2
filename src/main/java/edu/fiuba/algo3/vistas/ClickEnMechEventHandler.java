package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Coordenada;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ClickEnMechEventHandler implements EventHandler<MouseEvent> {

    VistaPieza pieza;
    Coordenada coordenada;
    Observable observable;

    public ClickEnMechEventHandler(VistaPieza pieza, Coordenada coordenada, Observable observable) {
        this.pieza = pieza;
        this.coordenada = coordenada;
        this.observable = observable;
    }

    @Override 
    public void handle(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        if (enRangoX(x) && enRangoY(y))  {
            observable.deseleccionarTodos(pieza);
            observable.notificarTodos();
        }
    }

    private boolean enRangoX(double x) {
        return (x > coordenadaXMin()) && (x < coordenadaXMax());
    }

    private boolean enRangoY(double y) {
        return (y > coordenadaYMin()) && (y < coordenadaYMax());
    }

    private int coordenadaXMin() {
        return coordenada.getX() * 60;
    }

    private int coordenadaXMax() {
        return (coordenada.getX()+1) * 60;
    }

    private int coordenadaYMin() {
        return coordenada.getY() * 60;
    }

    private int coordenadaYMax() {
        return (coordenada.getY()+1) * 60;
    }

}