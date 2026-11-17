package edu.fiuba.algo3.modelo.mechs;

import edu.fiuba.algo3.modelo.armas.Punio;

public class MechDeCombate extends Mech {
    public MechDeCombate() {
        this.vida = 3;
        this.arma = new Punio();
    }
}
