package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordenadaTest {
    
    @Test
    public void coordenadaSeCreaConSusValoresCorrectamente() {
        Coordenada coordenada = new Coordenada(3, 5);

        assertEquals(3, coordenada.getX());
        assertEquals(5, coordenada.getY());
    }
    @Test
    public void distanciaEntreCoordenadas() {
        Coordenada coordenada1 = new Coordenada(1, 1);
        Coordenada coordenada2 = new Coordenada(4, 5);

        assertEquals(4, coordenada1.distanciaA(coordenada2));
    }

    @Test
    public void trasladarCoordenada() {
        Coordenada coordenada = new Coordenada(2, 3);
        Coordenada nuevaCoordenada = coordenada.trasladar(1, -2);

        assertEquals(3, nuevaCoordenada.getX());
        assertEquals(1, nuevaCoordenada.getY());
    }
}
