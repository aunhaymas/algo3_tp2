package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;
import edu.fiuba.algo3.modelo.excepciones.CoordenadaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;
import edu.fiuba.algo3.modelo.excepciones.PosicionVaciaException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class TableroTest {

    @Test
    public void EsPosicionValidaRechazaCoordenadasFueraDelTablero8x8() {

        Tablero tablero = new Tablero();

        assertFalse(tablero.esPosicionValida(new Coordenada(-1, 0)));
    }

    @Test
    public void ColocarPiezaModificaElEstadoDeLaCasilla() {

        Tablero tablero = new Tablero();
        Coordenada posicion = new Coordenada(3, 3);
        Unidad mech = new Mech();

        tablero.colocarPieza(mech, posicion);

        assertEquals(mech, tablero.obtenerPiezaEn(posicion));

    }

    @Test
    public void SacarPiezaModificaElEstadoDeLaCasillaYQuedaLibre() {
        
        Tablero tablero = new Tablero();
        Coordenada posicion = new Coordenada(3, 3);
        Unidad mech = new Mech();

        tablero.colocarPieza(mech, posicion);

        tablero.quitarPieza(posicion);

        assertTrue(tablero.estaLibre(posicion));

    }

    @Test
    public void SacarUnaPiezaDeUnaCasillaDevuelveLaPiezaCorrectamente() {
        
        Tablero tablero = new Tablero();
        Coordenada posicion = new Coordenada(3, 3);
        Unidad mech = new Mech();

        tablero.colocarPieza(mech, posicion);

        Pieza piezaSacada = tablero.quitarPieza(posicion);

        assertEquals(mech, piezaSacada);

    }

    @Test
    public void MoverPiezaLiberaElOrigen() {
        
        Tablero tablero = new Tablero();
        Coordenada origen = new Coordenada(1, 1);
        Coordenada destino = new Coordenada(2, 1);
        Unidad unidad = new Mech();
        
        tablero.colocarPieza(unidad, origen);

        tablero.moverPieza(origen, destino);

        assertTrue(tablero.estaLibre(origen));
      
    }

    @Test
    public void MoverPiezaLlegaAlDestino() {
        
        Tablero tablero = new Tablero();
        Coordenada origen = new Coordenada(1, 1);
        Coordenada destino = new Coordenada(2, 1);
        Unidad unidad = new Mech();
        
        tablero.colocarPieza(unidad, origen);

        tablero.moverPieza(origen, destino);

        assertEquals(unidad, tablero.obtenerPiezaEn(destino));
      
    }

    @Test
    public void MoverPiezaOcupaElDestino() {
        
        Tablero tablero = new Tablero();
        Coordenada origen = new Coordenada(1, 1);
        Coordenada destino = new Coordenada(2, 1);
        Unidad unidad = new Mech();
        
        tablero.colocarPieza(unidad, origen);

        tablero.moverPieza(origen, destino);

        assertFalse(tablero.estaLibre(destino));
      
    }

    @Test
    public void testMoverPiezaACasillaOcupadaLanzaPosicionOcupadaException() {
        
        Tablero tablero = new Tablero();
        Coordenada origen = new Coordenada(0, 0);
        Coordenada destino = new Coordenada(0, 1);
        
        tablero.colocarPieza(new Vek(), origen);
        tablero.colocarPieza(new Mech(), destino);

        assertThrows(PosicionOcupadaException.class, () -> {
            tablero.moverPieza(origen, destino);
        });
    }

}