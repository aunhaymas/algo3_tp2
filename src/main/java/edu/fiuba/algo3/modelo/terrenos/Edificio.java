package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Edificio implements TipoTerreno {
    public Edificio(){}
    public Edificio(RedElectrica redElectrica) {
    }

    @Override
    public void aplicarEfecto(Unidad unidad) {
        // Implementación del efecto del edificio en la unidad
    }

    @Override
    public boolean esTransitable() { 
        return false; 
    }
    
}
