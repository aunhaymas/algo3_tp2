package edu.fiuba.algo3.modelo;

public class Artilleria extends Arma {
    public boolean atacar(Pieza pieza, Tablero tablero, Direccion direccion) {
        boolean hit = pieza.recibirAtaque(1);
        if (! hit) {
            Casilla casilla = tablero.obtenerCasilla(pieza);
            return this.atacar(direccion(casilla), direccion);
        }
        return hit;
    }
}
