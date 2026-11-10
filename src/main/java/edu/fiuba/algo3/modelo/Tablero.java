package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CoordenadaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;

public class Tablero {
    //private final Map<Coordenada, Casilla> casillas;
    private Casilla[][] casillas;
    private static final int LARGO_PREDEFINIDO = 8;
    private static final int ANCHO_PREDEFINIDO = 8;

    public Tablero(){this.casillas = new Casilla[ANCHO_PREDEFINIDO][LARGO_PREDEFINIDO]; }
    public Tablero(Casilla[][] casillas) {
        this.casillas = new Casilla[ANCHO_PREDEFINIDO][LARGO_PREDEFINIDO];
        for (int y = 0; y < LARGO_PREDEFINIDO; y++) {
            for (int x = 0; x < ANCHO_PREDEFINIDO; x++) {
                if (casillas[y][x]==null) {
                    //Deberia lanzar un error
                    }
                this.casillas[y][x] = casillas[y][x];
                }
            }
    }

    public void agregarCasilla(Coordenada coordenada, Casilla casilla) {
        this.casillas[coordenada.getY()][coordenada.getX()] = casilla;
    }

    public Casilla obtenerCasilla(Coordenada coordenada) {
        return casillas[coordenada.getY()][coordenada.getX()];
    }

    public boolean esPosicionValida(Coordenada coordenada) {
        int x = coordenada.getX();
        int y = coordenada.getY();
        if (x<0 || y<0) {
            return false;
        }
        return (coordenada.getY()< ANCHO_PREDEFINIDO && coordenada.getX() < LARGO_PREDEFINIDO);
    }

    public void colocarPieza(Pieza pieza, Coordenada coordenada) {
        if (!this.esPosicionValida(coordenada))
        {
            throw new CoordenadaInvalidaException("La coordenada esta fuera de los limites del tablero!");
        }
        if (!this.estaLibre(coordenada)){
            throw new PosicionOcupadaException("La posicion ya esta siendo ocupada");
        }
        this.obtenerCasilla(coordenada).recibirPieza(pieza);
    }

    public boolean estaLibre(Coordenada coordenada) {
        return obtenerCasilla(coordenada).estaLibre();
    }

}
