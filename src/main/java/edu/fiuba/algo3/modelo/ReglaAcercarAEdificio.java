package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaqueReal;
import edu.fiuba.algo3.modelo.veks.AnuncioNulo;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;

public class ReglaAcercarAEdificio extends ReglaIA {
	private LocalizadorEdificio localizador;

	public ReglaAcercarAEdificio() {
		this.localizador = new LocalizadorEdificio();
	}

	private Casilla elegirCasillaQueMasAcerca(Casilla origen, Casilla objetivo, List<Casilla> casillasAlcanzables) {
		Casilla mejorDestino = origen;
		int menorDistancia = origen.calcularDistanciaCon(objetivo);

		for (Casilla candidato : casillasAlcanzables) {
			int distancia = candidato.calcularDistanciaCon(objetivo);

			if (distancia < menorDistancia) {
				menorDistancia = distancia;
				mejorDestino = candidato;
			}
		}
		return mejorDestino;
	}
	
	public AccionIA evaluar(Tablero tablero, Vek vek, RegistroDeAtaques registro) {
		localizador.cargarDesde(tablero);
		Casilla origen = tablero.obtenerCasilla(vek);

		Casilla edificioMasCercano = localizador.hallarMasCercanoDesde(origen);
		List<Casilla> casillasAlcanzables = vek.hallarDestinosValidosDesde(origen, tablero);
		Casilla mejorDestino = elegirCasillaQueMasAcerca(origen, edificioMasCercano, casillasAlcanzables);

		return new AccionIA(origen, mejorDestino, new AnuncioNulo(), registro);
	}
}