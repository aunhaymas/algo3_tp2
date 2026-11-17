package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.veks.*;

public class ReglaAtacarEdificio extends ReglaIA {
	private LocalizadorEdificio localizador;

	public ReglaAtacarEdificio() {
		this.localizador = new LocalizadorEdificio();
	}
	
	public AccionIA evaluar(Tablero tablero, Vek vek, RegistroDeAtaques registro) {
		localizador.cargarDesde(tablero);
		Casilla origen = tablero.obtenerCasilla(vek);
		List<Casilla> casillasConEdificios = localizador.obtenerCasillasConEdificios();

		// Me fijo si ya hay un edificio en rango de ataque
		for (Casilla casilla : casillasConEdificios) {
			if (vek.puedoAtacarDesde(origen, casilla, tablero)) {
				return new AccionIA(origen, casilla, new AnuncioNulo(), registro);
			}
		}

		// Me fijo si hay alguna casilla al alcance del vek desde donde pueda atacar un edificio
		List<Casilla> casillasAlcanzables = vek.hallarDestinosValidosDesde(origen, tablero);
		for (Casilla casillaConEdificio : casillasConEdificios) {
			for (Casilla casillaAlcanzable : casillasAlcanzables) {
				if (vek.puedoAtacarDesde(casillaAlcanzable, casillaConEdificio, tablero)) {
					AnuncioDeAtaque anuncio = new AnuncioDeAtaqueReal(vek, casillaConEdificio);
					return new AccionIA(origen, casillaAlcanzable, anuncio, registro);
				}
			}
		}
		
		// Si no se pudo atacar un edificio, paso a la siguiente regla
		return delegarASiguiente(tablero, vek, registro);
	}
}