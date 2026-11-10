package edu.fiuba.algo3.modelo;

public class EstadoContaminado extends EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return 2 * super.calcularDañoRecibido(dañoBase);
	}
	@Override
	public int calcularDañoAtaque(int dañoBase) {
		return super.calcularDañoAtaque(dañoBase);
	}
}
