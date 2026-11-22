package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

public class DecisionAcercarseAEdificio implements Decision {
    private final OpcionAcercarseAEdificio acercarseAEdificio;
    private final Vek vek;

    public DecisionAcercarseAEdificio(OpcionAcercarseAEdificio acercarseAEdificio, Vek vek) {
        this.acercarseAEdificio=acercarseAEdificio;
        this.vek = vek;
    }

    @Override
    public void ejecutar() {
        acercarseAEdificio.ejecutar(vek);
    }
}
