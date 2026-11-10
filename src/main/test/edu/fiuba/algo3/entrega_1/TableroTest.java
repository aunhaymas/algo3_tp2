package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CoordenadaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionVaciaException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    private Tablero crearTableroBase(){
        Casilla[][] casillas = new Casilla[8][8];
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++){
                casillas[f][c] = new Casilla(new Suelo());
            }
        }
        return new Tablero(casillas);
    }
    @Test
    public void tableroSabeSusLimites() {
        Tablero tablero = crearTableroBase();
        assertFalse(tablero.esPosicionValida(new Coordenada(8,8)));
        assertFalse(tablero.esPosicionValida(new Coordenada(0,8)));
        assertFalse(tablero.esPosicionValida(new Coordenada(8,0)));

        assertTrue(tablero.esPosicionValida(new Coordenada(0,0)));
        assertTrue(tablero.esPosicionValida(new Coordenada(3,3)));
        assertTrue(tablero.esPosicionValida(new Coordenada(7,7)));
    }
    @Test
    public void tableroColocaPiezaEnUnaCoordenadaYOcupaEsaCasilla() {
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        Tablero tablero = crearTableroBase();
        Coordenada coordenada = new Coordenada(1,1);

        tablero.colocarPieza(moquitoDePieza,coordenada);
        assertFalse(tablero.estaLibre(coordenada));
    }
    @Test
    public void tableroCordenadaSinPiezaEstaLibre() {
        Tablero tablero = crearTableroBase();
        Coordenada coordenada = new Coordenada(1,1);
        assertTrue(tablero.estaLibre(coordenada));
    }
    @Test
    public void colocarPiezaEnUnaCoordenadaInvalidaLanzaException() {
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        Tablero tablero = crearTableroBase();
        Coordenada coordenada_invalida = new Coordenada(-1,-1);

        assertThrows(CoordenadaInvalidaException.class, () ->{
            tablero.colocarPieza(moquitoDePieza,coordenada_invalida);
        });
    }
    @Test
    public void colocarPiezaEnUnaCoordenadaOcupadaLanzaException() {
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        Pieza piezaOcupante = Mockito.mock(Pieza.class);
        Tablero tablero = crearTableroBase();
        Coordenada coordenada = new Coordenada(0,0);
        tablero.colocarPieza(piezaOcupante,coordenada);

        assertThrows(PosicionOcupadaException.class, () ->{
            tablero.colocarPieza(moquitoDePieza,coordenada);
        });
    }
    @Test
    public void tableroMueveUnaPiezaAotraPosicion() {
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        Tablero tablero = crearTableroBase();

        Coordenada origen = new Coordenada(0,0);
        Coordenada destino = new Coordenada(1,1);
        tablero.colocarPieza(moquitoDePieza,origen);
        tablero.moverPieza(origen,destino);
        assertTrue(tablero.estaLibre(origen));
        assertFalse(tablero.estaLibre(destino));
    }
    @Test
    public void moverPiezaDesdeUnaCoordenadaVaciaLanzaException() {
        Tablero tablero = crearTableroBase();
        Coordenada origen = new Coordenada(0,0);
        Coordenada destino = new Coordenada(1,1);
        assertThrows(PosicionVaciaException.class, () ->{
            tablero.moverPieza(origen,destino);
        });
    }
}