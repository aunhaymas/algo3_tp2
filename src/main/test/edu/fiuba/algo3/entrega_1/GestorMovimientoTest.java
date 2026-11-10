package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorMovimientoTest {
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
    public void moverUnaUnidadEnElMapaNotificaAlaUnidad() {
        Tablero tablero = crearTableroBase();
        Unidad unidadMoquito = Mockito.mock(Unidad.class);
        Coordenada origen = new Coordenada(0, 0);
        Coordenada destino = new Coordenada(0, 1);
        tablero.colocarPieza(unidadMoquito, origen);

        GestorMovimiento gestor = new GestorMovimiento();
    }
}
