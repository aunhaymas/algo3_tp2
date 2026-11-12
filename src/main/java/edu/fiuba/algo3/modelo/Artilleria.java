package edu.fiuba.algo3.modelo;

public class Artilleria extends Arma {
    public boolean atacar(Casilla casilla, Direccion direccion) {
        Casilla casillaAdyacente = casilla.casillaAdyacente(direccion);
        Casilla casillaAdyacente2 = casillaAdyacente.casillaAdyacente(direccion);
        Ataque ataque = new Ataque(1);
        boolean hit = casillaAdyacente2.recibirAtaque(ataque);
        if (! hit) {
            // Hasta que golpee algo.
            return casillaAdyacente.casillaAdyacente(direccion).recibirAtaque(ataque);
        }
        return hit;
    }

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }
}
