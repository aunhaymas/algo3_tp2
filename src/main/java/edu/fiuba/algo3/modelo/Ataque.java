package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;

public class Ataque {
    protected int danio;
    private final Empuje empuje;

    public Ataque(int danio) {
        this.danio = danio;
        this.empuje = new EmpujeNulo();
    }
    public Ataque(int danio, Empuje empuje) {
        this.danio = danio;
        this.empuje = empuje;
    }

    public Ataque(int danio, Direccion direccionEmpuje) {
        this.danio = danio;
        this.empuje = new EmpujeEfectivo(direccionEmpuje.obtenerDireccion());
    }

    public int getDanio() {
        return this.danio;
    }
    public void aplicarEfecto(Unidad unidad){}
    public void efectuarAtaque(Unidad unidad) {
        unidad.recibirDaño(danio);
    }

    public Empuje generarEmpujeDesde(Coordenada origen) {
        return this.empuje.prepararDesde(origen);
    }

    public Empuje efectuarAtaquePieza(Unidad pieza, Coordenada origen) {
		pieza.recibirDaño(danio);
		this.aplicarEfecto(pieza);
		return generarEmpujeDesde(origen);
    }
}
