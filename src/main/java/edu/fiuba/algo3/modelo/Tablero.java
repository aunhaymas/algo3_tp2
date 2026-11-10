package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    //private final Map<Coordenada, Casilla> casillas;
    private Casilla[][] casillas;
    private static final int LARGO_PREDEFINIDO = 8;
    private static final int ANCHO_PREDEFINIDO = 8;

    public Tablero(){this.casillas = new Casilla[ANCHO_PREDEFINIDO][LARGO_PREDEFINIDO]; }
    public Tablero(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public void agregarCasilla(Coordenada coordenada, Casilla casilla) {
        this.casillas[coordenada.getY()][coordenada.getX()] = casilla;
    }

    public Casilla obtenerCasilla(Coordenada coordenada) {
        return casillas[coordenada.getY()][coordenada.getX()];
    }

    public boolean esPosicionValida(Coordenada coordenada) {
        return (coordenada.getY()< ANCHO_PREDEFINIDO && coordenada.getX() < LARGO_PREDEFINIDO);
    }
}
