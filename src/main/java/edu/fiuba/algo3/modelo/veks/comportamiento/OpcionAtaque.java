package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

public abstract class OpcionAtaque implements Opcion {
    private final Coordenada objetivo;
    private final Trayectoria trayectoria;
    // Recibe la trayectoria a una casilla desde donde pueda atacar
    // objetivo es la coordenada a atacar
    public OpcionAtaque(Trayectoria trayectoria, Coordenada objetivo) {
        this.objetivo=objetivo;
        this.trayectoria=trayectoria;
    }

    @Override
    public void ejecutar(Vek vek) {
        trayectoria.ejecutarTraslado();
        vek.anunciarAtaque(this.objetivo);
    }
}
