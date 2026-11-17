package edu.fiuba.algo3.modelo.mechs;

import edu.fiuba.algo3.modelo.armas.Artilleria;

public class MechDeArtilleria extends Mech {
    public MechDeArtilleria() {
        this.vida = 2;
        this.arma = new Artilleria();
    }
}
