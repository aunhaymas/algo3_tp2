package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.armas.Arma;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.estadosUnidad.*;
import edu.fiuba.algo3.modelo.excepciones.CantidadMovimientosInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PuntosVidaInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Unidad implements Dañable, Pieza,Atacable {
	protected int vida;
	protected int movimientos;
	protected EstadoUnidad estadoUnidad;
	private Casilla casillaActual;
	protected Arma arma;
	protected TipoMovimiento tipoMovimiento;

	public Unidad(int vida, int movimientos) {
		if (vida <= 0)
            throw new PuntosVidaInvalidoException("No se pudo crear la unidad. Los puntos de vida deben ser mayores a 0.");
		if (movimientos <= 0) {
			throw new CantidadMovimientosInvalidaException("No se pudo crear la unidad. Los movimientos disponibles deben ser mayores a 0.");
		}

		this.vida = vida;
		this.movimientos = movimientos;
		this.tipoMovimiento = new Terrestre();
		this.estadoUnidad = new Estable();
	}
	public Unidad(int vida, int movimientos, TipoMovimiento tipoMovimiento){
		this(vida,movimientos);
		this.tipoMovimiento = tipoMovimiento;
	}
	public void contaminar() {
		this.estadoUnidad = new Contaminado();
	}

	public int getVida() {
		return this.vida;
	}

	public void recibirAtaque(Ataque ataque) {
		this.recibirAtaqueConEmpuje(ataque);

	}
	@Override
	public Empuje recibirAtaqueConEmpuje(Ataque ataque) {
		return ataque.efectuarAtaquePieza(this);
	};

	// Deberia ser privado, unidad no sabe que tipo de arma la ataco y no puedo aplicar polimorfismo
	// tampoco puede recibir el efecto del arma, por ejemplo la contaminacion
	// https://docs.google.com/presentation/d/1lf1sGq9iIxYKDetrzGbqh8L9fd5YhxZXutqcoW9jrlM/edit?slide=id.g52d1aa7109_0_15#slide=id.g52d1aa7109_0_15
	public void recibirDaño(int puntos) {
		this.vida -= this.estadoUnidad.calcularDañoRecibido(puntos);
		if (this.vida<=0){
			this.estadoUnidad = new Muerta();
		}
		//this.estadoUnidad = this.estadoUnidad.recibirDanio(puntos);
	}
//private void recibirDanio(int danio){this.vida -= danio;}
	public boolean estaDestruida() {
		return this.estadoUnidad.estaDestruida();
	}

	public void sumergir() {
		this.estadoUnidad = new Sumergido();
	}

	public void estabilizar() {
		this.estadoUnidad = new Estable();
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

	// viola lod
	public void atacar(Tablero tablero, Coordenada coordenada) {
		Casilla origen = tablero.obtenerCasilla(this);
		Trayectoria trayectoria = tablero.crearTrayectoria(origen.getCoordenada(), coordenada);
		atacar(trayectoria);
	}
	public void atacar(Trayectoria trayectoria) {
		arma.recorrer(trayectoria);
	}

	public void atacar(Atacable objetivo) {
		arma.atacar(objetivo);
	}


	@Override
    public void entrarEn(Casilla casilla) {
        this.casillaActual = casilla;
        casilla.aplicarEfecto(this);
    }
	public boolean puedeTransitar(Casilla casilla){
		return this.tipoMovimiento.puedeTransitar(casilla);
	}
}
