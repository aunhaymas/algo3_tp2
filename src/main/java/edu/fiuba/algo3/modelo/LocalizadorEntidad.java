package edu.fiuba.algo3.modelo;

public interface LocalizadorEntidad {
	public void registrar(Vek vek, Casilla casilla);
	public void registrar(Mech mech, Casilla casilla);
	public void registrar(PiezaEdificio edificio, Casilla casilla);
	// public void registrar(PiezaMontaña montaña, Casilla casilla);
	public void cargarDesde(Tablero tablero);
}