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

	public void recibirAtaque(Ataque ataque) {
		this.recibirDaño(ataque.getDanio());
		
		// Pepo: Vamos por otro camino: delegamos a Casilla la responsabilidad de destruir o mover.
        // if (!this.estaDestruida() && ataque.tieneEmpuje()) {
        //     Casilla destino = this.casillaActual.casillaAdyacente(ataque.getDireccionEmpuje());
            
        //     if (destino.estaLibre()) {
        //         this.casillaActual.moverContenidoA(destino);
        //     } else {
        //         this.vida -= 1; // por choque
        //     }
        // }
        // return true;
	}

	// Deberia ser privado, unidad no sabe que tipo de arma la ataco y no puedo aplicar polimorfismo
	// tampoco puede recibir el efecto del arma, por ejemplo la contaminacion
	// https://docs.google.com/presentation/d/1lf1sGq9iIxYKDetrzGbqh8L9fd5YhxZXutqcoW9jrlM/edit?slide=id.g52d1aa7109_0_15#slide=id.g52d1aa7109_0_15
	public void recibirDaño(int puntos) {
		this.vida -= puntos;
	}

	public boolean estaDestruida() {
		return this.vida <= 0;
	}

	public void sumergir() {
		this.estadoUnidad = new Sumergido();
	}

	public void estabilizar() {
		this.estadoUnidad = new Estable();
	}

	public void cancelarMovimiento() {
	}

	public boolean puedeAtacar() {
        return this.estadoUnidad.puedeAtacar();
    }

	public EstadoUnidad obtenerEstado() {
		return this.estadoUnidad;
	}
}
