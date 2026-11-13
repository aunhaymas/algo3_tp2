package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorFaseJugador {
	private final Tablero tablero;
	private Mech mechSeleccionado;
	private RegistroMovimiento ultimoMovimiento;

	public GestorFaseJugador(Tablero tablero) {
		this.mechSeleccionado = null;
		this.tablero = tablero;
		this.ultimoMovimiento = new RegistroMovimientoNulo();
	}

	public void seleccionarMech(Mech mech) {
		this.mechSeleccionado = mech;
	}

	public void moverMechSeleccionado(Casilla mechCasilla, Casilla destino) {
		RegistroMovimiento registro = new RegistroMovimientoJugador(mechSeleccionado, mechCasilla, destino, mechSeleccionado.obtenerEstado());
		
		mechCasilla.moverContenidoA(destino);
		this.ultimoMovimiento = registro;
	}

	public void atacarCasilla(Casilla objetivo) {
		Coordenada coordenadaObjetivo = objetivo.getCoordenada();
		mechSeleccionado.atacar(tablero, coordenadaObjetivo);
		this.ultimoMovimiento = new RegistroMovimientoNulo();
	}

	public void deshacerMovimiento() {
		ultimoMovimiento.restaurar();
	}
}
