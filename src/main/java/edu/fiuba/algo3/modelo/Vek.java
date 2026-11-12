package edu.fiuba.algo3.modelo;

public class Vek extends Unidad {

    // Temporal
	public Vek() {
		super(3,3);
	}

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}
}
