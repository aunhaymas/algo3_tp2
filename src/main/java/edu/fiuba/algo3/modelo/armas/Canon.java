package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.ataques.AtaqueCanon;
import edu.fiuba.algo3.modelo.unidades.Atacable;
import edu.fiuba.algo3.modelo.unidades.Dañable;

public class Canon extends Arma {

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }

    @Override
    public void recorrer(Trayectoria trayectoria) {
        // Deberia recorrer todas las casillas hasta que una NO este libre, De ahi hit!. 
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new AtaqueCanon(trayectoria.getDireccion()));
    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaqueCanon());
    }

    @Override
    public boolean puedeAtacar(Trayectoria trayectoria) {
        throw new RuntimeException("Implementar?");
    }
}
