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

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }

    public void atacar(Trayectoria trayectoria) {
        // Deberia recorrer todas las casillas hasta que una NO este libre, De ahi hit!. 
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new Ataque(1));
        hit.empujar(trayectoria.getDireccion());
    }
}
