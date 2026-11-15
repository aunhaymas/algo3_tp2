package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;
import edu.fiuba.algo3.modelo.veks.Saltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
//Verificar que cada Vek anuncie su ataque en la Fase Vek
//y que se ejecute en la Fase de Resolución, en el orden anunciado.
/*
* Estructura del Turno
La misión dura 5 turnos. Cada turno sigue la siguiente secuencia:
Fase Vek: cada Vek vivo se mueve y anuncia la casilla que atacará.
* El sistema marca también la casilla donde emergerá el próximo Vek.

* Fase del Jugador: el jugador activa sus Mechs en el orden que elija.
* Cada Mech puede moverse y atacar una vez.
Fase de Resolución: los ataques anunciados se ejecutan en el orden en que fueron anunciados.
* Un Vek muerto antes de su turno no ataca.
Emergencia: aparece el nuevo Vek en la casilla marcada.
* Si una unidad ocupa esa casilla, bloquea la emergencia y recibe 1 de daño.
* */

// Fase vek:
// 1) Todos los vek se mueven
// 2) Todos los vek anuncian su ataque
public class CasoDeUso7Test {
    @Test
    public void AnuncioDeAtaqueDelVekNoDaniaAlMech(){
        Vek saltador = new Saltador(); // 2 de daño
        Mech mech = new MechDeCanon(); // 3 de vida
        Casilla casillaMech = new Casilla(new Coordenada(1,1));
        casillaMech.recibirPieza(mech);

        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque = saltador.anunciarAtaque(casillaMech);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque);
        assertEquals(3,mech.getVida());
    }

    @Test
    public void AnuncioDeAtaqueDaniaAlMechDeCanonAlEjecutarse(){
        Vek saltador = new Saltador(); // 2 de daño
        Mech mech = new MechDeCanon(); // 3 de vida
        Casilla casillaMech = new Casilla(new Coordenada(1,1));
        casillaMech.recibirPieza(mech);

        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque = saltador.anunciarAtaque(casillaMech);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque);
        registroDeAtaques.ejecutarSiguienteAtaque(anuncioDeAtaque);
        assertEquals(1,mech.getVida());
    }

}
