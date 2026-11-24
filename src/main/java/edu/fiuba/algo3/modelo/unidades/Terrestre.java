package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla;

public class Terrestre implements TipoMovimiento{
    @Override
    public boolean puedeTransitar(Casilla casilla) {
        return casilla.estaLibre();
    }
}
