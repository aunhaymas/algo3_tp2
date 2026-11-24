package edu.fiuba.algo3.vistas;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.scene.canvas.Canvas;

public class VistaTablero {
    Tablero tablero;
    Canvas canvas;
    // List<VistaCasilla> casillas;
    public VistaTablero(Tablero tablero, Canvas canvas) {
        this.tablero = tablero;
        this.canvas = canvas;
        // this.casillas = new ArrayList<VistaCasilla>();
    }

    public void dibujar() {
        for (Casilla casilla: tablero.iterarCasillas()) {
            VistaCasilla vistaCasilla = new VistaCasilla(casilla, canvas);
            vistaCasilla.dibujar();
        }
    }

}