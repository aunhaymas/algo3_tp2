package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.AtaqueAcido;

public class ArmaAcido extends Arma{
    @Override
    public void atacar(Dañable objetivo) {

    }

    @Override
    public void atacar(Trayectoria trayectoria, Tablero contexto) {
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new AtaqueAcido(),contexto);
        hit.empujar(trayectoria.getDireccion());
    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaqueAcido());
    }
}
