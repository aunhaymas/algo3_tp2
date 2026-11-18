package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public interface TipoTerreno {
    void aplicarEfecto(Unidad unidad);
    boolean esTransitable();
}
