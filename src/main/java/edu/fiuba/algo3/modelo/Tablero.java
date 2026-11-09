package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private final Map<Coordenada, Casilla> casillas;
    private static final int LARGO_PREDEFINIDO = 8;
    private static final int ANCHO_PREDEFINIDO = 8;

    public Tablero() {
        this.casillas = new HashMap<>();
    }

    public void agregarCasilla(Coordenada coordenada, Casilla casilla) {
        this.casillas.put(coordenada, casilla);
    }

    public Casilla obtenerCasilla(Coordenada coordenada) {
        return casillas.get(coordenada);
    }
}
