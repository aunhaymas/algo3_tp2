package edu.fiuba.algo3.modelo;

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
}
