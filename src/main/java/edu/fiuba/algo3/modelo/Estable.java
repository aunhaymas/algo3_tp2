package edu.fiuba.algo3.modelo;

public class Estable implements EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return dañoBase;
	}

	@Override
	public int calcularDañoAtaque(int dañoBase) {
		return dañoBase;
	}

	@Override
	public boolean puedeAtacar() {
		return true;
	}

	@Override
	public void aplicarA(Unidad unidad) {
		unidad.estabilizar();
	}
}
