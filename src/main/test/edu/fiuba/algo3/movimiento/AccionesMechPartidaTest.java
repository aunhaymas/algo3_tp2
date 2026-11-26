package edu.fiuba.algo3.movimiento;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccionesMechPartidaTest {

    @Test
    void seSeleccionarUnMechenFaseJugadorParaMoverse() {
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla destino = Casilla.crearEn(0, 1);
        Mech mech = new MechDeCombate();
        origen.recibirPieza(mech);

        Tablero tablero = new Tablero(List.of(origen,destino));
        Partida partida = new Partida(tablero, new RedElectrica(), List.of(mech));
        // Fases: vek-jugador-resolucion-emergencia
        // primera fase avanza, de fase vek a fase jugador
        partida.avanzarFase();

        //estamos en fase de jugador
        partida.seleccionarMechEn(new Coordenada(0, 0));
        partida.moverMechSeleccionado(new Coordenada(0,1));
        assertFalse(origen.tiene(mech));
        assertTrue(destino.tiene(mech));
    }
}
