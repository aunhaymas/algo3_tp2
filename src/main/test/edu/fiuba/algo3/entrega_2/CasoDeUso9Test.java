package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.estadosUnidad.*;
import edu.fiuba.algo3.modelo.mechs.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.terrenos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.veks.*;
import org.junit.jupiter.api.Test;

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
	 * el Vek ataca (mas bien, lo anuncia) sin necesidad de moverse.
	 */
	@Test 
	public void vekAtacaEdificioSinMoverseSiYaEstaEnAlcance() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
		RedElectrica redElectrica = new RedElectrica();
		
		Vek vek = new Saltador();
		Coordenada vekPos = new Coordenada(2, 2);

		// Edificio en rango de ataque del Vek
		Pieza edificio = new PiezaEdificio(redElectrica);
		Coordenada edificioPos = new Coordenada(2, 3);
		
		tablero.colocarPieza(edificio, edificioPos);
		tablero.colocarPieza(vek, vekPos);

		MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
		AccionIA vekDecision = motor.resolverProximaAccion();
		vekDecision.ejecutar();

		registroDeAtaques.ejecutarSiguienteAtaque();
		assertTrue(edificio.estaDestruida());
	}

	/**
	 * Si no hay edificio inmediato pero hay posicion en alcance que me permita atacar uno,
	 * el Vek se mueve a dicha posicion y entonces lo ataca. No hay mech en alcance.
	 */
	@Test 
	public void vekPriorizaAcercarseAEdificioParaAtacarSinMechEnAlcance() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(2, 1);

        // Un edificio a distancia 3 en la misma fila
		// El vek debe moverse a (2, 3) para atacarlo
        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(2, 4);

        tablero.colocarPieza(vek, vekPos);
        tablero.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        // Verifico que se movio a la casilla adyacente al edificio
        assertEquals(vek, tablero.obtenerCasilla(new Coordenada(2, 3)).obtenerPieza());
        assertTrue(tablero.obtenerCasilla(vekPos).estaLibre());

        registroDeAtaques.ejecutarSiguienteAtaque();
        assertTrue(edificio.estaDestruida());
	}

	/**
	 * Si no hay edificio inmediato pero hay posicion en alcance que me permita atacar uno,
	 * el Vek se mueve a dicha posicion y entonces lo ataca, sin importar si hay mech inmediato.
	 */
	@Test
	public void vekPriorizaAcercarseAEdificioParaAtacarInclusoSiHayMechEnAlcance() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(2, 1);

        // Mech adyacente al vek (pero es prioridad 2)
        Mech mech = new MechDeCombate();
        int vidaInicialMech = mech.getVida();
        Coordenada mechPos = new Coordenada(1, 1);

        // Edificio alcanzable por vek moviéndose a (2, 3) (es prioridad 1)
        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(2, 4);

        tablero.colocarPieza(vek, vekPos);
        tablero.colocarPieza(mech, mechPos);
        tablero.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        registroDeAtaques.ejecutarSiguienteAtaque();

        // El Vek ignoró al Mech adyacente para moverse y atacar al edificio
        assertEquals(vek, tablero.obtenerCasilla(new Coordenada(2, 3)).obtenerPieza());
        assertTrue(edificio.estaDestruida());
        assertEquals(vidaInicialMech, mech.getVida());
	}

	/**
	 * Si no encuentro una posicion que me permita atacar un edificio, si hay mech inmediato,
	 * el Vek lo ataca sin necesidad de moverse.
	 */
	@Test
	public void vekAtacaMechSinMoverseSiNoHayPosicionParaAtacarEdificio() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(2, 2);

        // Mech adyacente al vek
        Mech mech = new MechDeCombate();
        int vidaInicialMech = mech.getVida();
        Coordenada mechPos = new Coordenada(2, 3);

        // Edificio esta demasiado lejos para alcanzarlo incluso si muevo al vek
        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(7, 7);

        tablero.colocarPieza(vek, vekPos);
        tablero.colocarPieza(mech, mechPos);
        tablero.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        // El Vek no se mueve de (2, 2)
        assertEquals(vek, tablero.obtenerCasilla(vekPos).obtenerPieza());

        registroDeAtaques.ejecutarSiguienteAtaque();
        assertTrue(mech.getVida() < vidaInicialMech);
        assertFalse(edificio.estaDestruida());
	}

	/**
	 * Si no encuentro una posicion que me permita atacar un edificio, 
	 * si no hay mech inmediato pero hay posicion en alcance que me permita atacar uno,
	 * el Vek se mueve a dicha posicion y entonces lo ataca.
	 */
	@Test
	public void vekPriorizaAcercarseAMechParaAtacarSiNoHayPosicionParaAtacarEdificio() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(2, 1);

        // Mech alcanzable si muevo vek a (2, 3)
        Mech mech = new MechDeCombate();
        int vidaInicialMech = mech.getVida();
        Coordenada mechPos = new Coordenada(2, 4);

        // Edificio no alcanzable en (7, 7)
        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(7, 7);

        tablero.colocarPieza(vek, vekPos);
        tablero.colocarPieza(mech, mechPos);
        tablero.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        assertEquals(vek, tablero.obtenerCasilla(new Coordenada(2, 3)).obtenerPieza());

        registroDeAtaques.ejecutarSiguienteAtaque();
        assertTrue(mech.getVida() < vidaInicialMech);
        assertFalse(edificio.estaDestruida());
	}

	/**
	 * Si no hay edificio o mech que atacar aunque me mueva a otra posicion,
	 * entonces me acerco al edificio mas cercano, sin anunciar ataque.
	 */
	@Test 
	public void vekSeAcercaAlEdificioMasCercanoSinAtacarSiNoHayPosicionDondePuedaAtacarAMechOEdificio() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(0, 0);

        // Edificio lejos en (0, 6)
		// El vek avanza 3 casillas hacia el Este hasta (0, 3) pero no llega a atacar
        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(0, 6);

        tablero.colocarPieza(vek, vekPos);
        tablero.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        assertEquals(vek, tablero.obtenerCasilla(new Coordenada(0, 3)).obtenerPieza());

        // Se registra el anuncio de ataque pero ejecutarlo no causa daño
        registroDeAtaques.ejecutarSiguienteAtaque();
        assertFalse(edificio.estaDestruida());
	}

	/**
	 * Al caer en decision de prioridad 3, si hay dos edificios a la misma distancia del Vek,
	 * el Vek desempata por coordenada de menor fila y columna.
	 */
	@Test 
	public void vekTieneDosEdificiosAMismaDistanciaDesempataPorCoordenadaMenorFilaYColumna() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();
		
		// Por las dudas, un tablero limpio
        tablero = parserTablero.parsear(List.of(
			"SSSSSSSS",
			"SSSSSSSS",
			"SSSSSSSS",
			"SSSSSSSS",
			"SSSSSSSS",
			"SSSSSSSS",
			"SSSSSSSS",
			"SSSSSSSS"
        ));

        Vek vek = new Saltador();
        Coordenada origen = new Coordenada(4, 4);
        
        Pieza edificioMenorFila = new PiezaEdificio(redElectrica);
        Pieza edificioMayorFila = new PiezaEdificio(redElectrica);

        tablero.colocarPieza(vek, origen);
        tablero.colocarPieza(edificioMenorFila, new Coordenada(4, 0)); // Columna 4, Fila 0 (distancia 4)
        tablero.colocarPieza(edificioMayorFila, new Coordenada(0, 4)); // Columna 0, Fila 4 (distancia 4)

        MotorIA motor = new MotorIA(tablero, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        // El vek elige acercarse al edificio en (4, 0) por tener menor fila (y = 0 < y = 4)
        assertEquals(vek, tablero.obtenerCasilla(new Coordenada(4, 1)).obtenerPieza());
        assertFalse(edificioMenorFila.estaDestruida());
        assertFalse(edificioMayorFila.estaDestruida());
	}
	
	/**
	 * Verifico que el ultimo movimiento de Vek no termina en Agua.
	 */
	@Test 
	public void vekTerrestreEvitaTerminarMovimientoEnAgua() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Tablero tableroConAgua = parserTablero.parsear(List.of(
                "SAAASSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        ));

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(0, 0);

        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(2, 5);

        tableroConAgua.colocarPieza(vek, vekPos);
        tableroConAgua.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tableroConAgua, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        assertTrue(tableroConAgua.obtenerCasilla(new Coordenada(1, 0)).estaLibre());
        assertTrue(tableroConAgua.obtenerCasilla(new Coordenada(2, 0)).estaLibre());
        assertTrue(tableroConAgua.obtenerCasilla(new Coordenada(3, 0)).estaLibre());
        assertEquals(vek, tableroConAgua.obtenerCasilla(new Coordenada(0, 3)).obtenerPieza());
    }

	/**
	 * Verifico que el ultimo movimiento de Vek no termina en Acido.
	 */
	@Test 
	public void vekTerrestreEvitaTerminarMovimientoEnAcido() {
		RegistroDeAtaques registroDeAtaques = new RegistroDeAtaques();
        RedElectrica redElectrica = new RedElectrica();

        Tablero tableroConAcido = parserTablero.parsear(List.of(
                "SÁÁÁSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        ));

        Vek vek = new Saltador();
        Coordenada vekPos = new Coordenada(0, 0);

        Pieza edificio = new PiezaEdificio(redElectrica);
        Coordenada edificioPos = new Coordenada(2, 5);

        tableroConAcido.colocarPieza(vek, vekPos);
        tableroConAcido.colocarPieza(edificio, edificioPos);

        MotorIA motor = new MotorIA(tableroConAcido, vek, registroDeAtaques);
        AccionIA vekDecision = motor.resolverProximaAccion();
        vekDecision.ejecutar();

        assertTrue(tableroConAcido.obtenerCasilla(new Coordenada(1, 0)).estaLibre());
        assertTrue(tableroConAcido.obtenerCasilla(new Coordenada(2, 0)).estaLibre());
        assertTrue(tableroConAcido.obtenerCasilla(new Coordenada(3, 0)).estaLibre());
        assertEquals(vek, tableroConAcido.obtenerCasilla(new Coordenada(0, 3)).obtenerPieza());
    }
}