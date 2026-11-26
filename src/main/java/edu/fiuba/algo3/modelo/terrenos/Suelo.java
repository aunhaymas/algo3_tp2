package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.TipoMovimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Suelo implements TipoTerreno{
    @Override
    public void aplicarEfecto(Unidad unidad) {

    }
    
    @Override
    public boolean esTransitable() { 
        return true; 
    }

    @Override
    public boolean puedeTerminarAca(TipoMovimiento mov) {
        return true;
    }
}
