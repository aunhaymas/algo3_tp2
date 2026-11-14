package edu.fiuba.algo3.modelo;

public class Contaminado implements EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return 2 * dañoBase;
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
		unidad.contaminar();
	}
}
