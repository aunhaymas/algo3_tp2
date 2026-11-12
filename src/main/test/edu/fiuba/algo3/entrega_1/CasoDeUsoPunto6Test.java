package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Mech;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.excepciones.MovimientoExcedidoException;
import edu.fiuba.algo3.modelo.excepciones.UnidadYaSeMovioException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

//Verificar que un Mech no pueda moverse ni atacar dos veces en el mismo turno.
public class CasoDeUsoPunto6Test {
    @Test
    void mechSeMueveUnaSolaVezPorTurno(){
        Mech mech = new Mech();
        Casilla casillaOrigen = new Casilla(new Coordenada(0,0));
        Casilla casillaDestino = new Casilla(new Coordenada(0,1));
        Casilla casillaAdyacente = new Casilla(new Coordenada(1,1));
        casillaOrigen.recibirPieza(mech);
        Trayectoria camino1 = new Trayectoria(List.of(casillaOrigen,casillaDestino)) ;
        Trayectoria camino2 = new Trayectoria(List.of(casillaDestino,casillaAdyacente)) ;
        mech.moverseA(camino1);
        assertThrows(UnidadYaSeMovioException.class, () -> {
            mech.moverseA(camino2);
        });
    }
}
