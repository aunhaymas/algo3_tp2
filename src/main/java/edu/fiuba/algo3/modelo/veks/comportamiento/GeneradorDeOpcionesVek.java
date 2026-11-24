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
        Casilla origen = tablero.casillaQueContiene(vek);

        List<Trayectoria> trayectorias =
                buscaTrayectorias.trayectoriasDesde(
                        origen.getCoordenada(), vek, tablero
                );

        List<Opcion> opciones = new ArrayList<>();

        for (Casilla casillaEdificio : tablero.casillasConEdificios()) {
            Coordenada objetivo = casillaEdificio.getCoordenada();

            for (Trayectoria trayectoria : trayectorias) {
                Coordenada posicionAtaque =
                        trayectoria.getDestino().getCoordenada();

                if (vek.puedeAtacarDesde(tablero, posicionAtaque, objetivo)) {
                    opciones.add(new OpcionAtaqueEdificio(trayectoria, objetivo));
                }
            }
        }

        return opciones;
    }
}
