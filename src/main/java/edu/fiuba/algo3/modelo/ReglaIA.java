package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.veks.*;

public abstract class ReglaIA {
	private ReglaIA siguienteRegla;

	public ReglaIA encadenarCon(ReglaIA siguiente) {
		this.siguienteRegla = siguiente;
		return siguiente;
	}

	public abstract AccionIA evaluar(Tablero tablero, Vek vek, RegistroDeAtaques registro);

	protected AccionIA delegarASiguiente(Tablero tablero, Vek vek, RegistroDeAtaques registro) {
		return this.siguienteRegla.evaluar(tablero, vek, registro);
	}
}