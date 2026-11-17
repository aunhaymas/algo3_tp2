package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.AtaqueCanon;

public class Canon extends Arma {

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }

    public void atacar(Trayectoria trayectoria, Tablero contexto) {
        // Deberia recorrer todas las casillas hasta que una NO este libre, De ahi hit!. 
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new AtaqueCanon(trayectoria.getDireccion()),contexto);
    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaqueCanon());
    }
}
