package edu.fiuba.algo3.modelo;

public class Punio extends Arma {
    public boolean atacar(Casilla casilla, Direccion direccion) {
        Casilla casillaAdyacente = casilla.casillaAdyacente(direccion);
        Ataque ataque = new Ataque(2);
        return casillaAdyacente.recibirAtaque(ataque);
    }
}
