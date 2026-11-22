package edu.fiuba.algo3.entrega_2;
//https://refactoring.guru/es/design-patterns/chain-of-responsibility
//Verificar que la IA de los Vek aplique las reglas de prioridad en orden.

/*
La IA de los Vek debe ser determinista, para que sus decisiones puedan probarse.
Cada Vek evalúa las siguientes reglas en orden y aplica la primera que pueda cumplir

1
Moverse a una posición desde la que pueda atacar un Edificio.
2
Moverse a una posición desde la que pueda atacar un Mech.
3
Acercarse lo más posible al edificio más cercano, sin anunciar ataque.

Asumimos que vek recibira las rutas (trayectorias) ya definidas hacia los objetivos mas cercanos?

vek esta en una casilla.
una entidad externa crea una lista de opciones (no me preocupo ahora)
opcionEdificio, opcionMech, opcionAcercarse?
que es una opcion? tiene una trayectoria a un objetivo, y una coordenada de ese objetivo
vek >> tomarDecision(opciones) retorna un objeto Decision con trayectoria para el movimiento
Decision tiene dos implementaciones: DecisionAtacar, DecisionAcercarse.
es un comando. al ejecutarse el mech se mueve a una posicion. si es una decisionAtacar, entonces anuncia su ataque!!!
claro! ahi la propia decision le otorga al vek la coordenada objetivo

 */


import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.comportamiento.OpcionAcercarseAEdificio;
import edu.fiuba.algo3.modelo.veks.comportamiento.OpcionAtaqueEdificio;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.comportamiento.OpcionAtaqueMech;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso9Test {
    // vek en casilla origen tiene un edificio en casilla adyacente
    // vek decide atacar casilla adyacente
    @Test
    void VekSaltadorEvaluaOpcionAtaqueEdificio(){
        Casilla casillavek=new Casilla(new Coordenada(3,0));
        Casilla casillaEsperada=new Casilla(new Coordenada(2,0));
        Casilla casillaEdificio=new Casilla(new Coordenada(1,0));
        // |  E  |    |Vek |Mech |
        //    1    2    3    4
        Trayectoria aEdificio = new Trayectoria(List.of(casillavek,casillaEsperada));

        Saltador vek = new Saltador();
        Pieza edificio = new PiezaEdificio(new RedElectrica());

        casillaEdificio.recibirPieza(edificio);
        casillavek.recibirPieza(vek);
        Opcion opcionAtaqueEdificio = new OpcionAtaqueEdificio(aEdificio,new Coordenada(1,0));
        Decision decisionTomada= vek.tomarDecision(List.of(opcionAtaqueEdificio));
        decisionTomada.ejecutar();
        assertFalse(casillaEsperada.estaLibre());
        assertTrue(casillavek.estaLibre());
    }
    @Test
    void VekSaltadorPrefiereOpcionAtaqueEdificioFrenteAAtaqueMech(){
        Casilla casillaEdificio=new Casilla(new Coordenada(1,0));
        Casilla casillaEsperada=new Casilla(new Coordenada(2,0));
        Casilla casillavek=new Casilla(new Coordenada(3,0));
        Casilla casillamech=new Casilla(new Coordenada(4,0));
        // |  E  |    |Vek |Mech |
        //    1    2    3    4
        Trayectoria aEdificio = new Trayectoria(List.of(casillavek,casillaEsperada));
        Trayectoria aMech = new Trayectoria(List.of(casillavek));

        Saltador vek = new Saltador();
        Mech mech = new MechDeArtilleria();
        Pieza edificio = new PiezaEdificio(new RedElectrica());

        casillaEdificio.recibirPieza(edificio);
        casillavek.recibirPieza(vek);
        casillamech.recibirPieza(mech);

        Opcion  opcionAtaqueEdificio = new OpcionAtaqueEdificio(aEdificio,new Coordenada(1,0));
        Opcion  opcionAtaqueMech = new OpcionAtaqueMech(aMech,new Coordenada(4,0));
        Decision decisionTomada= vek.tomarDecision(List.of(opcionAtaqueMech,opcionAtaqueEdificio));
        decisionTomada.ejecutar();
        assertFalse(casillaEsperada.estaLibre());
        assertTrue(casillavek.estaLibre());
    }

    @Test
    void VekSaltadorPrefiereOpcionAtaqueMechFrenteAAcercarseAEdificio(){
        Casilla casillaEdificio=new Casilla(new Coordenada(1,0));
        Casilla casillaEsperada=new Casilla(new Coordenada(2,0));
        Casilla casillavek=new Casilla(new Coordenada(3,0));
        Casilla casillamech=new Casilla(new Coordenada(4,0));
        // |  E  |    |Vek |Mech |
        //    1    2    3    4
        Trayectoria aEdificio = new Trayectoria(List.of(casillavek,casillaEsperada));
        Trayectoria aMech = new Trayectoria(List.of(casillavek));

        Saltador vek = new Saltador();
        Mech mech = new MechDeArtilleria();
        Pieza edificio = new PiezaEdificio(new RedElectrica());

        casillaEdificio.recibirPieza(edificio);
        casillavek.recibirPieza(vek);
        casillamech.recibirPieza(mech);

        Opcion  opcionAcercarseAEdificio = new OpcionAcercarseAEdificio(aEdificio);
        Opcion  opcionAtaqueMech = new OpcionAtaqueMech(aMech,new Coordenada(4,0));
        Decision decisionTomada= vek.tomarDecision(List.of(opcionAcercarseAEdificio,opcionAtaqueMech));
        decisionTomada.ejecutar();
        assertFalse(casillavek.estaLibre());
        assertTrue(casillaEsperada.estaLibre());
        assertEquals(2,mech.getVida());
    }

}
