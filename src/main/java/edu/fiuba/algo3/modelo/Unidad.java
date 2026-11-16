package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.estadosUnidad.Estable;
import edu.fiuba.algo3.modelo.estadosUnidad.EstadoUnidad;
import edu.fiuba.algo3.modelo.estadosUnidad.Sumergido;
import edu.fiuba.algo3.modelo.excepciones.CantidadMovimientosInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PuntosVidaInvalidoException;

public abstract class Unidad implements Dañable, Pieza,Atacable {
	protected int vida;
	protected int movimientos;
	protected EstadoUnidad estadoUnidad;
	private Casilla casillaActual;
	protected Arma arma;
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
		this.recibirAtaque(ataque,new Coordenada(0,0));

	}
	@Override
	public Empuje recibirAtaque(Ataque ataque, Coordenada origen) {
		this.recibirDaño(ataque.getDanio());
		ataque.aplicarEfecto(this);
		return this.estadoUnidad.calcularEmpuje(ataque,origen);
	};

	// Deberia ser privado, unidad no sabe que tipo de arma la ataco y no puedo aplicar polimorfismo
	// tampoco puede recibir el efecto del arma, por ejemplo la contaminacion
	// https://docs.google.com/presentation/d/1lf1sGq9iIxYKDetrzGbqh8L9fd5YhxZXutqcoW9jrlM/edit?slide=id.g52d1aa7109_0_15#slide=id.g52d1aa7109_0_15
	public void recibirDaño(int puntos) {
		this.vida -= this.estadoUnidad.calcularDañoRecibido(puntos);
		//this.estadoUnidad = this.estadoUnidad.recibirDanio(puntos);
	}
private void recibirDanio(int danio){this.vida -= danio;}
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

	public boolean estaContaminado() {
		return this.estadoUnidad.estaContaminado();
	}

	public void atacar(Atacable objetivo) {
		arma.atacar(objetivo);
	}
}
