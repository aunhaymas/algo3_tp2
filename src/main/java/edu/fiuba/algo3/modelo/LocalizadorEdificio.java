package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mechs.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.tablero.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalizadorEdificio implements LocalizadorEntidad {
	private final List<Casilla> edificiosLocalizados = new ArrayList<>();

	@Override
	public void registrar(PiezaEdificio edificio, Casilla casilla) {
		edificiosLocalizados.add(casilla);
	}

	@Override 
	public void registrar(Mech mech, Casilla casilla) {
		// No registro Mech
	}

	@Override 
	public void registrar(PiezaNula pieza, Casilla casilla) {
		// No registro Pieza nula
	}

	@Override 
	public void registrar(PiezaMontania montaña, Casilla casilla) {
		// No registro Montaña
	}

	@Override 
	public void registrar(Vek vek, Casilla casilla) {
		// No registro Vek
	}

	@Override 
	public void cargarDesde(Tablero tablero) {
		this.edificiosLocalizados.clear();
		tablero.reportarA(this);
	}

	public List<Casilla> obtenerCasillasConEdificios() {
		return this.edificiosLocalizados;
	}

	private Casilla desempatarEdificiosMismaDistancia(Casilla casillaEdificioA, Casilla casillaEdificioB) {
		if (casillaEdificioA.precedeEnOrdenLectura(casillaEdificioB)) {
			return casillaEdificioA;
		}
		return casillaEdificioB;
	}

	public Casilla hallarMasCercanoDesde(Casilla origen) {
		Casilla casillaEdificioMasCercano = edificiosLocalizados.get(0);
		int menorDistancia = origen.calcularDistanciaCon(casillaEdificioMasCercano);
		
		for (Casilla casilla : edificiosLocalizados) {
			int distancia = origen.calcularDistanciaCon(casilla);

			if (distancia < menorDistancia) {
				casillaEdificioMasCercano = casilla;
				menorDistancia = distancia;
			} else if (distancia == menorDistancia) {
				casillaEdificioMasCercano = desempatarEdificiosMismaDistancia(casilla, casillaEdificioMasCercano);
			}
		}

		return casillaEdificioMasCercano;
	}
}