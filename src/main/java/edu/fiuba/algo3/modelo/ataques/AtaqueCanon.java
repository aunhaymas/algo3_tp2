package edu.fiuba.algo3.modelo.ataques;

import edu.fiuba.algo3.modelo.Ataque;
import edu.fiuba.algo3.modelo.Direccion;

public class AtaqueCanon extends Ataque {
    public AtaqueCanon() {
        super(1);
    }
    public AtaqueCanon(Direccion dir) {
        super(1,dir);
    }
}
