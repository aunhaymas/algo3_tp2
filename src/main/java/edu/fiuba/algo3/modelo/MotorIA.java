package edu.fiuba.algo3.modelo;

import java.util.Arrays;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;

public class MotorIA {
	private Tablero tablero;
	private Vek vek;
	private ReglaIA cadenaDeReglas;
	private RegistroDeAtaques registro;

	public MotorIA(Tablero tablero, Vek vek, RegistroDeAtaques registro) {
		this.tablero = tablero;
		this.vek = vek;
		this.registro = registro;

		ReglaIA reglaAtacarEdificio = new ReglaAtacarEdificio();
		ReglaIA reglaAtacarMech = new ReglaAtacarMech();
		ReglaIA reglaAcercarAEdificio = new ReglaAcercarAEdificio();

		reglaAtacarEdificio.encadenarCon(reglaAtacarMech).encadenarCon(reglaAcercarAEdificio);
		this.cadenaDeReglas = reglaAtacarEdificio;
	}

	public AccionIA resolverProximaAccion() {
		return cadenaDeReglas.evaluar(tablero, vek, registro);
	}
}
