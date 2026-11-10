package edu.fiuba.algo3.modelo;

public abstract class Arma {

    // public abstract void moverPieza(Pieza pieza, Tablero tablero, Direccion direccion);
    public boolean atacar(Casilla casilla, Direccion direccion) {
        return casilla.recibirAtaque(this, direccion);
    }
    public abstract boolean atacar(Pieza pieza, Tablero tablero, Direccion direccion);
}
