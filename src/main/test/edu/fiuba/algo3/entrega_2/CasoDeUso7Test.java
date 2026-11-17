package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.Avispon;
import edu.fiuba.algo3.modelo.veks.RegistroDeAtaques;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.BeforeEach;
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
    private Coordenada coordenada01;
    private Coordenada coordenada02;
    private Coordenada coordenada03;
    private Coordenada coordenada04;

    private Coordenada coordenada11;
    private Coordenada coordenada12;
    private Coordenada coordenada13;
    private Coordenada coordenada14;
    private RegistroDeAtaques registroDeAtaques;

    @BeforeEach
    public void setUp() {
        coordenada01 = new Coordenada(0,1);
        coordenada02 = new Coordenada(0,2);
        coordenada03 = new Coordenada(0,3);
        coordenada04 = new Coordenada(0,4);

        coordenada11 = new Coordenada(1,1);
        coordenada12 = new Coordenada(1,2);
        coordenada13 = new Coordenada(1,3);
        coordenada14 = new Coordenada(1,4);

        registroDeAtaques = new RegistroDeAtaques();
    }
    @Test
    public void anuncioDeAtaqueDelVekNoDaniaAlMech(){
        Vek saltador = new Saltador(); // 2 de daño
        Mech mech = new MechDeCanon(); // 3 de vida
        Coordenada origen = coordenada01;
        Coordenada objetivo = coordenada11;
        Casilla casillaMech = new Casilla(objetivo);
        casillaMech.recibirPieza(mech);

        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque = saltador.anunciarAtaque(origen,objetivo);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque);
        assertEquals(3,mech.getVida());
    }
    @Test
    public void anuncioDeAtaqueDelVekDaniaAlMechEnFaseDeResolucion(){
        Vek saltador = new Saltador(); // 2 de daño
        Mech mech = new MechDeCanon(); // 3 de vida
        Casilla casillaOrigen = new Casilla(coordenada01);
        Coordenada objetivo = coordenada11;
        Casilla casillaMech = new Casilla(objetivo);
        casillaMech.recibirPieza(mech);
        Tablero contexto = new Tablero(List.of(casillaOrigen,casillaMech));
        // Esta entidad puede ser modificada en fase Jugador
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque = saltador.anunciarAtaque(coordenada01,objetivo);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque);
        registroDeAtaques.ejecutarSiguienteAtaque(contexto);
        assertEquals(1,mech.getVida());
    }

    @Test
    public void ejecutarSiguienteAtaqueUnaVezEjecutaUnicamenteElPrimerAtaqueAnunciado(){
        //Arrange
        Vek avispon1 = new Avispon(); // 2 de daño
        Vek avispon2 = new Avispon(); // 2 de daño
        Mech mech1 = new MechDeCanon(); // 3 de vida
        Mech mech2 = new MechDeCanon(); // 3 de vida

        Coordenada origen1 = coordenada01;
        Coordenada origen2 = coordenada02;
        Coordenada objetivo1 = coordenada11;
        Coordenada objetivo2 = coordenada12;

        Casilla casillaOrigen1 = new Casilla(origen1);
        Casilla casillaOrigen2 = new Casilla(origen2);
        Casilla casillaMech1 = new Casilla(objetivo1);
        Casilla casillaMech2 = new Casilla(objetivo2);

        casillaMech1.recibirPieza(mech1);
        casillaMech2.recibirPieza(mech2);

        Tablero contexto = new Tablero(List.of(casillaOrigen1, casillaOrigen2, casillaMech1, casillaMech2));
        RegistroDeAtaques registroDeAtaques= new RegistroDeAtaques();

        // FaseVek
        AnuncioDeAtaque anuncio1 = avispon1.anunciarAtaque(origen1, objetivo1);
        AnuncioDeAtaque anuncio2 = avispon2.anunciarAtaque(origen2, objetivo2);

        registroDeAtaques.registrarAtaque(anuncio1);
        registroDeAtaques.registrarAtaque(anuncio2);
        //Act
        registroDeAtaques.ejecutarSiguienteAtaque(contexto);
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
        Casilla casillaMech = new Casilla(coordenada11);
        Casilla casillaMech2 = new Casilla(coordenada12);
        casillaMech.recibirPieza(mech1);
        casillaMech2.recibirPieza(mech2);
        // Esta entidad puede ser modificada en fase Jugador
        // FaseVek
        AnuncioDeAtaque anuncioDeAtaque1 = avispon1.anunciarAtaque(coordenada01,coordenada11);
        AnuncioDeAtaque anuncioDeAtaque2 = avispon2.anunciarAtaque(coordenada02,coordenada12);
        Tablero contexto = new Tablero(List.of(casillaMech,casillaMech2));
        registroDeAtaques.registrarAtaque(anuncioDeAtaque1);
        registroDeAtaques.registrarAtaque(anuncioDeAtaque2);
        //Act
        registroDeAtaques.ejecutarSiguienteAtaque(contexto);
        registroDeAtaques.ejecutarSiguienteAtaque(contexto);
        //Assert
        assertEquals(2,mech1.getVida());
        assertEquals(2,mech2.getVida());
    }

}
