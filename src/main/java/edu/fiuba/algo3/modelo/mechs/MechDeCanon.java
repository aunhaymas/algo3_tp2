package edu.fiuba.algo3.modelo.mechs;

import edu.fiuba.algo3.modelo.armas.Canon;

public class MechDeCanon extends Mech {
    public MechDeCanon() {
        this.vida = 3;
        this.arma = new Canon();
    }
}
