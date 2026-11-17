package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.AtaqueAcido;
import edu.fiuba.algo3.modelo.ataques.AtaqueMordida;

public class ArmaMordida extends Arma{
    @Override
    public void atacar(Dañable objetivo) {

    }

    @Override
    public void recorrer(Trayectoria trayectoria) {
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new AtaqueMordida());
    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaqueMordida());
    }
}
