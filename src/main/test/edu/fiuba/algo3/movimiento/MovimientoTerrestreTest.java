package edu.fiuba.algo3.movimiento;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;

import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.Luciernaga;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovimientoTerrestreTest {
    @Test
    public void mechDeCombateNoPuedeAtravesarCasillaOcupada(){
        Mech mechCombate = new MechDeCombate();
        Casilla casillaOcupada = Casilla.crearEn(0,0);
        casillaOcupada.recibirPieza(new PiezaMontania());
        assertFalse(mechCombate.puedeTransitar(casillaOcupada));
    }
    @Test
    public void mechDeArtilleriaNoPuedeAtravesarCasillaOcupada(){
        Mech mechArtillero = new MechDeArtilleria();
        Casilla casillaOcupada = Casilla.crearEn(0,0);
        casillaOcupada.recibirPieza(new PiezaMontania());
        assertFalse(mechArtillero.puedeTransitar(casillaOcupada));
    }
    @Test
    public void mechDeCAnonNoPuedeAtravesarCasillaOcupada(){
        Mech mechCanon = new MechDeCanon();
        Casilla casillaOcupada = Casilla.crearEn(0,0);
        casillaOcupada.recibirPieza(new PiezaMontania());
        assertFalse(mechCanon.puedeTransitar(casillaOcupada));
    }
    @Test
    public void saltadorNoPuedeAtravesarCasillaOcupada(){
        Vek saltador = new Saltador();
        Casilla casillaOcupada = Casilla.crearEn(0,0);
        casillaOcupada.recibirPieza(new PiezaMontania());
        assertFalse(saltador.puedeTransitar(casillaOcupada));
    }
    @Test
    public void luciernagaNoPuedeAtravesarCasillaOcupada(){
        Vek lucienrnaga = new Luciernaga();
        Casilla casillaOcupada = Casilla.crearEn(0,0);
        casillaOcupada.recibirPieza(new PiezaMontania());
        assertFalse(lucienrnaga.puedeTransitar(casillaOcupada));
    }

}