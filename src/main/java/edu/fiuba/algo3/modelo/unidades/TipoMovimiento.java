package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla;

public interface TipoMovimiento {
    public boolean puedeTransitar(Casilla casilla);
}
