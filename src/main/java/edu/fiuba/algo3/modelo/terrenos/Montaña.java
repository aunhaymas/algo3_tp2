package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Montaña implements TipoTerreno {

    @Override
    public void aplicarEfecto(Unidad unidad){

    }

    @Override
    public boolean esTransitable() { 
        return false; 
    }
}
