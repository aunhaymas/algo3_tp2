package edu.fiuba.algo3.modelo.partida.fase;

import edu.fiuba.algo3.modelo.partida.Partida;

public class FaseResolucion implements FaseTurno {
    @Override
    public FaseTurno avanzarFase(Partida p) {
        p.ejecutarFaseResolucion();
        return new FaseEmergencia();
    }
}
