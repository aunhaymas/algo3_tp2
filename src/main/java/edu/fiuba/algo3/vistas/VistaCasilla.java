package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaCasilla {
    Casilla casilla;
    Coordenada coordenada;
    Canvas canvas;
    public VistaCasilla(Casilla casilla, Canvas canvas) {
        this.casilla = casilla;
        this.coordenada = casilla.getCoordenada();
        this.canvas = canvas;
    }

    public void dibujar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHEAT);
        gc.fillRect(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 60, 60);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeRect(getCoordenadaX(coordenada),getCoordenadaY(coordenada), 60, 60);
    }

    public int getCoordenadaX(Coordenada coordenada) {
        return (coordenada.getX() - 1)*60;
    }

    public int getCoordenadaY(Coordenada coordenada) {
        return (coordenada.getY() - 1)*60;
    }

}