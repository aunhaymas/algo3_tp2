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

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void buscaTrayectoriasNoEncuentraCasillasMasAllaDeUnObstaculo() {
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla primera = Casilla.crearEn(1, 0);
        Casilla obstaculo = Casilla.crearEn(2, 0);
        Casilla destino = Casilla.crearEn(3, 0);
        obstaculo.recibirPieza(new PiezaMontania());

        Tablero tablero = new Tablero(
                List.of(origen,primera,obstaculo,destino)
        );

        Unidad vek = new Saltador();// movimiento 3
        BuscaTrayectorias buscador = new BuscadorBfs();

        List<Trayectoria> trayectorias = buscador.trayectoriasDesde(origen.getCoordenada(), vek, tablero);

        assertEquals(1, trayectorias.size());
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == primera));
        assertFalse(trayectorias.stream().anyMatch(t -> t.getDestino() == destino));
    }
    @Test
    void buscaTrayectoriasPermitrRodearObstaculo(){
        //  c   c   o
        //  c   x   c

        Casilla origen = Casilla.crearEn(0, 0);
        Casilla obstaculo = Casilla.crearEn(1, 0);
        Casilla primera = Casilla.crearEn(0, 1);
        Casilla segunda = Casilla.crearEn(1, 1);
        Casilla destino = Casilla.crearEn(2, 1);
        obstaculo.recibirPieza(new PiezaMontania());

        Tablero tablero = new Tablero(
                List.of(origen,primera,segunda,obstaculo,destino)
        );

        Unidad vek = new Saltador();// movimiento 3
        BuscaTrayectorias buscador = new BuscadorBfs();

        List<Trayectoria> trayectorias = buscador.trayectoriasDesde(origen.getCoordenada(), vek, tablero);

        assertEquals(3, trayectorias.size()); // hay 3 caminos hasta agotar movimientos
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == destino));
        assertFalse(trayectorias.stream().anyMatch(t -> t.getDestino() == obstaculo));
    }



    @Test
    void avisponPuedeSaltarseObstaculos(){
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla primera = Casilla.crearEn(1, 0);
        Casilla obstaculo = Casilla.crearEn(2, 0);
        Casilla segunda = Casilla.crearEn(3, 0);
        Casilla destino = Casilla.crearEn(4, 0);
        obstaculo.recibirPieza(new PiezaMontania());

        Tablero tablero = new Tablero(
                List.of(origen,primera,obstaculo,segunda,destino)
        );

        Unidad vek = new Avispon();// movimiento 4
        BuscaTrayectorias buscador = new BuscadorBfs();

        List<Trayectoria> trayectorias = buscador.trayectoriasDesde(origen.getCoordenada(), vek, tablero);

        assertEquals(3, trayectorias.size());
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == primera));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == segunda));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == destino));
    }
    @Test
    void buscaTrayectoriasRespetaRangoDeMovimiento(){
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla primera = Casilla.crearEn(1, 0);
        Casilla tercera = Casilla.crearEn(2, 0);
        Casilla segunda = Casilla.crearEn(3, 0);
        Casilla cuarta = Casilla.crearEn(4, 0);

        Tablero tablero = new Tablero(
                List.of(origen,primera,tercera,segunda,cuarta)
        );

        Unidad mech = new MechDeCombate();// movimiento 3
        BuscaTrayectorias buscador = new BuscadorBfs();

        List<Trayectoria> trayectorias = buscador.trayectoriasDesde(origen.getCoordenada(), mech, tablero);

        assertEquals(3, trayectorias.size());
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == primera));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == segunda));
        assertTrue(trayectorias.stream().anyMatch(t -> t.getDestino() == tercera));
        assertFalse(trayectorias.stream().anyMatch(t -> t.getDestino() == cuarta));
    }

}
