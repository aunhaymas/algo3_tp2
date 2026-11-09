package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Efecto;
import edu.fiuba.algo3.modelo.Pieza;
import edu.fiuba.algo3.modelo.Suelo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasillaTest {

    @Test
    public void casillaSeCreaConSueloYEstaLibre() {
        Efecto suelo = new Suelo();
        Casilla casilla = new Casilla(suelo);

        assertTrue(casilla.estaLibre());
    }
    @Test
    public void casillaRecibePiezaYDejaDeEstarLibre() {
        Efecto suelo = new Suelo();
        Casilla casilla = new Casilla(suelo);
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        casilla.recibirPieza(moquitoDePieza);
        assertFalse(casilla.estaLibre());
    }
}
