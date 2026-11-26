package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Seleccionador;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.ArrayList;
import java.util.List;

public class SeleccionadorDeVeks implements Seleccionador {
    private final List<Vek> veksSeleccionados;

    public SeleccionadorDeVeks(List<Vek> veksSeleccionados) {
        this.veksSeleccionados = veksSeleccionados;
    }


    @Override
    public void seleccionarEdificio(PiezaEdificio edificio) {

    }

    @Override
    public void seleccionarMech(Mech mech) {

    }

    @Override
    public void seleccionarVek(Vek vek) {
        if (vek.estaDestruida()) return;
        this.veksSeleccionados.add(vek);
    }

    @Override
    public void seleccionarMontania(PiezaMontania montania) {

    }

    @Override
    public void seleccionarCasilla(Casilla casilla) {

    }
}
