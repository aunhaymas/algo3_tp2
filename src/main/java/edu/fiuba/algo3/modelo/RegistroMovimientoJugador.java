package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.estadosUnidad.EstadoUnidad;

public class RegistroMovimientoJugador implements RegistroMovimiento {
	private final Mech mech;
	private final Casilla destino;
	private final Casilla origen;
	private final EstadoUnidad estadoUnidad;

	public RegistroMovimientoJugador(Mech mech, Casilla origen, Casilla destino, EstadoUnidad estadoUnidad) {
		this.mech = mech;
		this.origen = origen;
		this.destino = destino;
		this.estadoUnidad = estadoUnidad;
	}

	@Override 
	public void restaurar() {
		estadoUnidad.aplicarA(mech);
		destino.moverContenidoA(origen);
	}
}
