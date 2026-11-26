package edu.fiuba.algo3.entrega_3;


import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.ataques.DanioColision;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import org.junit.jupiter.api.Test;

import javax.crypto.MacSpi;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CondicionesVictoriaYDerrotaTest {
    /*3.9. Condiciones de Victoria
El sistema verifica las condiciones de fin de partida al final de cada Fase de Resolución:

    Condición                                                   Resultado
    Se completan los 5 turnos con la Red Eléctrica mayor a 0.   Victoria
    La Red Eléctrica llega a 0.                                 Derrota
    Los tres Mechs son destruidos.                              Derrota


Al terminar la partida, el sistema muestra el resultado, la cantidad de Vek eliminados y la Red Eléctrica final.
*/
 //Todas las condiciones de derrota pueden pasar en fase de resolucion
    @Test
    void partidaTerminaEnDerrotaSiLaRedElectricaLlegaACero(){
        RedElectrica red = new RedElectrica(); // tiene 5 de vida
        // aca deberiamos crear casillas con edificios y atacarlas
        //partida.verificarGameOver();
        //assertTrue(partida.esGameOver()); la partida puede terminar de 2 formas
        //assertTrue(partida.esDerrota());
    }
}
