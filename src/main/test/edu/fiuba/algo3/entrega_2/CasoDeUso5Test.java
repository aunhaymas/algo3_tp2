package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.veks.Luciernaga;


public class CasoDeUso5Test {
    
    @Test
    public void luciernagaAtacaUnMechYEsContaminado() {
        // Arrange
        Coordenada coordenadaLuciernaga = new Coordenada(4, 2);
        Coordenada coordenadaBlanco = new Coordenada(4, 5);

        Luciernaga luciernaga = new Luciernaga();
        Mech blanco = new Mech(6, 3, new Punio());

        Casilla casillaLuciernaga = new Casilla(coordenadaLuciernaga);
        casillaLuciernaga.recibirPieza(luciernaga);
        Casilla casillaBlanco = new Casilla(coordenadaBlanco);
        casillaBlanco.recibirPieza(blanco);

        // Act: Luciernaga ataca al blanco
        luciernaga.anunciarAtaque(casillaBlanco);
        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe recibir el dano y ser contaminado.
        assertEquals(5, blanco.getVida());
        assertTrue(blanco.estaContaminado());
    }

    @Test
    public void luciernagaAtacaUnMechContaminadoDebeRecibirElDobleDeDanoYElEstadoNoSeSobrepone() {
        // Arrange
        Coordenada coordenadaLuciernaga = new Coordenada(4, 2);
        Coordenada coordenadaBlanco = new Coordenada(4, 5);

        Luciernaga luciernaga = new Luciernaga();
        Mech blanco = new Mech(6, 3, new Punio());

        Casilla casillaLuciernaga = new Casilla(coordenadaLuciernaga);
        casillaLuciernaga.recibirPieza(luciernaga);
        Casilla casillaBlanco = new Casilla(coordenadaBlanco);
        casillaBlanco.recibirPieza(blanco);

        // Act: Luciernaga ataca al blanco
        luciernaga.anunciarAtaque(casillaBlanco);
        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe recibir el dano y ser contaminado.
        assertEquals(5, blanco.getVida());
        assertTrue(blanco.estaContaminado());

        // Act: Luciernaga ataca al blanco
        luciernaga.anunciarAtaque(casillaBlanco);
        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe recibir el doble de dano y seguir contaminado.
        assertEquals(3, blanco.getVida());
        assertTrue(blanco.estaContaminado());

        // Act: Luciernaga ataca al blanco
        luciernaga.anunciarAtaque(casillaBlanco);
        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe solo recibir el doble de dano y seguir contaminado.
        assertEquals(1, blanco.getVida());
        assertTrue(blanco.estaContaminado());
    }
}
