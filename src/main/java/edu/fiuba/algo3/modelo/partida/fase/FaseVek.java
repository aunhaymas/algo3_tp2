package edu.fiuba.algo3.modelo.partida.fase;

import edu.fiuba.algo3.modelo.partida.Partida;

public class FaseVek implements FaseTurno{
    @Override
    public FaseTurno avanzarFase(Partida p) {
        p.ejecutarFaseVek();
        return new FaseJugador();
    }
}
