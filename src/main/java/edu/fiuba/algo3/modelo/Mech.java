package edu.fiuba.algo3.modelo;

public class Mech extends Unidad {
    protected int vida;
    protected Arma arma;

    public boolean atacar(Tablero tablero, Direccion direccion) {
        Casilla casilla = tablero.obtenerCasilla(this);
        return casilla.recibirAtaque(arma, direccion);
    }
}
