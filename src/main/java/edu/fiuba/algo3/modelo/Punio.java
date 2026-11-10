package edu.fiuba.algo3.modelo;

public class Punio extends Arma {
    public boolean atacar(Pieza pieza, Tablero tablero, Direccion direccion) {
        return pieza.recibirAtaque(2);
    }
}
