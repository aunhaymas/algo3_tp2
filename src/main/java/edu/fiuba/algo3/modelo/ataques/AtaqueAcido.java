package edu.fiuba.algo3.modelo.ataques;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class AtaqueAcido extends Ataque {
    public AtaqueAcido() {
        super(1);
    }

    @Override 
    public void efectuarAtaque(Unidad unidad) {
        unidad.recibirDaño(danio);
        unidad.contaminar();
    }
    public void aplicarEfecto(Unidad unidad){
        unidad.contaminar();
    }
}
