package edu.fiuba.algo3.movimiento;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;

import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
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

}