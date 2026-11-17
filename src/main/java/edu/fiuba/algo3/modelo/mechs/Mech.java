package edu.fiuba.algo3.modelo.mechs;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Memento;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.armas.Arma;
import edu.fiuba.algo3.modelo.armas.ArmaNula;
import edu.fiuba.algo3.modelo.estadosUnidad.EstadoUnidad;
import edu.fiuba.algo3.modelo.LocalizadorEntidad;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Atacable;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Mech extends Unidad {
    protected Arma arma;
	private boolean yaSeMovio=false;
	private boolean yaAtaco;
	private boolean yaCanceloMovimiento;
	private static  class MementoMech implements Memento{
		private final EstadoUnidad estadoUnidad;
		private MementoMech(EstadoUnidad estadoUnidad){
			this.estadoUnidad = estadoUnidad;
		}
		@Override
		public void restaurarEstadoEn(Mech mech) {
			mech.estadoUnidad = this.estadoUnidad;
		}
	}
	public Mech() {
		super(3,3);
		arma = new ArmaNula();
	}

	public Mech(int vida, int movimientos, Arma arma) {
		super(vida, movimientos);
		this.arma = arma;
	}

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}

	// @Override
	// public void recibirAtaque(Ataque ataque) {
	// }

	public void moverseA(Trayectoria trayectoria) {
		validarMovimiento(trayectoria);
		yaSeMovio=true;
		trayectoria.ejecutarTraslado();
	}
	private void validarMovimiento(Trayectoria trayectoria){
		if (yaSeMovio){
			throw new UnidadYaSeMovioException("El Mech ya se movio!");
		}
		if (trayectoria.logitud()>this.movimientos){
			throw new MovimientoExcedidoException("El movimiento requerido excede el movimiento maximo del Mech");
		}
		if (trayectoria.tieneObstaculos()){
			throw new MovimientoInvalidoException("Hay obstaculos en el camino!");
		}
	}

	public void atacar(Tablero tablero, Coordenada coordenada) {
		tablero.procesarAtaque(this, coordenada);
	}

	public void recorrer(Trayectoria trayectoria) {
		arma.recorrer(trayectoria);
	}

	public void atacar(Atacable objetivo) {
		if (yaAtaco){
			throw new UnidadYaAtacoException("Mech ya ataco");
		}
		if (!this.puedeAtacar()) {
            throw new UnidadSumergidaException("El Mech no puede atacar mientras está en el Agua.");
        }
		yaAtaco=true;
		arma.atacar(objetivo);
	}

	public void restaurarMovimiento(Trayectoria regreso) {
		// TDD: refactor a State
		yaSeMovio=false;
		if(yaAtaco){
			throw new MovimientoNoCancelable("mech no pudo deshacer su mov si ya ataco");
		}
		if (yaCanceloMovimiento){
			throw new MovimientoYaCancelado("Mech ya restauro su movimiento en este turno");
		}
		yaCanceloMovimiento = true;

		regreso.ejecutarTraslado();
	}

	@Override
	public void reportarA(LocalizadorEntidad localizador, Casilla casilla) {
		localizador.registrar(this, casilla);
	}
	public Memento guardarEstado(){
		return new MementoMech(this.estadoUnidad);
	}
	public void restaurarEstado(Memento memento){
		memento.restaurarEstadoEn(this);
	}
}
