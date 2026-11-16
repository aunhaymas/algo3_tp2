package edu.fiuba.algo3.modelo.estadosUnidad;

import edu.fiuba.algo3.modelo.Ataque;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;

public abstract class EstadoUnidad {
	public abstract int calcularDañoRecibido(int dañoBase);

	public abstract int calcularDañoAtaque(int dañoBase);

	public abstract boolean puedeAtacar();

	public abstract void aplicarA(Unidad unidad);

	public abstract boolean estaContaminado();

	public Empuje calcularEmpuje(Ataque ataque, Coordenada origen) {
		return ataque.generarEmpujeDesde(origen);
	}
}
