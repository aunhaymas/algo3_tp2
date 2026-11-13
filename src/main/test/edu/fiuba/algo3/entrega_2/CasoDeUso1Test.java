package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.MovimientoInvalidoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals(casilla_origen.obtenerPieza(),mech);
        assertEquals(casilla_colision.obtenerPieza(),mechColision);
    }


}
