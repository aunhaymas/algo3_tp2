package edu.fiuba.algo3.movimiento;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;

import edu.fiuba.algo3.modelo.tablero.BuscadorBfs;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.veks.Avispon;
import edu.fiuba.algo3.modelo.veks.Luciernaga;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuscaTrayectoriasTest {
    @Test
    void buscaTrayectoriasEncuentraTodasLasTrayectoriasDesdeUnOrigenA4Adyacentes(){
        Coordenada coordOrigen = new Coordenada(1,1);
        Casilla origen = new Casilla(coordOrigen);
        Casilla norte = Casilla.crearEn(1,2);
        Casilla sur = Casilla.crearEn(1,0);
        Casilla este = Casilla.crearEn(2,1);
        Casilla oeste = Casilla.crearEn(0,1);
        Tablero tablero = new Tablero(List.of(origen,norte,sur,este,oeste));
        Unidad saltador = new Saltador();
        BuscaTrayectorias buscador = new BuscadorBfs();
        List<Trayectoria> trayectorias = buscador.trayectoriasDesde(coordOrigen,saltador,tablero);
        assertEquals(4,trayectorias.size());
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == norte));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == sur));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == este));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == oeste));
    }

}
