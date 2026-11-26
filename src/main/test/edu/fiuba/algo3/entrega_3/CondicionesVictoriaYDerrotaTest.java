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
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.veks.Avispon;
import edu.fiuba.algo3.modelo.veks.Luciernaga;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
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
    void partidaSePierdeCuandotodosLosMechsSonDestruidos() {
        Mech mech1 = new MechDeCombate();
        Mech mech2 = new MechDeArtilleria();
        Mech mech3 = new MechDeCanon();
        Vek vek1 = new Saltador(); // 2 de danio
        Vek vek2 = new Saltador(); // 2 de danio
        Vek vek3 = new Saltador(); // 2 de danio
        List<Mech> mechs = List.of(mech1, mech2, mech3);
        Casilla casillaMech1 = Casilla.crearEn(0,0);
        Casilla casillaMech2 = Casilla.crearEn(1,0);
        Casilla casillaMech3 = Casilla.crearEn(2,0);
        Casilla casillaVek1 =  Casilla.crearEn(0,1);
        Casilla casillaVek2 =  Casilla.crearEn(1,1);
        Casilla casillaVek3 =  Casilla.crearEn(2,1);
        casillaMech1.recibirPieza(mech1);
        casillaMech2.recibirPieza(mech2);
        casillaMech3.recibirPieza(mech3);
        casillaVek1.recibirPieza(vek1);
        casillaVek2.recibirPieza(vek2);
        casillaVek3.recibirPieza(vek3);
        Tablero tablero = new Tablero(List.of(casillaMech1,casillaMech2,casillaMech3,casillaVek1,casillaVek2,casillaVek3));
        Partida partida = new Partida(tablero, new RedElectrica(), mechs);
        //En dos Turnos (8 fases), los saltadores habran hecho, 4 de danio. los mechs estaran muertos
        for (int i = 0; i < 8; i++) {
            partida.avanzarFase();
        }

        assertTrue(partida.esGameOver());
        assertTrue(partida.sePerdio());
    }

    @Test
    void partidaTerminaEnDerrotaSiLaRedElectricaLlegaACero(){
        RedElectrica red = new RedElectrica(); // tiene 5 de vida
        Vek vek1 = new Saltador();
        Vek vek2 = new Saltador();
        Vek vek3 = new Saltador();
        Vek vek4 = new Luciernaga();
        Vek vek5 = new Avispon();
        Casilla casillaEdificio1 = Casilla.crearEn(0,0);
        Casilla casillaEdificio2 = Casilla.crearEn(1,0);
        Casilla casillaEdificio3 = Casilla.crearEn(2,0);
        Casilla casillaEdificio4 = Casilla.crearEn(3,0);
        Casilla casillaEdificio5 = Casilla.crearEn(4,0);
        Casilla casillaVek1 =  Casilla.crearEn(0,1);
        Casilla casillaVek2 =  Casilla.crearEn(1,1);
        Casilla casillaVek3 =  Casilla.crearEn(2,1);
        Casilla casillaVek4 =  Casilla.crearEn(3,1);
        Casilla casillaVek5 =  Casilla.crearEn(4,1);
        casillaEdificio1.recibirPieza(new PiezaEdificio(red));
        casillaEdificio2.recibirPieza(new PiezaEdificio(red));
        casillaEdificio3.recibirPieza(new PiezaEdificio(red));
        casillaEdificio4.recibirPieza(new PiezaEdificio(red));
        casillaEdificio5.recibirPieza(new PiezaEdificio(red));
        casillaVek1.recibirPieza(vek1);
        casillaVek2.recibirPieza(vek2);
        casillaVek3.recibirPieza(vek3);
        casillaVek4.recibirPieza(vek4);
        casillaVek5.recibirPieza(vek5);
        Tablero tablero = new Tablero(List.of(casillaEdificio1,casillaEdificio2,casillaEdificio3,casillaEdificio4,casillaEdificio5,casillaVek1,casillaVek2,casillaVek3,casillaVek4,casillaVek5));
        Partida partida = new Partida(tablero,red,List.of(new Mech()));
        for (int i = 0; i < 4; i++) {
            partida.avanzarFase();
        }
        assertTrue(partida.sePerdio());
    }
}
