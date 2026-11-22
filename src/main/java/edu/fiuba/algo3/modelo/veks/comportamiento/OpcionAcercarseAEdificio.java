package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

public class OpcionAcercarseAEdificio implements Opcion {
    private final Trayectoria trayectoria;

    public OpcionAcercarseAEdificio(Trayectoria trayectoria){
        this.trayectoria=trayectoria;
    }
    @Override
    public void evaluarse(Evaluador evaluador) {
        evaluador.evaluarOpcionAcercarseEdificio(this);
    }

    @Override
    public void ejecutar(Vek vek) {
        trayectoria.ejecutarTraslado();
    }
}
