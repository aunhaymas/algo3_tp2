package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.AtaquePunio;
import edu.fiuba.algo3.modelo.excepciones.ObjetoFueraDeRangoException;

public class Punio extends Arma {

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(2);
    }

    public void atacar(Trayectoria trayectoria,Tablero contexto) {
        // Trayectoria no debe tener longitud mayor a 2.
        if (!trayectoria.esTrayectoriaCorta()) {
            throw new ObjetoFueraDeRangoException();
        } else {
            Casilla destino = trayectoria.getDestino();
            destino.recibirAtaque(new Ataque(2),contexto);
            destino.empujar(trayectoria.getDireccion());
        }
    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaquePunio());
    }
}
