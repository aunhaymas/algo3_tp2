package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Efecto;
import edu.fiuba.algo3.modelo.Suelo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasillaTest {

    @Test
    public void casillaSeCreaConSueloYEstaLibre() {
        Efecto suelo = new Suelo();
        Casilla casilla = new Casilla(suelo);

        assertTrue(casilla.estaLibre());
    }

}
