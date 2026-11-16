package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;

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

    public AnuncioDeAtaque anunciarAtaque(Casilla casillaMech) {
		// TDD: Proximo caso de uso me obligaria a guardar este anuncio?
		return new AnuncioDeAtaque(this,casillaMech);
	}
}
