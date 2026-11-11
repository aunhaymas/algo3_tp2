package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.MovimientoExcedidoException;

public class Mech extends Unidad {
	private final Arma arma;

	public Mech() {
		super(3,3);
		arma = null;
	}
	public Mech(int vida, int movimientos, Arma arma) {
		super(vida, movimientos);
		this.arma = arma;
	}

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}

	@Override
	public void recibirAtaque(Ataque ataque) {
	}









	public void moverseA(Casilla destino,Trayectoria trayectoria) {
		//El Proximo Test me va a pedir que valide el movimiento segun distancia
		if (trayectoria.logitud()>this.movimientos){
			throw new MovimientoExcedidoException("El movimiento requerido excede el movimiento maximo del Mech");
		}
		trayectoria.ejecutarTraslado();
	}
}
