package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.AtaqueArtilleria;

public class Artilleria extends Arma {
    private final Ataque ataque = new AtaqueArtilleria();

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }

    @Override
    public void recorrer(Trayectoria trayectoria) {
        // Deberia recorrer todas las casillas hasta que una NO este libre, De ahi hit!. 
        Casilla destino = trayectoria.getDestino();
        destino.recibirAtaque(new AtaqueArtilleria());

    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(ataque);
    }
}
