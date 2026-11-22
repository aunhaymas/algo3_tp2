package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

public class OpcionAtaqueMech extends OpcionAtaque {
    public OpcionAtaqueMech(Trayectoria trayectoria, Coordenada objetivo) {
        super(trayectoria, objetivo);
    }

    @Override
    public void evaluarse(Evaluador evaluador) {
        evaluador.evaluarOpcionAtaqueMech(this);
    }
}
