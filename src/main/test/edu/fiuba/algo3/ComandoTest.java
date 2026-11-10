package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class ComandoTest {
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
    public void ejecutarComandoMoverMuevePiezaEnTableroYNotificaAUnidad() {
        Tablero tablero = crearTableroBase();
        Unidad unidadMock = Mockito.mock(Unidad.class);
        Coordenada origen = new Coordenada(0, 0);
        Coordenada destino = new Coordenada(0, 1);
        tablero.colocarPieza(unidadMock, origen);

    }
}
