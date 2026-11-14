package edu.fiuba.algo3.entrega_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Verificar que una unidad terrestre que termina sobre Ácido quede Contaminada y reciba el
doble de daño, y que el Avispón no se contamine
 */

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

public class CasoDeUso4Test {

    @Test
    void unidadTerminaEnAcidoQuedaConntaminadaYRecibe2DeDanio(){
        Casilla casilla = new Casilla(new Coordenada(1,1), new Acido());
        Mech mech = new MechDeCombate(); //mech de combate tiene 3 de vida
        Ataque ataqueCanion = new AtaqueCanion();
        casilla.recibirPieza(mech);

        int vidaEsperada = 1;
        mech.recibirAtaque(ataqueCanion);

        assertEquals(vidaEsperada,mech.getVida());
    }

    @Test
    void avisponSobreAcidoNoSeContamina(){
        Casilla casilla = new Casilla(new Coordenada(1,1), new Acido());
        Avispon avispon = new Avispon(); //mech de combate tiene 3 de vida
        Ataque ataqueCanion = new AtaqueCanion();
        casilla.recibirPieza(avispon);

        int vidaEsperada = 1;
        avispon.recibirAtaque(ataqueCanion);

        assertEquals(vidaEsperada,avispon.getVida());
    }
}
