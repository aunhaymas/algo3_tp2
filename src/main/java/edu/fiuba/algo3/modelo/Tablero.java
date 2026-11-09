package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private final Map<Coordenada, Casilla> casillas;
    public Tablero() {
        this.casillas = new HashMap<>();
    }

    public void agregarCasilla(Coordenada coordenada, Casilla casilla) {
        this.casillas.put(coordenada, casilla);
    }
}
