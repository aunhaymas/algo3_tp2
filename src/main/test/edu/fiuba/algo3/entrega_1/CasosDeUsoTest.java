package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.MovimientoExcedidoException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
public class CasosDeUsoTest {

    //Verificar que un Mech solo pueda moverse a casillas dentro de su rango de movimiento,
    // sin atravesar obstáculos ni otras unidades.
    @Test
    public void MechSeMueveAUnaCasillaYLaOcupa() {
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_destino = new Casilla(new Coordenada(1, 0));
        List<Casilla> caminoNormal = List.of(casilla_origen, casilla_destino);
        Mech mech = new Mech();

        Trayectoria camino = new Trayectoria(caminoNormal);

        casilla_origen.recibirPieza(mech);
        mech.moverseA(casilla_destino,camino);

        assertFalse(casilla_destino.estaLibre());
        assertTrue(casilla_origen.estaLibre());
    }
    @Test
    public void MechSeMueveAMaximoRangoDeMovimiento() {
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_1 = new Casilla(new Coordenada(1, 0));
        Casilla casilla_2 = new Casilla(new Coordenada(2, 0));
        Casilla casilla_destino = new Casilla(new Coordenada(3, 0));
        List<Casilla> caminoNormal = List.of(
                casilla_origen,
                casilla_1,
                casilla_2,
                casilla_destino);
        Mech mech = new Mech();
        casilla_origen.recibirPieza(mech);
        Trayectoria camino = new Trayectoria(caminoNormal);
        mech.moverseA(casilla_destino,camino);
        assertFalse(casilla_destino.estaLibre());
        assertTrue(casilla_origen.estaLibre());

    }
    @Test
    public void MechIntentaExcederSuRangoDeMovimiento() {
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_1 = new Casilla(new Coordenada(1, 0));
        Casilla casilla_2 = new Casilla(new Coordenada(2, 0));
        Casilla casilla_3 = new Casilla(new Coordenada(3, 0));
        Casilla casilla_destino = new Casilla(new Coordenada(4, 0));


        List<Casilla> caminoExcedido = List.of(
                casilla_origen,
                casilla_1,
                casilla_2,
                casilla_3,
                casilla_destino);
        Trayectoria camino = new Trayectoria(caminoExcedido);
        Mech mech = new Mech();//Su rango maximo es 3
        casilla_origen.recibirPieza(mech);
        assertThrows(MovimientoExcedidoException.class, () -> {
            mech.moverseA(casilla_destino,camino);
        });
    }

    @Test
    public void MechIntentaMoverseEnCaminoBloqueado(){
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_obstaculo = new Casilla(new Coordenada(1, 0));
        Casilla casilla_destino = new Casilla(new Coordenada(2, 0));

        casilla_obstaculo.recibirPieza(new Mech());

        List<Casilla> caminoExcedido = List.of(
                casilla_origen,
                casilla_obstaculo,
                casilla_destino);
        Trayectoria camino = new Trayectoria(caminoExcedido);
        Mech mech = new Mech();
        casilla_origen.recibirPieza(mech);
        assertThrows(MovimientoInvalidoException.class, () -> {
            mech.moverseA(casilla_destino,camino);
        });
    }
    //////////////////////////////////////////////////////////////////////////////////
    //Verificar que el Puño cause 2 de daño a una unidad adyacente
    // y la empuje 1 casilla en la dirección del golpe.
}
