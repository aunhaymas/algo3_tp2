package edu.fiuba.algo3.modelo;

public abstract class Arma {

    // public abstract void moverPieza(Pieza pieza, Tablero tablero, Direccion direccion);
    // public abstract boolean atacar(Casilla casilla, Direccion direccion);

    public abstract void atacar(Dañable objetivo);
    public abstract void atacar(Trayectoria trayectoria); 
}
