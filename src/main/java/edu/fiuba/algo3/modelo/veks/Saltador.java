package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.ArmaMordida;
import edu.fiuba.algo3.modelo.Atacable;

public class Saltador extends Vek {
    public Saltador(){
        super(2,3);
        this.arma = new ArmaMordida();
    }
    @Override
    public void atacar(Atacable objetivo) {
        this.arma.atacar(objetivo);
    }
}
