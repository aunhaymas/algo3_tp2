package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.TipoMovimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Agua implements TipoTerreno{

    @Override
    public void aplicarEfecto(Unidad unidad){
        unidad.sumergir();
    }

    @Override
    public boolean esTransitable() { 
        return true; 
    }

    @Override
    public boolean puedeTerminarAca(TipoMovimiento mov) {
        return mov.puedeTerminarEnAgua();
    }
}
