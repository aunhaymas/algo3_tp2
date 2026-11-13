package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;

//Verificar que al empujar una unidad contra otra unidad u obstáculo,
// la unidad no se mueva y se aplique el daño de colisión.
public class CasoDeUso1Test {
    @Test
    public void empujarUnaUnidadContraOtraLaUnidadNoSeMueve(){
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_colision = new Casilla(new Coordenada(1, 0));
        MechDeCombate mechColision = new MechDeCombate();
        casilla_colision.recibirPieza(mechColision);
        Mech mech = new MechDeCombate();
        casilla_origen.recibirPieza(mech);
        casilla_origen.empujarA(casilla_colision);
        assertEquals(mech,casilla_origen.obtenerPieza());
        assertEquals(mechColision,casilla_colision.obtenerPieza());
    }

    @Test
    public void empujarUnaUnidadContraOtraLaUnidadAplicaDanioDeColision(){
        Casilla casilla_origen = new Casilla(new Coordenada(0, 0));
        Casilla casilla_colision = new Casilla(new Coordenada(1, 0));
        MechDeCombate mechColision = new MechDeCombate(); // 3 de vida
        casilla_colision.recibirPieza(mechColision);
        Mech mech = new MechDeCombate(); // 3 de vida
        casilla_origen.recibirPieza(mech);
        //ACT
        casilla_origen.empujarA(casilla_colision); // Colision 1 de Danio

        assertEquals(2, mech.getVida());
        assertEquals(2,mechColision.getVida());
    }
}
