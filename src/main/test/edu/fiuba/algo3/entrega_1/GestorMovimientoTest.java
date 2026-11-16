package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.MovimientoNoCancelable;
import edu.fiuba.algo3.modelo.excepciones.MovimientoYaCancelado;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	public void mechSoloPuedeDeshacerMovimientoUnaVezPorTurno() {
		MechDeCanon mech = new MechDeCanon();
		GestorFaseJugador gestor = new GestorFaseJugador(tablero);

		Coordenada coordenadaOrigen = new Coordenada(1, 1);
		Coordenada coordenadaDestino = new Coordenada(1, 2);

		Casilla casillaOrigen = tablero.obtenerCasilla(coordenadaOrigen);
		Casilla casillaDestino = tablero.obtenerCasilla(coordenadaDestino);

		tablero.colocarPieza(mech, coordenadaOrigen);
		gestor.seleccionarMech(mech);
		gestor.moverMechSeleccionado(casillaOrigen, casillaDestino);

		gestor.deshacerMovimiento();
		gestor.deshacerMovimiento(); // No cambia nada respecto a la posición restaurada antes

		assertTrue(casillaDestino.estaLibre());
		assertEquals(mech, casillaOrigen.obtenerPieza());
	}

	@Test
	public void comandoEjecutaMovimiento() {
		Casilla origen = new Casilla(new Coordenada(0,0));
		Casilla destino = new Casilla(new Coordenada(0,1));
		Trayectoria trayectoria = new Trayectoria(List.of(origen,destino));
		Mech mechCombate = new MechDeCombate();
		origen.recibirPieza(mechCombate);

		ComandoMover comando= new ComandoMover(mechCombate,trayectoria);
		comando.ejecutar();
		assertTrue(origen.estaLibre());
		assertEquals(mechCombate, destino.obtenerPieza());
	}
	@Test
	public void comandoDeshaceMovimiento() {
		Casilla origen = new Casilla(new Coordenada(0,0));
		Casilla destino = new Casilla(new Coordenada(1,1));
		Trayectoria camino = new Trayectoria(List.of(origen, destino));
		Mech mechCombate = new MechDeCombate();
		origen.recibirPieza(mechCombate);
		ComandoMover comando = new ComandoMover(mechCombate, camino);
		comando.ejecutar();
		comando.deshacer();
		assertFalse(origen.estaLibre());
	}
	@Test
	public void mechNoPuedeDeshacerMovimientoDespuesDeAtacar() {
		Casilla origen = new Casilla(new Coordenada(0,0));
		Casilla destino = new Casilla(new Coordenada(1,1));
		Trayectoria camino = new Trayectoria(List.of(origen, destino));
		Mech mechCombate = new MechDeCombate();
		origen.recibirPieza(mechCombate);
		ComandoMover comando = new ComandoMover(mechCombate, camino);
		Vek vek = new Vek(3,3);

		comando.ejecutar();
		mechCombate.atacar(vek);
		assertThrows(MovimientoNoCancelable.class, comando::deshacer);
	}
	@Test
	public void comandoNoPuedeDeshacerMovimientoMasDeUnaVezPorTurno() {
		//se puede lograr sin usar comando
		Casilla origen = new Casilla(new Coordenada(0,0));
		Casilla destino = new Casilla(new Coordenada(1,1));
		Trayectoria camino = new Trayectoria(List.of(origen, destino));
		Mech mechCombate = new MechDeCombate();
		origen.recibirPieza(mechCombate);
		ComandoMover comando = new ComandoMover(mechCombate, camino);
		comando.ejecutar();
		comando.deshacer();
		ComandoMover comandoInvalido = new ComandoMover(mechCombate, camino);
		comandoInvalido.ejecutar();
		assertThrows(MovimientoYaCancelado.class, comandoInvalido::deshacer);
	}
}
