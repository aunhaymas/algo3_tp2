package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class LocalizadorMech implements LocalizadorEntidad {
	private final List<Casilla> mechsLocalizados = new ArrayList<>();

	@Override
	public void registrar(Mech mech, Casilla casilla) {
		mechsLocalizados.add(casilla);
	}

	@Override 
	public void registrar(Vek vek, Casilla casilla) {
		// No registro Mech
	}
	/* @Override 
	public void registrar(PiezaMontaña montaña, Casilla casilla) {
		// No registro Montaña
	} */

	@Override 
	public void registrar(PiezaEdificio edificio, Casilla casilla) {
		// No registro Vek
	}

	@Override 
	public void registrar(PiezaNula pieza, Casilla casilla) {
		// No registro Pieza nula
	}

	@Override 
	public void cargarDesde(Tablero tablero) {
		this.mechsLocalizados.clear();
		tablero.reportarA(this);
	}

	public List<Casilla> obtenerCasillasConMechs() {
		return this.mechsLocalizados;
	}	
}
