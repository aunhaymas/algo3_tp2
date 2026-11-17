package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.ataques.AtaqueTest;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.terrenos.Suelo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Verificar que al deshacer el movimiento de un Mech que terminó sobre Ácido,
// el Mech deje de estar Contaminado.
public class CasoDeUso6Test {
    @Test
    public void MechQueAcabaDeSerContaminadoSeCuraAlRestaurarMovimiento(){
        Mech mech = new MechDeCanon(); // Mech inicia con 3 de vida
        Casilla casilla = new Casilla(new Coordenada(0,0),new Suelo());
        Casilla casillaContaminada = new Casilla(new Coordenada(0,1),new Acido());
        AtaqueTest ataque = new AtaqueTest(1);
        Trayectoria camino = new Trayectoria(List.of(casilla,casillaContaminada));

        casilla.recibirPieza(mech);
        ComandoMover comando = new ComandoMover(mech,camino);
        comando.ejecutar(); // mech se contamina.
        comando.deshacer(); // mech vuelve a la casilla sin ácido.

        mech.recibirAtaque(ataque); //
        assertEquals(2,mech.getVida());

    }

}
