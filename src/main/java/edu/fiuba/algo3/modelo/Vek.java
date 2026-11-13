package edu.fiuba.algo3.modelo;

public class Vek extends Unidad {

    // Temporal
	public Vek() {
		super(3,3);
	}

	public Vek(int vida, int movimientos) {
        super(vida, movimientos);
    }

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}

	@Override
    public void sumergir() {
        this.recibirDaño(this.vida); 
    }
}
