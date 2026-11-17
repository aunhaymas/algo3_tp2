package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;

public class AccionIA {
	private final Casilla origen;
	private final Casilla destino;
	private final AnuncioDeAtaque anuncioDeAtaque;
	private final RegistroDeAtaques registroDeAtaques;

	public AccionIA(Casilla origen, Casilla destino, AnuncioDeAtaque anuncioDeAtaque, RegistroDeAtaques registroDeAtaques) {
		this.origen = origen;
		this.destino = destino;
		this.anuncioDeAtaque = anuncioDeAtaque;
		this.registroDeAtaques = registroDeAtaques;
	}

	public void ejecutar() {
		origen.moverContenidoA(destino);
		registroDeAtaques.registrarAtaque(anuncioDeAtaque);
	}
}
