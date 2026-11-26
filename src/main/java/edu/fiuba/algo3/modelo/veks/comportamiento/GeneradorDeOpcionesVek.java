package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.BuscaTrayectorias;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.tablero.BuscadorBfs;
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

        List<Opcion> opciones = new ArrayList<>();

        for (Trayectoria trayectoria : trayectorias) {
            Coordenada posicionAtaque = trayectoria.getCoordenadaDestino();

            for (Casilla casilla : tablero.obtenerCasillas()) {
                Coordenada objetivo = casilla.getCoordenada();

                if (vek.puedeAtacarDesde(tablero, posicionAtaque, objetivo)) {
                    casilla.serSeleccionadoPor(
                            new SeleccionadorObjetivosVek(trayectoria, objetivo, opciones)
                    );
                }
            }
        }

        return opciones;
    }
}
