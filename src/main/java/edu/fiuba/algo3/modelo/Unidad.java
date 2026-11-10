package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadMovimientosInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PuntosVidaInvalidoException;

public abstract class Unidad implements Dañable, Pieza {
	private int vida;
	private int movimientos;
	private EstadoUnidad estadoUnidad;

	public Unidad(int vida, int movimientos) {
		if (vida <= 0) {
			throw new PuntosVidaInvalidoException("No se pudo crear la unidad. Los puntos de vida deben ser mayores a 0.");
		}
		if (movimientos <= 0) {
			throw new CantidadMovimientosInvalidaException("No se pudo crear la unidad. Los movimientos disponibles deben ser mayores a 0.");
		}

		this.vida = vida;
		this.movimientos = movimientos;
		this.estadoUnidad = new EstadoNormal();
	}
	public void mover(){}

	public void contaminar() {
		this.estadoUnidad = new EstadoContaminado();
	}

	public int getVida() {
		return this.vida;
	}

	public boolean recibirAtaque(int danio) {
		this.vida -= danio;
		return true; // Impacto
	}

	public boolean estaDestruida() {
		return this.vida <= 0;
	}

	public void sumergir() {
		this.estadoUnidad = new EstadoSumergido();
	}

	public void cancelarMovimiento() {
	}
}
