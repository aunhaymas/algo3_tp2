package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.excepciones.CoordenadaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionVaciaException;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.Pieza;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tablero {
    
    private List<Casilla> casillas;
    private static final int LARGO_PREDEFINIDO = 8;
    private static final int ANCHO_PREDEFINIDO = 8;

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
        for (Casilla casilla : casillas) {
            if (casilla.tiene(pieza)) return casilla;
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
        casillaObjetivo.recibirPieza(pieza);
    }

    public boolean estaLibre(Coordenada coordenada) {
        return obtenerCasilla(coordenada).estaLibre();
    }

    public void moverPieza(Coordenada origen, Coordenada destino) {
        Casilla coordenadaOrigen = obtenerCasilla(origen);
        Casilla coordenadaDestino = obtenerCasilla(destino);
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

    public Trayectoria crearTrayectoria(Coordenada origen, Coordenada destino) {
        List<Coordenada> trayectoria = origen.trayectoriaA(destino);
        List<Casilla> casillas = trayectoria.stream().map(this::obtenerCasilla).collect(Collectors.toList());
        Direccion direccion = new Direccion(origen.direccionA(destino));
        return new Trayectoria(casillas,direccion );
    }

    public void procesarAtaque(Mech unidad, Coordenada destino) {
		Casilla origen = this.obtenerCasilla(unidad);
		Trayectoria trayectoria = this.crearTrayectoria(origen.getCoordenada(), destino);
		unidad.recorrer(trayectoria);
    }
}
