package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mechs.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.tablero.*;

public interface LocalizadorEntidad {
	public void registrar(Vek vek, Casilla casilla);
	public void registrar(Mech mech, Casilla casilla);
	public void registrar(PiezaEdificio edificio, Casilla casilla);
	public void registrar(PiezaMontania montaña, Casilla casilla);
	public void registrar(PiezaNula piezaNula, Casilla casilla);
	public void cargarDesde(Tablero tablero);
}