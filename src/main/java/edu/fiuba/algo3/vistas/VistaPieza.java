package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.veks.Vek;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaPieza implements Observer {
    Pieza pieza;
    Coordenada coordenada;
    Canvas canvas;
    Observable observable;
    boolean seleccionado;
    public VistaPieza(Pieza pieza, Coordenada coordenada, Canvas canvas, Observable observable) {
        this.pieza = pieza;
        this.coordenada = coordenada;
        this.canvas = canvas;
        this.observable = observable;
        this.seleccionado = false;
        observable.agregarObserver(this);
    }

    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (pieza instanceof PiezaEdificio) {
            gc.setFill(Color.BLACK);
            gc.fillRect(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 30, 30);
        }
        else if (pieza instanceof Mech) {
            gc.setFill(Color.RED);
            gc.fillOval(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 30, 30);
            if (seleccionado) {
                gc.setStroke(Color.ORANGE);
                gc.setLineWidth(5);
                gc.strokeRect(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 30, 30);
            }
        }
        else if (pieza instanceof Vek) {
            gc.setFill(Color.LIMEGREEN);
            gc.fillOval(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 30, 30);
        }
    }

    public int getCoordenadaX(Coordenada coordenada) {
        return (coordenada.getX())*60 + 15;
    }

    public int getCoordenadaY(Coordenada coordenada) {
        return (coordenada.getY())*60 + 15;
    }

    public void setearClickable() {
        ClickEnMechEventHandler handler = new ClickEnMechEventHandler(this, this.coordenada, this.observable);
        canvas.setOnMouseClicked(handler);
    }

    public void seleccionar() {
        seleccionado = true;
    }

    @Override 
    public void update() {
        dibujar();
    }

    @Override 
    public void deseleccionar(Observer o) {
        seleccionado = (this == o);
    }

}