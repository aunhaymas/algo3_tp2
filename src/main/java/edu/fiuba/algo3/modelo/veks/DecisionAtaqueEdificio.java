package edu.fiuba.algo3.modelo.veks;

import java.util.List;

public class DecisionAtaqueEdificio implements Decision{
    private final Opcion opcion;
    private final Vek vek;

    public DecisionAtaqueEdificio(Opcion opcionAtaqueEdificio,Vek vek) {
        this.opcion = opcionAtaqueEdificio;
        this.vek=vek;
    }

    @Override
    public void ejecutar() {
        opcion.ejecutar(this.vek);
    }
}
