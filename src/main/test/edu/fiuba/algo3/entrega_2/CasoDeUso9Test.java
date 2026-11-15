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

	@Test
	public void vekPriorizaAtacarEdificioSobreAtacarMech() {
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

	@Test
	public void vekPriorizaAtacarMechSiNoHayEdificioCercano() {}

	@Test 
	public void vekSeAcercaAlEdificioMasCercanoSiNoPuedeAtacarANadie() {}

	@Test 
	public void vekTerrestreEvitaTerminarMovimientoEnAgua() {}

	@Test 
	public void vekTerrestreEvitaTerminarMovimientoEnAcido() {}

	@Test 
	public void vekTieneDosEdificiosAMismaDistanciaDesempataPorCoordenadaMenorFilaYColumna() {}
}