package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ObjetoFueraDeRangoException;

public class Punio extends Arma {
    public boolean atacar(Casilla casilla, Direccion direccion) {
        Casilla casillaAdyacente = casilla.casillaAdyacente(direccion);
        Ataque ataque = new Ataque(2);
        return casillaAdyacente.recibirAtaque(ataque);
    }

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(2);
    }

    public void atacar(Trayectoria trayectoria) {
        // Trayectoria no debe tener longitud mayor a 2.
        if (!trayectoria.esTrayectoriaCorta()) {
            throw new ObjetoFueraDeRangoException();
        } else {
            Casilla destino = trayectoria.getDestino();
            destino.recibirAtaque(new Ataque(2));
            destino.empujar(trayectoria.getDireccion());
        }
    }
}
