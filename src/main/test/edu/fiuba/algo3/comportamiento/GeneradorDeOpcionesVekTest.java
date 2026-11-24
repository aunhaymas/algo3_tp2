package edu.fiuba.algo3.comportamiento;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.BuscadorBfs;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
import edu.fiuba.algo3.modelo.veks.comportamiento.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.comportamiento.GeneradorDeOpcionesVek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorDeOpcionesVekTest {
    /*
    1 Genero todas las trayectorias desde el origen del vek
    2 para cada una: obtengo la coordenada destino
    3 desde coordenada destino: consulto que edificios puede atacar desde ahi
    4 genero una OpcionAtaqueEdificio por cada consulta validada
    * */
    @Test
    void generadorDeOpcionesGeneraLaOpcionAtaqueAEdificio(){
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla posicionAtaque = Casilla.crearEn(1, 0);
        Casilla casillaEdificio = Casilla.crearEn(2, 0);
        RedElectrica red = new RedElectrica();
        PiezaEdificio edificio = new PiezaEdificio(red);
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Saltador();
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, posicionAtaque, casillaEdificio));

        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);

        assertEquals(1, opciones.size());

        AnuncioDeAtaque anuncioDeAtaque = opciones.get(0).ejecutar(vek);
        anuncioDeAtaque.ejecutarAtaque(tablero);

        assertEquals(vek, posicionAtaque.obtenerPieza());
        assertTrue(edificio.estaDestruida());
    }
}
