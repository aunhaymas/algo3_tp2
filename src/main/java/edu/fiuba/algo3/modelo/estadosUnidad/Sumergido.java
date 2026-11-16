package edu.fiuba.algo3.modelo.estadosUnidad;

import edu.fiuba.algo3.modelo.Ataque;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;

public class Sumergido extends EstadoUnidad {
	@Override
	public int calcularDañoRecibido(int dañoBase) {
		return dañoBase;
	}

	@Override
	public int calcularDañoAtaque(int dañoBase) {
		return 0;
	}

	@Override
    public boolean puedeAtacar() {
        return false;
    }

	@Override
	public void aplicarA(Unidad unidad) {
		unidad.sumergir();
	}

	@Override
	public boolean estaContaminado() {
		return false;
	}
}
