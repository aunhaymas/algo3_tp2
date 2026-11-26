package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.ataques.AtaqueAcido;
import edu.fiuba.algo3.modelo.unidades.Atacable;
import edu.fiuba.algo3.modelo.unidades.Dañable;

public class ArmaAcido extends Arma{
    @Override
    public void atacar(Dañable objetivo) {

    }

    @Override
    public void recorrer(Trayectoria trayectoria) {
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new AtaqueAcido());

    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaqueAcido());
    }

    @Override
    public boolean puedeAtacar(Trayectoria trayectoria) {
        return !trayectoria.tieneObstaculos();
    }
}
