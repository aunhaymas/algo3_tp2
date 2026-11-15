package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.ArmaAcido;
import edu.fiuba.algo3.modelo.Atacable;
import edu.fiuba.algo3.modelo.Vek;

public class Luciernaga extends Vek {
    public Luciernaga(){
        super(3,2);
        this.arma = new ArmaAcido();
    }
}
