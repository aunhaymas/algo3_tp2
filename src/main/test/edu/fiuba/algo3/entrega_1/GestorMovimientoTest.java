package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.tngtech.archunit.lang.ArchRule.Assertions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GestorMovimientoTest {
	private ParserTablero parserTablero = new ParserTablero(new TerrenosFactory());
    private Tablero tablero = parserTablero.parsear(List.of(
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    ));

	@Test 
	public void mechPuedeDeshacerMovimientoSiNoAtaco() {
		MechDeArtilleria mech = new MechDeArtilleria();
		GestorFaseJugador gestor = new GestorFaseJugador(tablero);

		Coordenada coordenadaOrigen = new Coordenada(1, 1);
		Coordenada coordenadaDestino = new Coordenada(1, 2);
		
		Casilla casillaOrigen = tablero.obtenerCasilla(coordenadaOrigen);
		Casilla casillaDestino = tablero.obtenerCasilla(coordenadaDestino);

		tablero.colocarPieza(mech, coordenadaOrigen);
		gestor.seleccionarMech(mech);
		gestor.moverMechSeleccionado(casillaOrigen, casillaDestino);

		assertTrue(casillaOrigen.estaLibre());
		assertEquals(mech, casillaDestino.obtenerPieza());

		gestor.deshacerMovimiento();

		assertTrue(casillaDestino.estaLibre());
		assertEquals(mech, casillaOrigen.obtenerPieza());
	}

	public void mechVuelveAPosicionAnteriorTrasDeshacerMovimiento() {
		return;
	}

	public void mechNoPuedeDeshacerMovimientoDespuesDeAtacar() {
		return;
	}

	public void mechNoPuedeDeshacerMovimientoMasDeUnaVezPorTurno() {
		return;
	}
}
