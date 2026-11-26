package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.BuscaTrayectorias;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeOpcionesVek {
    private final BuscaTrayectorias buscaTrayectorias;
    public GeneradorDeOpcionesVek(BuscaTrayectorias buscador) {
        buscaTrayectorias=buscador;
    }
    public List<Opcion> generarOpcionesPara(Vek vek, Tablero tablero) {
        Casilla origen = tablero.obtenerCasilla(vek);

        List<Trayectoria> trayectorias =
                buscaTrayectorias.trayectoriasDesde(origen.getCoordenada(), vek, tablero);
        trayectorias.add(new Trayectoria(List.of(origen)));

        List<Opcion> opciones = generarOpcionesDeAtaque(vek, tablero, trayectorias);
        generarOpcionAcercamiento(tablero,trayectorias,opciones);
        return opciones;
    }
    private List<Opcion> generarOpcionesDeAtaque(Vek vek, Tablero tablero, List<Trayectoria> trayectorias) {
        List<Opcion> opciones = new ArrayList<>();
        for (Trayectoria trayectoria : trayectorias) {
            Coordenada posicionAtaque = trayectoria.getCoordenadaDestino();
            for (Casilla casilla : tablero.obtenerCasillas()) {
                Coordenada objetivo = casilla.getCoordenada();
                if (!vek.puedeAtacarDesde(tablero, posicionAtaque, objetivo)) continue;
                casilla.serSeleccionadoPor(new SeleccionadorObjetivosAtaqueVek(trayectoria, objetivo, opciones));

            }
        }
        return opciones;
    }

    private void generarOpcionAcercamiento(Tablero tablero, List<Trayectoria> trayectorias, List<Opcion> opciones) {
        SeleccionadorAcercamientoAEdificioVek seleccionador = new SeleccionadorAcercamientoAEdificioVek();
        for (Casilla casilla : tablero.obtenerCasillas()) {
            casilla.serSeleccionadoPor(seleccionador);
        }
        seleccionador.agregarMejorOpcion(trayectorias, opciones);
    }
}
