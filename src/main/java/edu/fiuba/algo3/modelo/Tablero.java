package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CoordenadaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionVaciaException;
import java.util.ArrayList;
import java.util.List;

public class Tablero {
    
    private List<Casilla> casillas;
    private static final int LARGO_PREDEFINIDO = 8;
    private static final int ANCHO_PREDEFINIDO = 8;

    public Tablero(){
        this.casillas = new ArrayList<>();
        for (int i = 0; i < LARGO_PREDEFINIDO; i++) {
            for (int j = 0; j < ANCHO_PREDEFINIDO; j++) {
                Coordenada coordenada = new Coordenada(j, i);
                Casilla casilla = new Casilla(coordenada);
                this.agregarCasilla(casilla);
            }
        }
    }
    public Tablero(List<Casilla> casillas) {
        this.casillas = new ArrayList<>();
        for (Casilla casilla : casillas) {
            this.agregarCasilla(casilla);
        }
    }

    public void agregarCasilla(Casilla casilla) {
        this.casillas.add(casilla);
        casilla.setTablero(this);
    }

    public Casilla obtenerCasilla(Coordenada coordenada) {
        return this.casillas.stream()
            .filter(c -> c.getCoordenada().equals(coordenada))
            .findFirst()
            .orElseThrow(() -> new CoordenadaInvalidaException("La coordenada pedida está fuera de los límites 8x8 del tablero."));
            
    }

    public Casilla obtenerCasilla(Pieza pieza) {
        for (int y = 0; y < LARGO_PREDEFINIDO; y++) {
            for (int x = 0; x < ANCHO_PREDEFINIDO; x++) {
                Casilla casilla = casillas[y][x];
                if (casilla.tiene(pieza)) return casilla;
            }
        }
        throw new RuntimeException("Pieza no encontrada");
    }

    public boolean esPosicionValida(Coordenada coordenada) {
        int x = coordenada.getX();
        int y = coordenada.getY();
        return (x >= 0 && y >= 0 && x < LARGO_PREDEFINIDO && y < ANCHO_PREDEFINIDO);
    }

    public void colocarPieza(Pieza pieza, Coordenada coordenada) {
        
        if (!esPosicionValida(coordenada)) {
            throw new CoordenadaInvalidaException("La coordenada esta fuera de los limites del tablero!");
        }
        Casilla casillaObjetivo = obtenerCasilla(coordenada);
        if (!casillaObjetivo.estaLibre()) {
            throw new PosicionOcupadaException("No se puede colocar la pieza, la posición ya está ocupada.");
        }
        casillaObjetivo.recibirPieza(pieza);
    }

    public boolean estaLibre(Coordenada coordenada) {
        return obtenerCasilla(coordenada).estaLibre();
    }

    public void moverPieza(Coordenada origen, Coordenada destino) {
        Casilla coordenadaOrigen = obtenerCasilla(origen);
        Casilla coordenadaDestino = obtenerCasilla(destino);
        if (!coordenadaDestino.estaLibre()) {
            throw new PosicionOcupadaException("No se puede mover la pieza, la posición de destino está ocupada.");
        }
        coordenadaOrigen.moverContenidoA(coordenadaDestino);
    }

    public void atacarPosicion(Coordenada objetivo, Ataque ataque) {
        Casilla casillaObjetivo = obtenerCasilla(objetivo);
        casillaObjetivo.recibirAtaque(ataque);
    }

    public Pieza quitarPieza(Coordenada objetivo) {
        return obtenerCasilla(objetivo).removerPieza();
    }

    public Pieza obtenerPiezaEn(Coordenada coordenada) {
        return obtenerCasilla(coordenada).obtenerPieza();
    }
}
