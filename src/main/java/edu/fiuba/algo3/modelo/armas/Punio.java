package edu.fiuba.algo3.modelo.armas;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.ataques.AtaquePunio;
import edu.fiuba.algo3.modelo.excepciones.ObjetoFueraDeRangoException;
import edu.fiuba.algo3.modelo.unidades.Atacable;
import edu.fiuba.algo3.modelo.unidades.Dañable;

public class Punio extends Arma {

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(2);
    }

    @Override
    public void recorrer(Trayectoria trayectoria) {
        // Trayectoria no debe tener longitud mayor a 2.
        if (!trayectoria.esTrayectoriaCorta()) {
            throw new ObjetoFueraDeRangoException();
        } else {
            Casilla destino = trayectoria.getDestino();
            destino.recibirAtaque(new AtaquePunio(trayectoria.getDireccion()));
        }
    }

    @Override
    public void atacar(Atacable objetivo) {
        objetivo.recibirAtaque(new AtaquePunio());
    }

    @Override
    public boolean puedeAtacar(Trayectoria trayectoria) {
        // las armas mech, tal vez no deberian implementar esto
        throw new RuntimeException("implementar?");
    }
}
