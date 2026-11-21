package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.veks.Avispon;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CasoDeUso10Test {

    /*
    Verificar que una unidad sobre la casilla de emergencia bloquee la aparición del nuevo Vek
    y reciba 1 de daño.
     */
    @Test
    public void unidadOcupeCasillaDeEmergencia(){
        Casilla casilla = new Casilla(new Coordenada(1,1), new Suelo());
        Vek avispon = new Avispon();
        Unidad mech = new MechDeCombate();

        casilla.recibirPieza(mech);
        casilla.emergencia(avispon);
        casilla.resolverEmergencia();
        int vidaEsperada = 2;

        assertEquals(vidaEsperada, mech.getVida());

    }
}
