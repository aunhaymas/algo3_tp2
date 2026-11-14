package edu.fiuba.algo3.modelo;

public interface EstadoUnidad {
	public int calcularDañoRecibido(int dañoBase);

	public int calcularDañoAtaque(int dañoBase);

	public boolean puedeAtacar();

	public void aplicarA(Unidad unidad);
}
