package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Acido implements TipoTerreno{

    @Override
    public void aplicarEfecto(Unidad unidad) {
        unidad.contaminar();
    }
}
