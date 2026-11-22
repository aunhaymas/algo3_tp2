package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.veks.comportamiento.Evaluador;

public interface Opcion {
    void evaluarse(Evaluador evaluador);
    void ejecutar(Vek vek);
}
