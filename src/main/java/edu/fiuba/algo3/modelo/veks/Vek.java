package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public class Vek extends Unidad {
	AnuncioDeAtaque anuncioAtaque;
    // Temporal
	public Vek() {
		super(3,3);
		this.anuncioAtaque=new AnuncioNulo();
	}

	public Vek(int vida, int movimientos) {
		super(vida, movimientos);
		this.anuncioAtaque=new AnuncioNulo();
    }

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}

	@Override
	public void notificarEmpuje(Coordenada direccion) {
		anuncioAtaque.desplazarAtaque(direccion);
	}


	@Override
    public void sumergir() {
        this.recibirDaño(this.vida); 
    }

    public AnuncioDeAtaque anunciarAtaque(Coordenada objetivo) {
		this.anuncioAtaque = new AnuncioReal(this,objetivo);
		return this.anuncioAtaque;
	}

	public Decision tomarDecision(List<Opcion> opcionAtaqueEdificio) {
		return new DecisionAtaqueEdificio(opcionAtaqueEdificio.get(0),this);
	}
}
