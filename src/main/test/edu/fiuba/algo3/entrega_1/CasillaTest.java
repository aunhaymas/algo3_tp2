package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
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

    @Test
    public void casillaOcupadaSeLiberaCuandoUnaPiezaEsQuitada() {
        Efecto suelo = Mockito.mock(Efecto.class);
        Casilla casilla = new Casilla(suelo);
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        casilla.recibirPieza(moquitoDePieza);

        casilla.removerPieza();
        assertTrue(casilla.estaLibre());
    }
    @Test
    public void casillaLeDiceAPiezaQueEntro() {
        Efecto efectoNulo = Mockito.mock(Efecto.class);
        Casilla casilla = new Casilla(efectoNulo);
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        casilla.recibirPieza(moquitoDePieza);

        Mockito.verify(moquitoDePieza).entrarEn(casilla);
    }

    @Test
    public void casillaDelegaAtaqueEnSuPieza() {
        Efecto efectoNulo = Mockito.mock(Efecto.class);
        Casilla casilla = new Casilla(efectoNulo);
        Pieza moquitoDePieza = Mockito.mock(Pieza.class);
        casilla.recibirPieza(moquitoDePieza);
        Ataque ataque = Mockito.mock(Ataque.class);
        casilla.recibirAtaque(ataque);
        Mockito.verify(moquitoDePieza).recibirAtaque(ataque);
    }
}
