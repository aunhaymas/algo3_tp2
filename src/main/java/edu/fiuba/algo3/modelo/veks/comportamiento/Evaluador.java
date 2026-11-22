package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.List;

public interface Evaluador {
    Decision tomarDecision(List<Opcion> opciones, Vek vek);

    void evaluarOpcionAtaqueMech(OpcionAtaqueMech opcion);
    void evaluarOpcionAtaqueEdificio(OpcionAtaqueEdificio opcion);
}
