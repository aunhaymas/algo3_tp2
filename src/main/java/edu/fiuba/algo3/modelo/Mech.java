package edu.fiuba.algo3.modelo;

import java.util.List;

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









	public void moverseA(Casilla casillaDestino, List<Casilla> camino) {
		//El Proximo Test me va a pedir que valide el movimiento segun distancia
		camino.getFirst().moverContenidoA(camino.getLast());
	}
}
