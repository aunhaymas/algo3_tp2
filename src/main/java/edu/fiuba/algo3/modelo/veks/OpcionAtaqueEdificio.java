package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;

public class OpcionAtaqueEdificio implements Opcion{
    private final Coordenada objetivo;

    private final Trayectoria trayectoria;
    public OpcionAtaqueEdificio(Trayectoria trayectoria, Coordenada objetivo) {
        this.objetivo=objetivo;
        this.trayectoria=trayectoria;
    }

    @Override
    public void ejecutar(Vek vek) {
        trayectoria.ejecutarTraslado();
        vek.anunciarAtaque(this.objetivo);
    }
}
