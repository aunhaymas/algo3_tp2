package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;

public class Vek extends Unidad {
	AnuncioDeAtaque anuncioActual;
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
	public void notificarEmpuje(Coordenada direccion) {
		this.anuncioActual.desplazarAtaque(direccion);
	}


	@Override
    public void sumergir() {
        this.recibirDaño(this.vida); 
    }

    public AnuncioDeAtaque anunciarAtaque(Coordenada origen, Coordenada objetivo) {
		this.anuncioActual = new AnuncioDeAtaque(this,origen,objetivo);
		return anuncioActual;
	}
}
