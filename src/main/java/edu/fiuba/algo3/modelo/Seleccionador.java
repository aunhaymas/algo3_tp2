package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.Vek;

public interface Seleccionador {
    void seleccionarEdificio(PiezaEdificio edificio);

    void seleccionarMech(Mech mech);

    void seleccionarVek(Vek vek);

    void seleccionarMontania(PiezaMontania montania);

    void seleccionarCasilla(Casilla casilla);
}