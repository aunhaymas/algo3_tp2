package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.Test;

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
    public void anuncioDeAtaqueDelVekNoDaniaAlMech(){
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
    public void anuncioDeAtaqueDelVekDaniaAlMechEnFaseDeResolucion(){
        Vek saltador = new Saltador(); // 2 de daño
        Mech mech = new MechDeCanon(); // 3 de vida
        Casilla casillaMech = new Casilla(new Coordenada(1,1));
        casillaMech.recibirPieza(mech);

        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque = saltador.anunciarAtaque(casillaMech);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque);

        registroDeAtaques.ejecutarSiguienteAtaque();
        assertEquals(1,mech.getVida());
    }

    @Test
    public void ejecutarSiguienteAtaqueUnaVezEjecutaUnicamenteElPrimerAtaqueAnunciado(){
        //Arrange
        Vek avispon1 = new Avispon(); // 2 de daño
        Vek avispon2 = new Avispon(); // 2 de daño
        Mech mech1 = new MechDeCanon(); // 3 de vida
        Mech mech2 = new MechDeCanon(); // 3 de vida
        Casilla casillaMech = new Casilla(new Coordenada(1,1));
        Casilla casillaMech2 = new Casilla(new Coordenada(1,2));
        casillaMech.recibirPieza(mech1);
        casillaMech2.recibirPieza(mech2);

        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque1 = avispon1.anunciarAtaque(casillaMech);
        AnuncioDeAtaque anuncioDeAtaque2 = avispon2.anunciarAtaque(casillaMech2);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque1);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque2);
        //Act
        registroDeAtaques.ejecutarSiguienteAtaque();
        //Assert
        assertEquals(2,mech1.getVida());
        assertEquals(3,mech2.getVida());
    }

    @Test
    public void ejecutarSiguienteDosVecesEjecutaElPrimerYultimoAtaque(){
        //Arrange
        Vek avispon1 = new Avispon(); // 2 de daño
        Vek avispon2 = new Avispon(); // 2 de daño
        Mech mech1 = new MechDeCanon(); // 3 de vida
        Mech mech2 = new MechDeCanon(); // 3 de vida
        Casilla casillaMech = new Casilla(new Coordenada(1,1));
        Casilla casillaMech2 = new Casilla(new Coordenada(1,2));
        casillaMech.recibirPieza(mech1);
        casillaMech2.recibirPieza(mech2);

        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque1 = avispon1.anunciarAtaque(casillaMech);
        AnuncioDeAtaque anuncioDeAtaque2 = avispon2.anunciarAtaque(casillaMech2);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque1);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque2);
        //Act
        registroDeAtaques.ejecutarSiguienteAtaque();
        registroDeAtaques.ejecutarSiguienteAtaque();
        //Assert
        assertEquals(2,mech1.getVida());
        assertEquals(2,mech2.getVida());
    }

}
