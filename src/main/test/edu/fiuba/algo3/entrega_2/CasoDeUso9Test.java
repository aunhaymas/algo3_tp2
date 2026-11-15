package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.veks.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * CasoDeUso9Test.
 * La IA de los Vek debe ser determinista, para que sus decisiones puedan probarse.
 * 
 * Cada Vek evalúa las siguientes reglas en orden y aplica la primera que pueda cumplir:
 * 		1. Moverse a una posicion desde la que pueda atacar un Edificio
 * 		2. Moverse a una posicion desde la que pueda atacar un Mech
 * 		3. Acercarse lo más posible al edificio más cercano, sin anunciar ataque
 * 
 * Los Vek terrestres nunca terminan su movimiento en el Agua ni en el Ácido.
 * Los empates se resuelven con un criterio fijo documentado en los Supuestos
 * (por ejemplo, la casilla con menor fila y luego menor columna).
 */
public class CasoDeUso9Test {
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

	/**
	 * Si ya hay un edificio en rango de ataque, 
	 * el Vek ataca sin necesidad de moverse.
	 */
	@Test 
	public void vekAtacaEdificioSinMoverseSiYaEstaEnAlcance() {

	}

	/**
	 * Si no hay edificio inmediato pero hay posicion en alcance que me permita atacar uno,
	 * el Vek se mueve a dicha posicion y entonces lo ataca. No hay mech en alcance.
	 */
	@Test 
	public void vekPriorizaAcercarseAEdificioParaAtacarSinMechEnAlcance() {

	}

	/**
	 * Si no hay edificio inmediato pero hay posicion en alcance que me permita atacar uno,
	 * el Vek se mueve a dicha posicion y entonces lo ataca, sin importar si hay mech inmediato.
	 */
	@Test
	public void vekPriorizaAcercarseAEdificioParaAtacarInclusoSiHayMechEnAlcance() {
		Vek vek = new Saltador();
		Mech mech = new MechDeCombate();
		int vidaEsperada = mech.getVida();
		RedElectrica redElectrica = new RedElectrica();
		Pieza edificio = new PiezaEdificio(redElectrica);

		Coordenada vekPos = new Coordenada(2, 2);

		// Mech en rango de ataque del Vek
		Coordenada mechPos = new Coordenada(2, 2);
		// Edificio en rango de ataque del Vek
		Coordenada edificioPos = new Coordenada(2, 3);

		// El nombre puede cambiar o sus responsabilidades
		DecisionVek vekDecision = EvaluadorIAVek.decidirAccion(tablero, vek);

		// vekDecision.ejecutar()
		// -> Coordenada vekDestino = vekDecision.destinoMovimiento();
		//    > No necesita moverse, la posicion es la misma.
		// -> Coordenada casillaPorAtacar = vekDecision.casillaAtacada();
		// 	  > Ataca al edificio por prioridad 1

		assertTrue(edificio.estaDestruida());
		assertEquals(mech.getVida(), vidaEsperada);
	}

	/**
	 * Si no encuentro una posicion que me permita atacar un edificio, si hay mech inmediato,
	 * el Vek lo ataca sin necesidad de moverse.
	 */
	@Test
	public void vekAtacaMechSinMoverseSiNoHayPosicionParaAtacarEdificio() {}

	/**
	 * Si no encuentro una posicion que me permita atacar un edificio, 
	 * si no hay mech inmediato pero hay posicion en alcance que me permita atacar uno,
	 * el Vek se mueve a dicha posicion y entonces lo ataca.
	 */
	@Test
	public void vekPriorizaAcercarseAMechParaAtacarSiNoHayPosicionParaAtacarEdificio() {}

	/**
	 * Si no hay edificio o mech que atacar aunque me mueva a otra posicion,
	 * entonces me acerco al edificio mas cercano, sin anunciar ataque.
	 */
	@Test 
	public void vekSeAcercaAlEdificioMasCercanoSinAtacarSiNoHayPosicionDondePuedaAtacarAMechOEdificio() {}

	/**
	 * Al caer en decision de prioridad 3, si hay dos edificios a la misma distancia del Vek,
	 * el Vek desempata por coordenada de menor fila y columna.
	 */
	@Test 
	public void vekTieneDosEdificiosAMismaDistanciaDesempataPorCoordenadaMenorFilaYColumna() {}
	
	/**
	 * Verifico que el ultimo movimiento de Vek no termina en Agua.
	 */
	@Test 
	public void vekTerrestreEvitaTerminarMovimientoEnAgua() {}

	/**
	 * Verifico que el ultimo movimiento de Vek no termina en Acido.
	 */
	@Test 
	public void vekTerrestreEvitaTerminarMovimientoEnAcido() {}
}