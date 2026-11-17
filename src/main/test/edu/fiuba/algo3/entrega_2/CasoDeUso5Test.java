package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.armas.Punio;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso5Test {
    //Verificar que la Luciérnaga contamine a la unidad que impacta y que la contaminación no se acumule.
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

        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe recibir el dano y ser contaminado.
        assertEquals(5, blanco.getVida());
        assertTrue(blanco.estaContaminado());

        // Act: Luciernaga ataca al blanco

        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe recibir el doble de dano y seguir contaminado.
        assertEquals(3, blanco.getVida());
        assertTrue(blanco.estaContaminado());

        // Act: Luciernaga ataca al blanco

        luciernaga.atacar(casillaBlanco);

        // Assert: Blanco debe solo recibir el doble de dano y seguir contaminado.
        assertEquals(1, blanco.getVida());
        assertTrue(blanco.estaContaminado());
    }
}
