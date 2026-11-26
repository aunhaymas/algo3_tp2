package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Recorrer;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.unidades.Atacable;
import edu.fiuba.algo3.modelo.unidades.Dañable;

public abstract class Arma implements Recorrer {

    // public abstract void moverPieza(Pieza pieza, Tablero tablero, Direccion direccion);
    // public abstract boolean atacar(Casilla casilla, Direccion direccion);

    public abstract void atacar(Dañable objetivo);
    public abstract void atacar(Atacable objetivo);

    public abstract boolean puedeAtacar(Trayectoria trayectoria);
}
