package edu.fiuba.algo3.comportamiento;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.veks.comportamiento.GeneradorDeOpcionesVek;
import edu.fiuba.algo3.modelo.veks.comportamiento.OpcionAtaqueEdificio;
import org.junit.jupiter.api.Test;

public class GeneradorDeOpcionesVekTest {
    /*
    1 Genero todas las trayectorias desde el origen del vek
    2 para cada una: obtengo la coordenada destino
    3 desde coordenada destino: consulto que edificios puede atacar desde ahi
    4 genero una OpcionAtaqueEdificio por cada consulta validada
    * */
    @Test
    void generadorDeOpcionesGeneraLaOpcionAtaqueAEdificio(){
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek();
        Coordenada objetivo = new Coordenada(2,0);
        Trayectoria
        OpcionAtaqueEdificio ataqueEdificio = new OpcionAtaqueEdificio()

    }
}
