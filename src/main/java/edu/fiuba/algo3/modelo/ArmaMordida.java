package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.AtaqueMordida;

public class ArmaMordida extends Arma{
    @Override
    public void atacar(Dañable objetivo) {

    }

    @Override
    public void atacar(Trayectoria trayectoria) {

    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaqueMordida());
    }
}
