package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class CasillaTest {
    @Test
    public void casillaNuevaSeCreaLibre() {
        Casilla casilla = new Casilla();
        assertTrue(casilla.estaLibre());
    }

}
