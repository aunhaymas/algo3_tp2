package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.estadosUnidad.*;
import edu.fiuba.algo3.modelo.mechs.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.terrenos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.excepciones.*;

public class CasoDeUso3Test {
    
    @Test
    public void VekTerrestreEmpujadoAlAguaMuere() {
        Coordenada posOrigen = new Coordenada(1, 1);
        Coordenada posAgua = new Coordenada(2, 1);

        Casilla casillaOrigen = new Casilla(posOrigen);
        Casilla casillaAgua = new Casilla(posAgua, new Agua());

        Vek terrestre = new Saltador();
        casillaOrigen.recibirPieza(terrestre);
        casillaOrigen.empujarA(casillaAgua);

        assertTrue(terrestre.estaDestruida());
    }

    @Test
    public void AvisponVoladorEmpujadoAlAguaNoMuere() {
        Coordenada posOrigen = new Coordenada(1, 1);
        Coordenada posAgua = new Coordenada(2, 1);

        Casilla casillaOrigen = new Casilla(posOrigen);
        Casilla casillaAgua = new Casilla(posAgua, new Agua());

        Vek avispon = new Avispon();
        casillaOrigen.recibirPieza(avispon);
        casillaOrigen.empujarA(casillaAgua);

        assertTrue(!avispon.estaDestruida());
    }

    @Test
    public void MechEnElAguaNoPuedeAtacar() {
        Coordenada posAgua = new Coordenada(1, 1);
        Casilla casillaAgua = new Casilla(posAgua, new Agua());

        Vek objetivo = new Saltador();
        Mech mech = new Mech();
        casillaAgua.recibirPieza(mech);

        assertThrows(UnidadSumergidaException.class, () -> {
            mech.atacar(objetivo);
        });
    }
}
