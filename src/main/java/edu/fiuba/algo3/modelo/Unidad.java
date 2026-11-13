package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadMovimientosInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PuntosVidaInvalidoException;

public abstract class Unidad implements Dañable, Pieza {
	protected int vida;
	protected int movimientos;
	private EstadoUnidad estadoUnidad;
	private Casilla casillaActual;

	public Unidad(int vida, int movimientos) {
		if (vida <= 0) {
			throw new PuntosVidaInvalidoException("No se pudo crear la unidad. Los puntos de vida deben ser mayores a 0.");
		}
		if (movimientos <= 0) {
			throw new CantidadMovimientosInvalidaException("No se pudo crear la unidad. Los movimientos disponibles deben ser mayores a 0.");
		}

		this.vida = vida;
		this.movimientos = movimientos;
		this.estadoUnidad = new Estable();
	}

	public void contaminar() {
		this.estadoUnidad = new Contaminado();
	}

	public int getVida() {
		return this.vida;
	}

	public boolean recibirAtaque(Ataque ataque) {
		this.vida -= estadoUnidad.calcularDañoRecibido(ataque.getDanio());
		
        if (!this.estaDestruida() && ataque.tieneEmpuje()) {
            Casilla destino = this.casillaActual.casillaAdyacente(ataque.getDireccionEmpuje());
            
            if (destino.estaLibre()) {
                this.casillaActual.moverContenidoA(destino);
            } else {
                this.vida -= 1; // por choque
            }
        }
        return true;
	}

	public boolean estaDestruida() {
		return this.vida <= 0;
	}

	public void sumergir() {
		this.estadoUnidad = new Sumergido();
	}

	public void cancelarMovimiento() {
	}

	public void actualizarEstado(EstadoUnidad estadoUnidad) {
		this.estadoUnidad = estadoUnidad;
	}

	public EstadoUnidad obtenerEstado() {
		return this.estadoUnidad;
	}
}
