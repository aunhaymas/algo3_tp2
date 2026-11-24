package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

public class DecisionAtaqueMech implements Decision {
    private final Opcion opcion;
    private final Vek vek;

    public DecisionAtaqueMech(Opcion opcionAtaqueMech,Vek vek) {
        this.opcion = opcionAtaqueMech;
        this.vek=vek;
    }

    @Override
    public AnuncioDeAtaque ejecutar() {
        return opcion.ejecutar(this.vek);
    }
}
