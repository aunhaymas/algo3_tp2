package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.MovimientoExcedidoException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.UnidadYaAtacoException;
import edu.fiuba.algo3.modelo.excepciones.UnidadYaSeMovioException;

public class Mech extends Unidad {
    protected Arma arma;
	private boolean yaSeMovio=false;
	private boolean yaAtaco;

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

	public void atacar(Dañable objetivo) {
		if (yaAtaco){
			throw new UnidadYaAtacoException("Mech ya ataco");
		}
		yaAtaco=true;
		arma.atacar(objetivo);
	}

}
