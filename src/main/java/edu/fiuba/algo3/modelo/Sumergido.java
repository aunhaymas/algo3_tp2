package edu.fiuba.algo3.modelo;

public class Sumergido extends EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return super.calcularDañoRecibido(dañoBase);
	}

	@Override
	public int calcularDañoAtaque(int dañoBase) {
		return 0;
	}
}
