package edu.fiuba.algo3.modelo.partida.fase;

import edu.fiuba.algo3.modelo.partida.Partida;

public class FaseEmergencia implements FaseTurno {
    @Override
    public FaseTurno avanzarFase(Partida p) {
        p.ejecutarFaseEmergencia();
        p.siguienteTurno();
        return new FaseVek();
    }
}
