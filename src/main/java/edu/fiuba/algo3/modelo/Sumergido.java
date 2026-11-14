package edu.fiuba.algo3.modelo;

public class Sumergido implements EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return dañoBase;
	}

	@Override
	public int calcularDañoAtaque(int dañoBase) {
		return 0;
	}

	@Override
    public boolean puedeAtacar() {
        return false;
    }

	@Override
	public void aplicarA(Unidad unidad) {
		unidad.sumergir();
	}
}
