package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;
import edu.fiuba.algo3.modelo.estadosUnidad.EstadoUnidad;

public class Contaminado extends EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return 2 * dañoBase;
	}
	@Override
	public int calcularDañoAtaque(int dañoBase) {
		return dañoBase;
	}

	@Override
	public boolean puedeAtacar() {
		return true;
	}

	@Override
	public void aplicarA(Unidad unidad) {
		unidad.contaminar();
	}

	@Override
	public boolean estaContaminado() {
		return true;
	}
}