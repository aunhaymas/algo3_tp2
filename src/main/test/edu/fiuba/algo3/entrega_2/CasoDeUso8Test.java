package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

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
    public void vekEmpujadoDesplazaSuAtaqueAnunciadoJuntoConEl(){
        //Instancio vek y mech
        //Vek anuncia su ataque en la casilla mech
        //empujo vek a una casilla adyacente
        //verifico que mech no recibio el ataque
    }
    @Test
    public void vekEmpujadoEjecutaSuAtaqueAnunciadoYdaniaAOtroVek(){
        // instancio 2 vek
        // vek1 anuncia ataque en una casilla
        // empujo vek1 adyacente a vek2
        // ejecuto el ataque de vek1
        // verifico que vek2 recibio el ataque

    }

}
