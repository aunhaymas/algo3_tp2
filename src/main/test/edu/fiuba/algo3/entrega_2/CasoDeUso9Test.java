package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	@Test
	public void vekPriorizaAtacarEdificioSobreAtacarMech() {
		
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