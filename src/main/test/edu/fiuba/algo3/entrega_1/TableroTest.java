package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.hamcrest.CoreMatchers;
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

}