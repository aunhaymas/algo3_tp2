package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

public class DecisionAtaqueEdificio implements Decision {
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
