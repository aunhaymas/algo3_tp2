package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUso {

    //Verificar que un Mech solo pueda moverse a casillas dentro de su rango de movimiento,
    // sin atravesar obstáculos ni otras unidades.
    @Test
    public void MechSeMueveAUnaCasillaYLaOcupa() {
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_destino = new Casilla(new Coordenada(1, 0));
        List<Casilla> caminoNormal = List.of(casilla_origen, casilla_destino);
        Mech mech = new Mech();

        casilla_origen.recibirPieza(mech);
        mech.moverseA(casilla_destino,caminoNormal);

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
        mech.moverseA(casilla_destino,caminoNormal);
        assertFalse(casilla_destino.estaLibre());
        assertTrue(casilla_origen.estaLibre());

    }
}
