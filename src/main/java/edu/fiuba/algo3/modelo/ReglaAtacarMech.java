package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.*;

public class ReglaAtacarMech extends ReglaIA {
	private LocalizadorMech localizador;

	public ReglaAtacarMech() {
		this.localizador = new LocalizadorMech();
	}
	
	public AccionIA evaluar(Tablero tablero, Vek vek, RegistroDeAtaques registro) {
		localizador.cargarDesde(tablero);
		Casilla origen = tablero.obtenerCasilla(vek);
		List<Casilla> casillasConMechs = localizador.obtenerCasillasConMechs();

		// Me fijo si ya hay un mech en rango de ataque
		for (Casilla casilla : casillasConMechs) {
			if (vek.puedoAtacarDesde(origen, casilla, tablero)) {
				return new AccionIA(origen, casilla, new AnuncioNulo(), registro);
			}
		}

		// Me fijo si hay alguna casilla al alcance del vek desde donde pueda atacar un mech
		List<Casilla> casillasAlcanzables = vek.hallarDestinosValidosDesde(origen, tablero);
		for (Casilla casillaConMech : casillasConMechs) {
			for (Casilla casillaAlcanzable : casillasAlcanzables) {
				if (vek.puedoAtacarDesde(casillaAlcanzable, casillaConMech, tablero)) {
					AnuncioDeAtaque anuncio = new AnuncioDeAtaqueReal(vek, casillaConMech);
					return new AccionIA(origen, casillaAlcanzable, anuncio, registro);
				}
			}
		}
		
		// Si no se pudo atacar un mech, paso a la siguiente regla
		return delegarASiguiente(tablero, vek, registro);
	}
}