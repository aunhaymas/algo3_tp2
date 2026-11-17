package edu.fiuba.algo3.modelo.estadoCasilla;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.ataques.DanioColision;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;
import edu.fiuba.algo3.modelo.unidades.Pieza;

public class EstadoOcupada implements EstadoCasilla{
    private final Pieza pieza;

    public EstadoOcupada(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public EstadoCasilla recibirEmpuje(Casilla origen, Casilla destino, Pieza pieza) {
        destino.recibirAtaque(new DanioColision());
        origen.recibirAtaque(new DanioColision());
        return this;
    }

    @Override
    public boolean estaLibre() {
        return false;
    }

    @Override
    public Empuje recibirAtaque(Ataque ataque) {
        return pieza.recibirAtaqueConEmpuje(ataque);
    }

    @Override
    public Pieza obtenerPieza() {
        return this.pieza;
    }

    @Override
    public boolean tienePieza(Pieza pieza) {
        return this.pieza.equals(pieza);
    }

    @Override
    public void empujarA(Casilla origen, Casilla destino) {
        destino.recibirEmpuje(origen,this.pieza);
    }
}
