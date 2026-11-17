package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.AnuncioReal;
import edu.fiuba.algo3.modelo.veks.Avispon;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Verificar que al empujar a un Vek su ataque anunciado se desplace con él,
// y que si queda apuntando a otro Vek, lo dañe.
public class CasoDeUso8Test {
    @Test
    public void vekAvisponEsEmpujado(){
        Casilla casilla1 = new Casilla(new Coordenada(0,0));
        Casilla casilla2 = new Casilla(new Coordenada(0,1));
        Vek vek = new Avispon();
        casilla1.recibirPieza(vek);
        casilla1.empujarA(casilla2);
        assertFalse(casilla2.estaLibre());
    }

    @Test
    public void vekEmpujadoDesplazaSuAtaqueAnunciadoJuntoConElYnoDaniaASuObjetivo(){
        Casilla casilla1 = new Casilla(new Coordenada(0,0));
        Casilla casilla2 = new Casilla(new Coordenada(1,0));

        Casilla casilla3 = new Casilla(new Coordenada(0,1));//objetivo
        Casilla casilla4 = new Casilla(new Coordenada(1,1));//victima
        Tablero contexto = new Tablero(List.of(casilla1,casilla2,casilla3,casilla4));
        Vek vek = new Avispon();
        Mech mechObjetivo = new MechDeCanon();
        Mech mechVictima = new MechDeCanon();
        casilla1.recibirPieza(vek);
        casilla3.recibirPieza(mechObjetivo);
        casilla4.recibirPieza(mechVictima);

        AnuncioDeAtaque anuncio=vek.anunciarAtaque(new Coordenada(0,1));
        casilla1.empujarA(casilla2);
        anuncio.ejecutarAtaque(contexto);
        assertEquals(3,mechObjetivo.getVida());
    }
    @Test
    public void vekEmpujadoEjecutaSuAtaqueAnunciadoYdaniaAOtroVek(){
            Casilla casilla1 = new Casilla(new Coordenada(0,0));
            Casilla casilla2 = new Casilla(new Coordenada(1,0));

            Casilla casilla3 = new Casilla(new Coordenada(0,1));//objetivo
            Casilla casilla4 = new Casilla(new Coordenada(1,1));//victima
            Tablero contexto = new Tablero(List.of(casilla1,casilla2,casilla3,casilla4));
            Vek vek = new Avispon();
            Mech mechObjetivo = new MechDeCanon();
            Mech mechVictima = new MechDeCanon();
            casilla1.recibirPieza(vek);
            casilla3.recibirPieza(mechObjetivo);
            casilla4.recibirPieza(mechVictima);

            AnuncioDeAtaque anuncio=vek.anunciarAtaque(new Coordenada(0,1));
            casilla1.empujarA(casilla2);
            anuncio.ejecutarAtaque(contexto);
            assertEquals(2,mechVictima.getVida());
    }

}
