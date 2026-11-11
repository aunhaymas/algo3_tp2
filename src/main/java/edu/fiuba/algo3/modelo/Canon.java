package edu.fiuba.algo3.modelo;

public class Canon extends Arma {
    public boolean atacar(Casilla casilla, Direccion direccion) {
        Casilla casillaAdyacente = casilla.casillaAdyacente(direccion);
        Ataque ataque = new Ataque(1);
        boolean hit = casillaAdyacente.recibirAtaque(ataque);
        if (! hit) {
            // Hasta que golpee algo.
            return casillaAdyacente.casillaAdyacente(direccion).recibirAtaque(ataque);
        }
        return hit;
    }
}
