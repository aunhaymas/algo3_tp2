package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaCasilla implements Observer {
    Casilla casilla;
    Coordenada coordenada;
    Canvas canvas;
    public VistaCasilla(Casilla casilla, Canvas canvas, Observable observable) {
        this.casilla = casilla;
        this.coordenada = casilla.getCoordenada();
        this.canvas = canvas;
        observable.agregarObserver(this);
        if (!casilla.estaLibre()) {
            VistaPieza vistapieza = new VistaPieza(casilla.obtenerPieza(), coordenada, canvas, observable);
        }
    }

    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(casilla.colorCasilla());
        gc.fillRect(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 60, 60);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeRect(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 60, 60);
    }

    public int getCoordenadaX(Coordenada coordenada) {
        return (coordenada.getX())*60;
    }

    public int getCoordenadaY(Coordenada coordenada) {
        return (coordenada.getY())*60;
    }

    @Override 
    public void update() {
        dibujar();
    }

    @Override 
    public void deseleccionar(Observer o) {
    }

}