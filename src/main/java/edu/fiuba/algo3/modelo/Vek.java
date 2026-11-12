package edu.fiuba.algo3.modelo;

public class Vek extends Unidad {

    // Temporal
	public Vek() {
		super(3,3);
	}

    public boolean recibirAtaque(Ataque ataque) {
        return super.recibirAtaque(ataque);
    }

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}
}
