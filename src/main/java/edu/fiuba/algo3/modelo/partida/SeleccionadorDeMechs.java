package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Seleccionador;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.Vek;

public class SeleccionadorDeMechs implements Seleccionador {
    private Mech mechSeleccionado = null;
    @Override
    public void seleccionarEdificio(PiezaEdificio edificio) {

    }

    @Override
    public void seleccionarMech(Mech mech) {
        this.mechSeleccionado = mech;
    }

    @Override
    public void seleccionarVek(Vek vek) {

    }

    @Override
    public void seleccionarMontania(PiezaMontania montania) {

    }

    @Override
    public void seleccionarCasilla(Casilla casilla) {

    }
    public Mech obtenerMechSeleccionado() {
        return this.mechSeleccionado;
    }
}
