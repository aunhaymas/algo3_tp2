package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Seleccionador;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.ArrayList;
import java.util.List;

public class SeleccionadorAcercamientoAEdificioVek implements Seleccionador {
    private Coordenada coordenadaActual;
    private final List<Coordenada> coordenadasEdificios = new ArrayList<>();
    @Override
    public void seleccionarEdificio(PiezaEdificio edificio) {
        // no necesito guardar el edificio.
        this.coordenadasEdificios.add(this.coordenadaActual);
    }

    @Override
    public void seleccionarMech(Mech mech) {

    }

    @Override
    public void seleccionarVek(Vek vek) {

    }

    @Override
    public void seleccionarMontania(PiezaMontania montania) {

    }

    @Override
    public void seleccionarCasilla(Casilla casilla) {
        this.coordenadaActual = casilla.getCoordenada();

    }

    public void agregarMejorOpcion(List<Trayectoria> trayectorias, List<Opcion> opciones) {
        if (coordenadasEdificios.isEmpty() || trayectorias.isEmpty()) return;

        Trayectoria mejor = trayectorias.get(0);
        Coordenada mejorDestino = mejor.getCoordenadaDestino();

        for (Trayectoria candidata : trayectorias) {
            Coordenada destinoCandidato = candidata.getCoordenadaDestino();
            if (destinoCandidato.estaMasCercaQue(mejorDestino, coordenadasEdificios)) {
                mejor = candidata;
                mejorDestino = destinoCandidato;
            }
        }

        opciones.add(new OpcionAcercarseAEdificio(mejor));
    }
}
