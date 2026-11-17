package edu.fiuba.algo3.modelo.estadosUnidad;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Estable extends EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return dañoBase;
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
		unidad.estabilizar();
	}

	@Override
	public boolean estaContaminado() {
		return false;
	}
}
