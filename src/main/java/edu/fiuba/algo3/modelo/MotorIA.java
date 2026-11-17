package edu.fiuba.algo3.modelo;

import java.util.Arrays;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;

public class MotorIA {
	private Tablero tablero;
	private Vek vek;
	private ReglaIA cadenaDeReglas;
	private RegistroDeAtaques registro;

	public MotorIA(Tablero tablero, Vek vek, RegistroDeAtaques registro) {
		this.tablero = tablero;
		this.vek = vek;
		this.registro = registro;

		ReglaIA reglaAtacarEdificio = new ReglaAtacarEdificio();
		ReglaIA reglaAtacarMech = new ReglaAtacarMech();
		ReglaIA reglaAcercarAEdificio = new ReglaAcercarAEdificio();

		reglaAtacarEdificio.encadenarCon(reglaAtacarMech).encadenarCon(reglaAcercarAEdificio);
		this.cadenaDeReglas = reglaAtacarEdificio;
	}

	public AccionIA resolverProximaAccion() {
		return cadenaDeReglas.evaluar(tablero, vek, registro);
	}

	// evaluo prioridad 1:
	// obtengo casillas con edificios en el tablero
	// obtengo casillas alcanzables -> para vek concreto (saltarin, luciernaga, avispon)
	// 
	// para cada casilla con edificio:
	//      si alguna casilla esta en rango de ataque:
	//          devuelvo (posicion actual, anuncio de ataque)
	//          fin
	// 
	//      para cada casilla en casillas alcanzables:
	//          si desde alguna casilla el objetivo (edificio) esta en rango de ataque:
	//              destino = casilla
	//              devuelvo (destino, anuncio de ataque)
	//              fin
	// 
	// si llego hasta aca, no habia edificio por atacar disponible, 
	// entonces, busco bajo el mismo criterio ahora un mech
	// 
	// evaluo prioridad 2:
	// obtengo casillas con mech en el tablero
	// 
	// para cada casilla con mech:
	//      si alguna casilla esta en rango de ataque:
	//          devuelvo (posicion actual, anuncio de ataque)
	//          fin
	// 
	//      para cada casilla en casillas alcanzables:
	//          si desde alguna casilla el objetivo (mech) esta en rango de ataque:
	//              destino = casilla
	//              devuelvo (destino, anuncio de ataque)
	//              fin
	// 
	// si llego hasta aca, tampoco habia mech por atacar disponible,
	// entonces, busco simplemente el edificio con menor distancia y me acerco (sin atacar)
	// 
	// evaluo prioridad 3:
	// casilla edificio mas cercano = casilla primer edificio
	// menor distancia = distancia (posicion actual, posicion primer edificio)
	// 
	// para cada casilla con edificio:
	//      si distancia (posicion actual, edificio actual) menor que menor distancia:
	//          menor distancia = distancia
	//          casilla edificio mas cercano = casilla edificio actual
	//      si no, si misma distancia:
	//          casilla edificio mas cercano = desempate por coordenada (edificio mas cercano, edificio actual)
	// 
	// destino = calcular primer casilla que permite acercarse a (edificio mas cercano)
	// devuelvo (destino, anuncio nulo)
	// fin
}
