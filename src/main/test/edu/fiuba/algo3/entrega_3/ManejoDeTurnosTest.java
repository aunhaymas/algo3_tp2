package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.veks.Avispon;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManejoDeTurnosTest {
    @Test
    void faseVekYResolucionEjecutanEnOrdenYAvanzaAlTurnoDos() {
        // - Saltador en (0,0), libre (1,0), Edificio en (2,0)
        Casilla origenVek = Casilla.crearEn(0, 0);
        Casilla ataqueVek = Casilla.crearEn(1, 0);
        Casilla casilla3 = Casilla.crearEn(3, 0);
        Casilla casillaEdificio = Casilla.crearEn(2, 0);
        Casilla casillaMech = Casilla.crearEn(4, 0);

        RedElectrica red = new RedElectrica();
        PiezaEdificio edificio = new PiezaEdificio(red);
        casillaEdificio.recibirPieza(edificio);

        Vek saltador = new Saltador();
        origenVek.recibirPieza(saltador);

        Mech mech = new MechDeCombate();
        casillaMech.recibirPieza(mech);

        Tablero tablero = new Tablero(List.of(origenVek, ataqueVek, casillaEdificio, casillaMech,casilla3));
        Partida partida = new Partida(tablero, red,List.of(mech));

        // 1. Fase Vek: mueve al Vek y anuncia ataque, pero NO daña el edificio todavía
        partida.avanzarFase();
        assertTrue(ataqueVek.tiene(saltador));
        assertFalse(edificio.estaDestruida());
        //2. Fase Jugador aca no hace nada y pasa el turno
        partida.avanzarFase();
        // 3. Fase Resolución: recien aca se ejecuta el ataque anunciado del Vek
        partida.avanzarFase();
        assertTrue(edificio.estaDestruida());
        assertEquals(4, red.integridadActual());

        partida.avanzarFase();
        assertEquals(2, partida.turnoActual());
    }
}
